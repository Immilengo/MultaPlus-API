package ao.multaplus.typeVehicle.service;

import ao.multaplus.typeVehicle.dtos.VehiclesTypeDto;
import ao.multaplus.typeVehicle.entity.TypeVehicles;

import java.util.List;

public interface TypeVehiclesService {
     TypeVehicles addTypeVehicles(VehiclesTypeDto typeVehicles);
     void updateTypeVehicles( Long typeVehiclesIdentifiers, VehiclesTypeDto typeVehicles);
     void deleteTypeVehicles(Long typeVehiclesIdentifiers);
     TypeVehicles getTypeVehicles(Long typeVehiclesIdentifiers);
     List<TypeVehicles> getAllTypeVehicles();
}
