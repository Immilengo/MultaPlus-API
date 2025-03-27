package ao.multaplus.typeInfringement.repository;

import ao.multaplus.typeInfringement.entity.TypeInfringements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeInfrigimentsRepository extends JpaRepository<TypeInfringements ,Long> {

}
