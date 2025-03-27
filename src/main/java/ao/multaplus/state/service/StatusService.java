package ao.multaplus.state.service;

import ao.multaplus.state.dtos.StateDto;
import ao.multaplus.state.dtos.StateSaveDto;
import ao.multaplus.state.entity.Status;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface StatusService {


    void migration();

    public ResponseEntity<?> listar();

    public ResponseEntity<?> editar(long id, StateSaveDto state);

    public ResponseEntity<?> cadastrar(StateSaveDto state);

    public Optional<Status> busca(long id);
    public ResponseEntity<?> deletar(long id);
}
