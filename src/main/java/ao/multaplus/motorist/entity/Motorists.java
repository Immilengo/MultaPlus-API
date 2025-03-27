package ao.multaplus.motorist.entity;

import ao.multaplus.gender.entity.Genders;
import ao.multaplus.model.AbstractModel;
import ao.multaplus.state.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Motorists extends AbstractModel {
    @Column(nullable = false)
    @NotBlank(message = "Enter a name")
    private String name;

    @Column(columnDefinition = "timestamp")
    private LocalDate dateBirth;

    @Column(unique = true)
    private String telephone;

    private String bi;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    @JsonIgnoreProperties("users")
    private Genders gender;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("users")
    private Status state;
}
