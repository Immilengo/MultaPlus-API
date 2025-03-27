package ao.multaplus.typeInfringement.dtos;

import ao.multaplus.state.entity.Status;

public record TypeInfringementsRequestdtos(
        String type,
        String description,
        Long price,
        Status statusId
) {
}
