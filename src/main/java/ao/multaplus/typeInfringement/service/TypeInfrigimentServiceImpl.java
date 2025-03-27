package ao.multaplus.typeInfringement.service;

import ao.multaplus.state.entity.Status;
import ao.multaplus.state.repository.StatusRepository;
import ao.multaplus.typeInfringement.dtos.TypeInfringementsRequestdtos;
import ao.multaplus.typeInfringement.dtos.TypeInfringementsResponsedtos;
import ao.multaplus.typeInfringement.entity.TypeInfringements;
import ao.multaplus.typeInfringement.repository.TypeInfrigimentsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeInfrigimentServiceImpl implements TypeInfrigimentService {
    @Autowired
   private final TypeInfrigimentsRepository repository;
    @Autowired
    private final StatusRepository statusRepository;

    @Override
    public TypeInfringementsResponsedtos create(TypeInfringementsRequestdtos requestdtos) {
        Status status = statusRepository.findById(requestdtos.statusId().getId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found"));

        TypeInfringements infringement = new TypeInfringements();
        infringement.setType(requestdtos.type()); // Define o tipo a partir do DTO
        infringement.setDescription(requestdtos.description()); // Define a descrição a partir do DTO
        infringement.setPrice(Float.valueOf(requestdtos.price())); // Define o preço a partir do DTO
        infringement.setState(status); // Define o estado encontrado

        return toDto(repository.save(infringement));
    }

    @Override
    public TypeInfringementsResponsedtos findById(Long id) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));
        return toDto(infringement);
    }

    @Override
    public List<TypeInfringementsResponsedtos> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public TypeInfringementsResponsedtos update(Long id, TypeInfringementsRequestdtos requestdtos) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));
        Status status = statusRepository.findById(requestdtos.statusId().getId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found"));

        infringement.setType(infringement.getType());
        infringement.setDescription(infringement.getDescription());
        infringement.setPrice(infringement.getPrice());
        infringement.setState(status);

        return toDto(repository.save(infringement));
    }


    @Override
    public void delete(Long id) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));

        // Alterar o estado para "eliminado"
        Status eliminatedStatus = (Status) statusRepository.findById(3L)
                .orElseThrow(() -> new EntityNotFoundException("Status 'eliminated' not found"));

        infringement.setState(eliminatedStatus);
        repository.save(infringement);
    }

    @Override
    @PostConstruct
    public void migration() {
        if (repository.count() == 0) {
            String[] types = {
                    "Speeding",
                    "Parking in prohibited areas",
                    "Driving without a license",
                    "Running a red light",
                    "Failure to comply with internal regulations",
                    "Misuse of company resources",
                    "Non-compliance with deadlines",
                    "Improper waste disposal",
                    "Pollution of water bodies",
                    "Illegal deforestation",
                    "Tax evasion",
                    "Failure to issue invoices",
                    "False or incomplete declarations",
                    "Public disorder",
                    "Minor theft",
                    "Light assaults"
            };
            String[] description = {
                    "Exceeding the speed limit",
                    " Parking vehicles in prohibited areas",
                    " Driving a vehicle without a valid license",
                    "Failing to stop at a red traffic light",
                    " Violating internal organizational rules",
                    " Misusing resources for personal purposes",
                    " Missing established deadlines",
                    " Disposing of waste improperly",
                    " Contaminating rivers",
                    " lakes, or other water bodies",
                    " Clearing forests without authorization",
                    " Avoiding taxes by falsifying or omitting financial information",
                    " Not issuing required invoices in commercial transactions",
                    " Providing false or incomplete information in legal documents",
                    " Causing public disturbances or disorder",
                    " Committing minor thefts of property",
                    " Engaging in light physical assaults without serious injury"
            };


            for (String infringementType : types) {
                TypeInfringements infringement = new TypeInfringements();
                infringement.setType(infringementType);
                infringement.setDescription(infringementType);
                repository.save(infringement);
            }
        }
    }

    private TypeInfringementsResponsedtos toDto(TypeInfringements infringement) {
        return new TypeInfringementsResponsedtos(
                infringement.getId(),
                infringement.getType(),
                infringement.getDescription(),
                infringement.getPrice(),
                infringement.getState()
        );
    }
}
