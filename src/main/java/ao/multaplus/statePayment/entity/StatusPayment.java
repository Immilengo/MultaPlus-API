package ao.multaplus.statePayment.entity;

import ao.multaplus.model.AbstractModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StatusPayment extends AbstractModel {

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Enter a Status")
    private String state;

    private String description;
}
