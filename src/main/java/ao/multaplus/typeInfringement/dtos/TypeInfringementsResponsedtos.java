package ao.multaplus.typeInfringement.dtos;

import ao.multaplus.state.entity.Status;

public record TypeInfringementsResponsedtos(
        Long id,
        String type,
        String description,
        Float price,
        Status stateId
) {
}
