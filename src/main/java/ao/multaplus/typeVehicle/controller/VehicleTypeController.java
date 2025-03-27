package ao.multaplus.typeVehicle.controller;

import ao.multaplus.typeVehicle.dtos.VehiclesTypeDto;
import ao.multaplus.typeVehicle.entity.TypeVehicles;
import ao.multaplus.typeVehicle.service.TypeVehicleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type-vehicle")
@Tag(name = "Vehicle type", description = "Vehicle Type endpoints")
@RequiredArgsConstructor
public class VehicleTypeController {
    private final TypeVehicleServiceImpl typeVehicleService;

    @PostMapping
    @Operation(summary = "Register vehicle type", description = "Register vehicle type")
    public ResponseEntity<TypeVehicles> registerVehicleType(
            @RequestBody VehiclesTypeDto VehiclesTypeDto) {
         return ResponseEntity.status(HttpStatus.CREATED).body(typeVehicleService.addTypeVehicles(VehiclesTypeDto));
    }

    @DeleteMapping("{typeVehiclesIdentifiers}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete vehicle type", description = "Delete vehicle type")
    public void deleteVehicleType(@PathVariable long typeVehiclesIdentifiers) {
        typeVehicleService.deleteTypeVehicles(typeVehiclesIdentifiers);
    }

    @PutMapping("{typeVehiclesIdentifiers}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update vehicle type", description = "Update vehicle type")
    public void updateVehicleType(@PathVariable long typeVehiclesIdentifiers,
                                  @RequestBody VehiclesTypeDto VehiclesTypeDto) {
        typeVehicleService.updateTypeVehicles(typeVehiclesIdentifiers,
                VehiclesTypeDto);
    }

    @GetMapping("{typeVehiclesIdentifiers}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get vehicle type", description = "Get vehicle type")
    public TypeVehicles getVehicleType(@PathVariable long typeVehiclesIdentifiers) {
        return typeVehicleService.getTypeVehicles(typeVehiclesIdentifiers);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all vehicle types", description = "Get all vehicle types")
    public List<TypeVehicles> getAllVehicleType() {
        return typeVehicleService.getAllTypeVehicles();
    }
}
