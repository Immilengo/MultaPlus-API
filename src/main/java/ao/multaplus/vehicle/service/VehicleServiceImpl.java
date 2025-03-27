package ao.multaplus.vehicle.service;

import ao.multaplus.motorist.service.MotoristServiceImpl;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.repository.StatusRepository;
import ao.multaplus.typeVehicle.entity.TypeVehicles;
import ao.multaplus.typeVehicle.service.TypeVehicleServiceImpl;
import ao.multaplus.vehicle.dtos.RegisteVehicleDto;
import ao.multaplus.vehicle.entity.Vehicles;
import ao.multaplus.vehicle.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    private final MotoristServiceImpl motoristService;
    private final StatusRepository statusRepository;
    private final TypeVehicleServiceImpl typeVehicleService;

    @Override
    @Transactional
    public void registerVehicle(RegisteVehicleDto registeVehicleDto) {
        TypeVehicles typeVehicles =
                typeVehicleService.getTypeVehicles(registeVehicleDto.vehicleTypeId());
        Status status = getStatus(1L);
        motoristService.findMotorist(registeVehicleDto.bi()).ifPresentOrElse(motorists -> {
            Vehicles vehicle = Vehicles.builder()
                    .motorist(motorists)
                    .plateNumber(registeVehicleDto.plateNumber())
                    .registration(registeVehicleDto.registration())
                    .typeVehicles(typeVehicles)
                    .state(status)
                    .build();
            vehicleRepository.save(vehicle);
        }, () -> {
            throw new RuntimeException("user not found");
        });
    }

    @Override
    @Transactional
    public void updateVehicle(String plate, RegisteVehicleDto vehicle) {
        vehicleRepository.findVehicles(plate).ifPresentOrElse(v -> {
                    boolean updated = false;
                  if (vehicle.vehicleTypeId()!= null && !vehicle.vehicleTypeId().equals(v.getTypeVehicles().getId())) {
                        v.setTypeVehicles(typeVehicleService.getTypeVehicles(vehicle.vehicleTypeId()));
                        updated = true;
                    }
                    if (!plate.equals(v.getPlateNumber())) {
                        v.setPlateNumber(plate);
                        updated = true;
                    }
                    if (!v.getMotorist().getBi().equals(vehicle.bi())) {
                        var owner = motoristService.findMotorist(vehicle.bi()).orElseThrow();
                        v.setMotorist(owner);
                        updated = true;
                    }
                    if(vehicle.vehicleTypeId()!= null && !vehicle.vehicleTypeId().equals(v.getTypeVehicles().getId())){
                        v.setTypeVehicles(typeVehicleService.getTypeVehicles(vehicle.vehicleTypeId()));
                        updated = true;
                    }
                    if (updated)
                        vehicleRepository.save(v);
                },
                () -> {
                    throw new RuntimeException("update-ve");
                });
    }

    @Override
    @Transactional
    public void removeVehicle(String plate) {
        vehicleRepository.findVehicles(plate).ifPresentOrElse(
                vehicles -> {
                  if(vehicles.getState().getId().equals(3L))
                        throw new RuntimeException("vehicle already deleted");
                   vehicles.setState(getStatus(3L));
                    vehicleRepository.save(vehicles);
                },
                () -> {
                    throw new RuntimeException("vehicle not found");
                });
    }

    @Override
    public Vehicles getVehicle(String plate) {
        return vehicleRepository.findVehicles(plate).orElseThrow(
                () -> new RuntimeException("vehicle not found"));
    }
//    Note: remove this method and use the implementation from the StatusServiceImpl
//    class, when the StatusServiceImpl class is available.
    private Status getStatus(Long statusId) {
        return statusRepository.findById(statusId).orElseThrow(
                () -> new RuntimeException("Status not found"));
    }
}
