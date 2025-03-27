package ao.multaplus.fineInfringement.entity;

import ao.multaplus.fine.entity.Fines;
import ao.multaplus.infringement.entity.Infringements;
import ao.multaplus.model.AbstractModel;
import ao.multaplus.state.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FineInfringements extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "fine_id")
    @JsonIgnoreProperties("fineInfringements")
    private Fines fine;

    @ManyToOne
    @JoinColumn(name = "infringement_id")
    @JsonIgnoreProperties("fineInfringements")
    private Infringements infringement;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("fineInfringements")
    private Status state;

}
