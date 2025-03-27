package ao.multaplus.motorist.repository;

import ao.multaplus.motorist.entity.Motorists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristRepository extends JpaRepository<Motorists, Long> {
    Optional<Motorists> findByBi(String bi);
}
