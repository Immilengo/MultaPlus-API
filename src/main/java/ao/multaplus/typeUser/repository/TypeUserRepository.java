package ao.multaplus.typeUser.repository;

import ao.multaplus.typeUser.entity.TypeUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeUserRepository extends JpaRepository<TypeUsers,Long> {

    Optional<TypeUsers> findById(Long id);
    boolean existsByType(String type);

    boolean existsByTypeAndIdNot(String type, Long id);

}
