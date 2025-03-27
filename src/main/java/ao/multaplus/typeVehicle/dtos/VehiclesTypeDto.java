package ao.multaplus.typeVehicle.dtos;

import java.io.Serializable;

public record VehiclesTypeDto(Long statusId,String type, String description) implements Serializable {
}
