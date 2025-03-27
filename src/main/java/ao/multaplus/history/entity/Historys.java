package ao.multaplus.history.entity;

import ao.multaplus.action.entity.Actions;
import ao.multaplus.model.AbstractModel;
import ao.multaplus.state.entity.Status;
import ao.multaplus.typeIdentifier.entity.TypeIdentifiers;
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
public class Historys extends AbstractModel {

    private Long indentifer;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("historys")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "action_id")
    @JsonIgnoreProperties("historys")
    private Actions action;

    @ManyToOne
    @JoinColumn(name = "type_identifier_id")
    @JsonIgnoreProperties("historys")
    private TypeIdentifiers typeIdentifier;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("historys")
    private Status state;
}
