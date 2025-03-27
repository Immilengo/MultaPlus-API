package ao.multaplus.province.entity;

import ao.multaplus.model.AbstractModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import ao.multaplus.state.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Provinces extends AbstractModel {

    @Column(nullable = false)
    @NotBlank(message = "Enter a Genders")
    private String province;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("provinces")
    private Status state;
}