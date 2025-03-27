package ao.multaplus.statePayment.controller;

import ao.multaplus.statePayment.dtos.StatusPaymentDTO;
import ao.multaplus.statePayment.dtos.StatusPaymentSaveDTO;
import ao.multaplus.statePayment.entity.StatusPayment;
import ao.multaplus.statePayment.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statuspayment")
public class StatusPaymentController {

    @Autowired
    public Service statusPayment;

    @Operation(description = "List All StatusPayment", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping
    public ResponseEntity<?> listar(){
        return statusPayment.listar();
    }

    @Operation(description = "List All StatusPayment By ID", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/{id}")
    public ResponseEntity<?> listarid(@PathVariable long id){
        return statusPayment.buscar(id);
    }

    @Operation(description = "Save New Status", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody StatusPaymentSaveDTO state){
        return statusPayment.cadastrar(state);
    }

    @Operation(description = "Update Status", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable long id, @RequestBody StatusPaymentSaveDTO state){
        return statusPayment.editar(id,state);
    }

    @Operation(description = "Delete StatusPayment", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        return statusPayment.deletar(id);
    }
}
