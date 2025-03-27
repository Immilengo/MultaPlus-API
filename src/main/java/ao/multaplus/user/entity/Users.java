package ao.multaplus.user.entity;

import ao.multaplus.auth.entity.Auth;
import ao.multaplus.gender.entity.Genders;
import ao.multaplus.model.AbstractModel;
import ao.multaplus.province.entity.Provinces;
import ao.multaplus.state.entity.Status;
import ao.multaplus.typeUser.entity.TypeUsers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Users extends AbstractModel {

    @Column(nullable = false)
    @NotBlank(message = "Enter a name")
    private String name;

    @Column(columnDefinition = "timestamp")
    private LocalDate dateBirth;



    @Column(unique = true)
    private String bi;

    private String img;

    @Column(unique = true)
    private String nIdentification;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    @JsonIgnoreProperties("users")
    private Genders genders;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("users")
    private Status state;

    @ManyToOne
    @JoinColumn(name = "province_id")
    @JsonIgnoreProperties("users")
    private Provinces province;

    @ManyToOne
    @JoinColumn(name = "type_user_id")
    @JsonIgnoreProperties("users")
    private TypeUsers typeUsers;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "login_id")
    @JsonIgnoreProperties("users")
    private Auth login;


}
