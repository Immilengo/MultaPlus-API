package ao.multaplus.gender.service;

import ao.multaplus.gender.dtos.GenderDto;
import ao.multaplus.gender.dtos.GenderDtoList;
import ao.multaplus.gender.entity.Genders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenderService {
    public void migrate();

    public ResponseEntity<?> cadastra(GenderDto gender);
    public ResponseEntity<List<Genders>> lista();
    public ResponseEntity<?> edita(long id, GenderDto genders);
    public ResponseEntity<?> deleta(long id);
    public Optional<Genders> busca(long id);
}
