package ao.multaplus.vehicle.dtos;

public record RegisteVehicleDto(
    String registration,
    String plateNumber,
    Long vehicleTypeId,
    String bi
) {
}
