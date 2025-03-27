package ao.multaplus.statePayment.repository;

import ao.multaplus.statePayment.entity.StatusPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusPaymentRepository extends JpaRepository<StatusPayment,Long> {

    Optional<StatusPayment> findById(Long id);

    StatusPayment findById(long id);

    Optional<Object> findByState(String eliminated);

    List<StatusPayment> findAll();
}
