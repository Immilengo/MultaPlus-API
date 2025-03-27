package ao.multaplus.vehicle.controller;

import ao.multaplus.vehicle.dtos.RegisteVehicleDto;
import ao.multaplus.vehicle.entity.Vehicles;
import ao.multaplus.vehicle.service.VehicleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@Tag(name = "Vehicle", description = "Vehicle endpoints")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleServiceImpl vehicleService;

    @PostMapping
    @Operation(summary = "Register a new vehicle", description = "Register a new vehicle")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerVehicle(@RequestBody RegisteVehicleDto registeVehicleDto) {
        vehicleService.registerVehicle(registeVehicleDto);
    }
    @PutMapping("{plate}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVehicle(@PathVariable String plate,
                              @RequestBody RegisteVehicleDto vehicle) {
        vehicleService.updateVehicle(plate, vehicle);
    }
    @DeleteMapping("{plate}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable String plate) {
        vehicleService.removeVehicle(plate);
    }
    @GetMapping("{plate}")
    public Vehicles getVehicle(@PathVariable String plate) {
       return vehicleService.getVehicle(plate);
    }

    
}
