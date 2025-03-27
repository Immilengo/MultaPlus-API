package ao.multaplus.auth.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.role.entity.Roles;
import ao.multaplus.state.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auth extends AbstractModel implements UserDetails {

    @Column(nullable = false, unique = true)
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Enter a email")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Enter a password")
    private String password;
    @Column(unique = true)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("auths")
    private Status state;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties("auths")
    private Roles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role.getRole().equals("admin")) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }
    @Override
    public String getUsername() {
        return email;
    }

}
