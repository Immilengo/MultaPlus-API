package ao.multaplus.typeUser.service;

import ao.multaplus.state.dtos.StateSenderDto;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.repository.StatusRepository;
import ao.multaplus.typeUser.dtos.TypeUserUpdateDto;
import ao.multaplus.typeUser.dtos.TypeUsersDto;
import ao.multaplus.typeUser.response.TypeUsersResponse;
import ao.multaplus.typeUser.repository.TypeUserRepository;
import ao.multaplus.typeUser.entity.TypeUsers;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeUserServiceImpl implements TypeUserService {

    @Autowired
    private TypeUserRepository repository;
    @Autowired
    private final StatusRepository statusRepository;

    @Override
    public TypeUsersResponse create(TypeUsersDto request) {
        Status state = statusRepository.findById(1L)
                .orElse(null);

        TypeUsers typeUsers = new TypeUsers();
        typeUsers.setType(request.type());
        typeUsers.setDescription(request.description());
        typeUsers.setState(state);

        typeUsers = repository.save(typeUsers);
        return toResponseDTO(typeUsers);
    }

    @Override
    public TypeUsersResponse update(Long id, TypeUserUpdateDto request) {
        TypeUsers existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TypeUser not found"));

        Status state = statusRepository.findById(request.state().id())
                .orElse(null);

        existingUser.setType(request.type());
        existingUser.setDescription(request.description());
        existingUser.setState(state);

        existingUser = repository.save(existingUser);
        return toResponseDTO(existingUser);
    }

    @Override
    public void delete(Long id) {
        TypeUsers existingUser = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeUser not found"));

        // Alterar o estado para "eliminado"
        Status deletedStatus = statusRepository.findById(3l) // Substitua 3L pelo ID correspondente ao estado "eliminado"
                .orElseThrow(() -> new EntityNotFoundException("Status 'eliminated' not found"));

        existingUser.setState(deletedStatus);
        repository.save(existingUser);
    }


    @Override
    public List<TypeUsersResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public TypeUsersResponse getById(Long id) {
        TypeUsers typeUsers = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TypeUser not found"));
        return toResponseDTO(typeUsers);
    }

    private TypeUsersResponse toResponseDTO(TypeUsers typeUsers) {
        return new TypeUsersResponse(
                typeUsers.getId(),
                typeUsers.getType(),
                typeUsers.getDescription(),
                new StateSenderDto(typeUsers.getState().getId())
        );
    }

    @Override
    public void migration() {
        if (repository.count() == 0) {
            String[] array = {
                    "admin",
                    "Traffic regulatory agent",
                    "Traffic agent",
                    "Traffic supervisor",
                    "Regular driver",
                    "Professional driver",
                    "Highway patrol",
                    "Traffic judge",
                    "Treasurer",
                    "Technical support"
            };

            for (int i = 0; i < array.length; i++) {
                TypeUsers typeUser = new TypeUsers();
                typeUser.setType(array[i]);
                repository.save(typeUser);
            }
        }
    }
}
