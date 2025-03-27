package ao.multaplus.payment.entity;

import ao.multaplus.fine.entity.Fines;
import ao.multaplus.model.AbstractModel;
import ao.multaplus.statePayment.entity.StatusPayment;
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
public class Payments extends AbstractModel {

    @Column(nullable = false)
    @NotBlank(message = "Enter a Reference")
    private String reference;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("payments")
    private StatusPayment statusPayment;

    @ManyToOne
    @JoinColumn(name = "fine_id")
    @JsonIgnoreProperties("payments")
    private Fines fine;

    private Float price;


}
