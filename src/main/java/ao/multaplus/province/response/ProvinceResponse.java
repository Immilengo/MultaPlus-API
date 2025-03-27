package ao.multaplus.province.response;

import ao.multaplus.state.dtos.StateSenderDto;

public record ProvinceResponse(
        Long id,
        String province,
        StateSenderDto state
) {

}
