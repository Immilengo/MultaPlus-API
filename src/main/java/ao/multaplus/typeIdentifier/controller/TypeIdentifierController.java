package ao.multaplus.typeIdentifier.controller;

import ao.multaplus.typeIdentifier.dtos.TipeIdentifierDto;
import ao.multaplus.typeIdentifier.dtos.TipeidentifierSaveDTO;
import ao.multaplus.typeIdentifier.entity.TypeIdentifiers;
import ao.multaplus.typeIdentifier.service.TypeIdentifierServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/typeidentifier")
@RestController
public class TypeIdentifierController {
    private final TypeIdentifierServiceImpl identifierService;
    public TypeIdentifierController(TypeIdentifierServiceImpl identifierService){
        this.identifierService=identifierService;
    }

    @Operation(description = "List All typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return identifierService.lista();
    }

    @Operation(description = "Find a typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/listar/{id}")
    public TipeIdentifierDto buscar(@PathVariable long id){
        return identifierService.busca(id);
    }

    @Operation(description = "Save typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody TipeidentifierSaveDTO saveDTO){
        return identifierService.cadastra(saveDTO);
    }

    @Operation(description = "Edit typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id,@RequestBody TipeidentifierSaveDTO saveDTO){
        return identifierService.edita(id,saveDTO);
    }

    @Operation(description = "Delete typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
      return identifierService.deleta(id);
    }
}
