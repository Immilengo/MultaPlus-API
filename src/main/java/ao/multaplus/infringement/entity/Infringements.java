package ao.multaplus.infringement.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.state.entity.Status;
import ao.multaplus.typeInfringement.entity.TypeInfringements;
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
public class Infringements extends AbstractModel {

    @Column(nullable = false)
    @NotBlank(message = "Enter a Status")
    private String infringement;

    private String description;

    @ManyToOne
    @JoinColumn(name = "type_infringement_id")
    @JsonIgnoreProperties("infringements")
    private TypeInfringements typeInfringements;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("infringements")
    private Status status;
}
