package ao.multaplus.typeUser.response;

import ao.multaplus.state.dtos.StateSenderDto;

public record TypeUsersResponse(
        Long id,
        String type,
        String description,
        StateSenderDto state
) {
}
