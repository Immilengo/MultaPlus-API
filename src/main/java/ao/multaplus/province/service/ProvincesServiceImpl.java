package ao.multaplus.province.service;

import ao.multaplus.province.dtos.ProviceUpdateDto;
import ao.multaplus.province.dtos.ProvinceDto;
import ao.multaplus.province.response.ProvinceResponse;
import ao.multaplus.province.entity.Provinces;
import ao.multaplus.province.repository.ProvinceRepository;
import ao.multaplus.state.dtos.StateSenderDto;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvincesServiceImpl implements ProvincesService {
    @Autowired
    private final ProvinceRepository repository;
    @Autowired
    private final StatusRepository statusRepository;


    @Override
    public ProvinceResponse create(ProvinceDto requestdtos) {
        if (repository.existsByProvince(requestdtos.province())){
            throw new RuntimeException("Province already exists");
        }

        Status state = statusRepository.findById(1L)
                .orElse(null);
        Provinces province = new Provinces();
        province.setProvince(requestdtos.province());
        province.setState(state);

        repository.save(province);
        return toResponseDTO(province);
    }

    @Override
    public ProvinceResponse update(Long id, ProviceUpdateDto requestdtos) {
        Provinces existingProvince = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Province not found"));
        if (repository.existsByProvince(requestdtos.province())){
            throw new RuntimeException("Another province with the same name already exists!");
        }

        Status state = statusRepository.findById(requestdtos.state().id())
                .orElse(null);

        existingProvince.setState(state);
        existingProvince.setProvince(requestdtos.province());
        repository.save(existingProvince);
        return toResponseDTO(existingProvince);
    }

    @Override
    public void delete(Long id) {
        Provinces province = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Province not found"));
        // Buscar o estado correspondente a "eliminated"
        Status deletedStatus = statusRepository.findById(3L) // Substitua 3L pelo ID de "eliminated"
                .orElseThrow(() -> new RuntimeException("Deleted state not found"));
        // Atualizar o estado da província
        province.setState(deletedStatus);
        // Salvar as alterações
        repository.save(province);
    }

    @Override
    public ProvinceResponse findById(Long id) {
        Provinces province = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Province not found"));

        return toResponseDTO(province);
    }

    @Override
    public List<ProvinceResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }


        //metodo para a response
    private ProvinceResponse toResponseDTO(Provinces province) {
        return new ProvinceResponse(
                province.getId(),
                province.getProvince(),
                new StateSenderDto(province.getState().getId())
        );
    }
    @Override
    @PostConstruct
    public void migration() {
        if (repository.count() == 0) {
            String[] array = {"Bengo", "Benguela", "Bié",
                    "Cabinda", "Cuando", "Cubango", "Cuanza Norte",
                    "Cuanza Sul", "Cunene", "Huambo", "Huíla", "Luanda",
                    "Lunda Norte", "Lunda Sul", "Malanje", "Moxico",
                    "Namibe", "Uíge", "Zaire", "Icolo e Bengo", "Moxico Leste", "Cuando"};

            for (int i = 0; i < array.length; i++) {
                Provinces province = new Provinces();
                province.setProvince(array[i]);
                repository.save(province);
            }
        }
    }
}
