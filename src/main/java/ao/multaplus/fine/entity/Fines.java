package ao.multaplus.fine.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.motorist.entity.Motorists;
import ao.multaplus.state.entity.Status;
import ao.multaplus.user.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fines extends AbstractModel {

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("fines")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "motorist_id")
    @JsonIgnoreProperties("fines")
    private Motorists motorist;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("fines")
    private Status state;


}
