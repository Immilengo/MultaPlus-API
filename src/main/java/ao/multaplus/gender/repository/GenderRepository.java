package ao.multaplus.gender.repository;

import ao.multaplus.gender.dtos.GenderDto;
import ao.multaplus.gender.entity.Genders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Genders,Integer> {

    Optional<Genders> findById(long integer);

    @Override
    List<Genders> findAll();

    void deleteById(long id);
}
