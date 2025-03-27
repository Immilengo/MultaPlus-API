package ao.multaplus.vehicle.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.motorist.entity.Motorists;

import ao.multaplus.state.entity.Status;
import ao.multaplus.typeVehicle.entity.TypeVehicles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicles extends AbstractModel {
    private String registration;
    @Column(nullable = false, unique = true)
    private String plateNumber;
    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("vehicles")
    private Status state;

    @ManyToOne
    @JoinColumn(name = "type_vehicle_id")
    @JsonIgnoreProperties("vehicles")
    private TypeVehicles typeVehicles;

    @ManyToOne
    @JoinColumn(name = "motorist_id")
    @JsonIgnoreProperties("vehicles")
    private Motorists motorist;
}
