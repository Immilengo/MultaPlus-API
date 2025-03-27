package ao.multaplus.vehicle.service;

import ao.multaplus.vehicle.dtos.RegisteVehicleDto;
import ao.multaplus.vehicle.entity.Vehicles;

public interface VehicleService {
    void registerVehicle( RegisteVehicleDto registeVehicleDto);
    void updateVehicle(String plate,RegisteVehicleDto vehicle);
    void removeVehicle(String plate);
    Vehicles getVehicle(String plate);

}
