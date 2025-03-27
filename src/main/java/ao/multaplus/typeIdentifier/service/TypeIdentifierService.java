package ao.multaplus.typeIdentifier.service;

import ao.multaplus.typeIdentifier.dtos.TipeIdentifierDto;
import ao.multaplus.typeIdentifier.dtos.TipeidentifierSaveDTO;
import org.springframework.http.ResponseEntity;

public interface TypeIdentifierService {
    public ResponseEntity<?> lista();
    public TipeIdentifierDto busca(long id);
    public ResponseEntity<?> cadastra(TipeidentifierSaveDTO saveDTO);
    public ResponseEntity<?> edita(long id,TipeidentifierSaveDTO saveDTO);
    public ResponseEntity<?> deleta(long id);
}
