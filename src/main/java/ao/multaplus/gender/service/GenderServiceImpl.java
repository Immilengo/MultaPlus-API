package ao.multaplus.gender.service;

import ao.multaplus.gender.dtos.GenderDto;
import ao.multaplus.gender.dtos.GenderDtoList;
import ao.multaplus.gender.entity.Genders;
import ao.multaplus.gender.repository.GenderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService {

    @Override
    @PostConstruct
    public void migrate(){
        if(genderRepository.count()==0){
            String[] genero={"Feminino","Masculino"};

            for (int i=0;i< genero.length;i++){
                Genders genders=new Genders();
                genders.setGender(genero[i]);
                genderRepository.save(genders);
            }
        }
    }

    final private GenderRepository genderRepository;
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public ResponseEntity<?> cadastra(GenderDto gender) {
        if (gender==null){
            return new ResponseEntity<>("Os campos têm que ser preenchidos",HttpStatus.OK);
        }
        Genders genders=new Genders();
        genders.setGender(gender.gender());
        return new ResponseEntity<>(genderRepository.save(genders),HttpStatus.CREATED) ;
    }

    @Override
    public ResponseEntity<List<Genders>> lista() {
        return new ResponseEntity<>(genderRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> edita(long id, GenderDto genders) {
        Optional<Genders> gender= Optional.of(new Genders());
        gender=genderRepository.findById(id);
        Genders ge=new Genders();
        ge=gender.get();
        ge.setId(id);
        gender.orElseThrow().setGender(genders.gender());
        genderRepository.save(ge);
        return new ResponseEntity<>("Dados Editado com sucesso",HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> deleta(long id) {
        Optional<Genders> genders=genderRepository.findById(id);
        if (genders==null){
            return new ResponseEntity<>("Registro não encontrado",HttpStatus.OK);
        }
        Genders genders1=new Genders();
        genders1.setId(id);
        genders1.setGender(genders.orElseThrow().getGender());
        genderRepository.delete(genders1);
        return new ResponseEntity<>("Deletado com sucesso",HttpStatus.OK);
    }

    @Override
    public Optional<Genders> busca(long id) {
        return genderRepository.findById(id);
    }
}
