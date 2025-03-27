package ao.multaplus.province.dtos;

import ao.multaplus.state.dtos.StateSenderDto;

public record ProviceUpdateDto(
        Long id,
        String province,
        StateSenderDto state
) {
}
