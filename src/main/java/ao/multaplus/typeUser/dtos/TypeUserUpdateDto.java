package ao.multaplus.typeUser.dtos;

import ao.multaplus.state.dtos.StateSenderDto;

public record TypeUserUpdateDto (
        String type,
        String description,
        StateSenderDto state
) {}