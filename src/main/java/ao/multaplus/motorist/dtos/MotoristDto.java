package ao.multaplus.motorist.dtos;

import java.io.Serializable;
import java.time.LocalDate;

public record MotoristDto(
        String name,
        LocalDate dateBirth,
        String telephone,
        String bi,
        Long genderId
) implements Serializable { }
