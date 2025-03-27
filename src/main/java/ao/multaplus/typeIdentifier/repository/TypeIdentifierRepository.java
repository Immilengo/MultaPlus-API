package ao.multaplus.typeIdentifier.repository;

import ao.multaplus.typeIdentifier.dtos.TipeIdentifierDto;
import ao.multaplus.typeIdentifier.entity.TypeIdentifiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeIdentifierRepository extends JpaRepository<TypeIdentifiers,String> {

    TipeIdentifierDto findById(long id);

    @Override
    List<TypeIdentifiers> findAll();

    TypeIdentifiers deleteById(long id);
}
