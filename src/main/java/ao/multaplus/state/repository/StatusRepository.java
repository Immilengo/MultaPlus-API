package ao.multaplus.state.repository;

import ao.multaplus.state.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Long> {
    Optional<Status> findById(Long id);

    Optional<Object> findByState(String eliminated);

    List<Status> findAll();

     //Status findById(long id);
    Optional<Status> findById(long id);
}
