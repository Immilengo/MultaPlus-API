package ao.multaplus.action.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.state.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Actions extends AbstractModel {

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Enter a Action")
    private String action;

    private String description;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("actions")
    private Status state;
}
