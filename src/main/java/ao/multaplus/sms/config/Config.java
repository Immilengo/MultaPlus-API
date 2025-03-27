package ao.multaplus.sms.config;

import ao.it.mimo.api.exception.MimoException;
import ao.it.mimo.api.service.AutheticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${api.security.mimo.username}")
    private String username;
    @Value("${api.security.mimo.password}")
    private String password;
    @Bean(name = "mimo-token")
    String token() {
        AutheticationService autheticationService = new AutheticationService();
        try {
            return autheticationService.login(username, password);
        } catch (MimoException e) {
            throw new RuntimeException(e);
        }
    }
}
