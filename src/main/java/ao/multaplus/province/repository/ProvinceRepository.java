package ao.multaplus.province.repository;

import ao.multaplus.province.entity.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Provinces, Long> {
    Optional<Provinces> findById(Long id);

    boolean existsByProvince(String province);
    boolean existsByProvinceAndIdNot(String province, Long id);
}
