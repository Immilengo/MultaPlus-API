package ao.multaplus.typeInfringement.entity;

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
public class TypeInfringements extends AbstractModel {

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Enter a Type Infringements")
    private String type;

    private String description;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("typeInfringements")
    private Status state;
}
