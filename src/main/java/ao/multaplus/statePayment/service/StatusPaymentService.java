package ao.multaplus.statePayment.service;

import ao.multaplus.statePayment.dtos.StatusPaymentDTO;
import ao.multaplus.statePayment.dtos.StatusPaymentSaveDTO;
import org.springframework.http.ResponseEntity;

public interface StatusPaymentService {
    public ResponseEntity<?> listar();

    public ResponseEntity<?> deletar(long id);

    public ResponseEntity<?> editar(long id, StatusPaymentSaveDTO state);

    public ResponseEntity<?> cadastrar(StatusPaymentSaveDTO state);

    public ResponseEntity<?> buscar(long id);
}
