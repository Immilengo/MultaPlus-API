package ao.multaplus.vehicle.repository;

import ao.multaplus.vehicle.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Long> {
    @Query("SELECT v FROM Vehicles v WHERE v.plateNumber " + "=:vehicleIdentifier OR v" +
            ".registration = :vehicleIdentifier OR v.motorist.bi= :vehicleIdentifier")
    Optional<Vehicles> findVehicles(@Param("vehicleIdentifier") String vehicleIdentifier);


}
