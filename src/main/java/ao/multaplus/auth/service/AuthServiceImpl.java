package ao.multaplus.auth.service;

import ao.multaplus.auth.dtos.LoginDto;
import ao.multaplus.auth.dtos.RegisterDto;
import ao.multaplus.auth.dtos.VerifyLoginDto;
import ao.multaplus.auth.entity.Auth;
import ao.multaplus.auth.repository.AuthRepository;
import ao.multaplus.auth.response.LoginResponse;
import ao.multaplus.mail.MailServiceImpl;
import ao.multaplus.sms.service.SMSServiceImpl;
import ao.multaplus.redis.RedisServiceImpl;
import ao.multaplus.role.service.RoleService;
import ao.multaplus.security.TokenService;
import ao.multaplus.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final AuthRepository authRepository;

    private final RoleService roleService;
    private  final SMSServiceImpl SMSService;
    private final MailServiceImpl mailService;
    private final RedisServiceImpl redisService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Value("${api.security.otp.expiration}")
    private long otpExpiration;

    @Override
    public UserDetails loadUserByUsername(
            String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username);
    }

    public LoginResponse login(LoginDto loginDto) {

        var usernamePassowrd = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        var auth = authenticationManager.authenticate(usernamePassowrd);

        return new LoginResponse(tokenService.generateToken((Auth) auth.getPrincipal()));
    }

    @Override
    public Auth register(RegisterDto registerDto) {
        if (authRepository.findByEmail(registerDto.email()) != null) {
            return null;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        Auth auth = new Auth();
        auth.setEmail(registerDto.email());
        auth.setPassword(encryptedPassword);
        auth.setRole(roleService.findByRole(registerDto.role()));
        return authRepository.save(auth);
    }

    @Override
    public void login(LoginDto loginDto, boolean sendOtpByEmail) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        sendOTP((Auth) auth.getPrincipal(),sendOtpByEmail);
    }

    @Override
    public LoginResponse validateLogin(VerifyLoginDto verifyLoginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                verifyLoginDto.email(), verifyLoginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        verifyOTP(((Auth) auth.getPrincipal()).getId().toString(),
                verifyLoginDto.code());
        return new LoginResponse(tokenService.generateToken((Auth) auth.getPrincipal()));
    }

    private void sendOTP(Auth auth, boolean sendOtpByEmail) {
        var otp = Utils.generateOTP();
        redisService.save(auth.getId().toString(),
                passwordEncoder.encode(otp.toString()),
                otpExpiration);
        if(sendOtpByEmail)
            mailService.sendOTP(auth.getEmail(), otp, otpExpiration);
        else
            SMSService.sendSMSWithMimoRestAip(auth.getUsername(), otp.toString());
    }

    private void verifyOTP(String email, Integer code) {
        var otp = redisService.get(email).orElse(null);
        if (otp == null || !passwordEncoder.matches(code.toString(), otp)) {
            throw new RuntimeException("Invalid OTP");
        }
        redisService.delete(email);
    }
}
