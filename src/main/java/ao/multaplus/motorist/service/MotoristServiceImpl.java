package ao.multaplus.motorist.service;

import ao.multaplus.motorist.dtos.MotoristDto;
import ao.multaplus.motorist.entity.Motorists;
import ao.multaplus.motorist.repository.MotoristRepository;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.repository.StatusRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MotoristServiceImpl implements MotoristService {
    private final MotoristRepository motoristRepository;
    private final StatusRepository statusRepository;

    @Override
    @Transactional
    public void registerMotorist(MotoristDto motoristDto) {
        try {
            Motorists motorist = Motorists.builder().
                    bi(motoristDto.bi()).
                    gender(null)
                    .dateBirth(motoristDto.dateBirth())
                    .state(getStatus(1L))
                    .telephone(motoristDto.telephone())
                    .name(motoristDto.name()).
                    build();
            motoristRepository.save(motorist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Motorists getMotorist(String motoristIdentifier) {
        return findMotorist(motoristIdentifier).orElseThrow(
                () -> new RuntimeException("Motorist not found"));
    }

    @Override
    @Transactional
    public void updateMotorist(String motoristIdentifier, MotoristDto motoristDto) {
        findMotorist(motoristIdentifier).ifPresentOrElse(motorists -> {
            boolean updated = false;
            if (
                    motoristDto.name() != null && !motoristDto.name().equals(
                            motorists.getName())) {
                motorists.setName(motoristDto.name());
                updated = true;
            }
            if (motoristDto.dateBirth() != null && !motoristDto.dateBirth().equals(motorists.getDateBirth())) {
                motorists.setDateBirth(motoristDto.dateBirth());
                updated = true;
            }
            if (motoristDto.telephone() != null && !motoristDto.telephone().equals(
                    motorists.getTelephone())) {
                motorists.setTelephone(motoristDto.telephone());
                updated = true;
            }
            if(motoristDto.bi() != null && !motoristDto.bi().equals(motorists.getBi())){
                motorists.setBi(motoristDto.bi());
                updated = true;
            }
            if (updated)
                motoristRepository.save(motorists);
        }, () -> {
            throw new RuntimeException("Motorist not found");
        });
    }

    @Override
    @Transactional
    public void deleteMotorist(String motoristIdentifier) {
        findMotorist(motoristIdentifier).ifPresentOrElse(motorists -> {
            if (motorists.getState().getId().equals(3L))
                throw new RuntimeException("Motorist already deleted");
            motorists.setState(getStatus(3L));
            motoristRepository.save(motorists);
        }, () -> {
            throw new RuntimeException("Motorist not found");
        });
    }

    public Optional<Motorists> findMotorist(String motoristIdentifier) {
        return motoristRepository.findByBi(motoristIdentifier);
    }
    // remove this method and use the one from the StatusServiceImpl class when you are
    // done.
    private Status getStatus(Long statusId) {
        return statusRepository.findById(statusId).orElseThrow(
                () -> new RuntimeException("Status not found"));
    }
}
