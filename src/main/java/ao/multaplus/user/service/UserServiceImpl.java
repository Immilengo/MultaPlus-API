package ao.multaplus.user.service;

import ao.multaplus.auth.entity.Auth;
import ao.multaplus.auth.repository.AuthRepository;
import ao.multaplus.role.entity.Roles;
import ao.multaplus.role.service.RoleService;
import ao.multaplus.user.dtos.UserDto;
import ao.multaplus.user.entity.Users;
import ao.multaplus.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    
    private final AuthRepository authRepository;
    
    private final RoleService roleService;
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users create(UserDto userDto, Roles role) {
        role = roleService.findByRole("admin");
        if (authRepository.findByEmail(userDto.authentication().email()) == null) {
            Auth auth = Auth.builder().email( userDto.authentication().email()).password(passwordEncoder.encode(userDto.authentication().password())).role(role).telephone(userDto.telephone()).build();
         //auth= authRepository.save(auth);
            Users user = new Users();
            user.setName(userDto.name());
            user.setDateBirth(userDto.dateBirth());
            user.setBi(userDto.bi());
            user.setImg(userDto.img());
            user.setNIdentification(userDto.nIdentification());
            //user.setGenders(userDto.genderId());
            //user.setState(userDto.stateId());
            //user.setProvince(userDto.provinceId());
            //user.setTypeUsers(userDto.typeUserId());
            user.setLogin(auth);

            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Users update(UserDto userDto) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return "";
    }
}
