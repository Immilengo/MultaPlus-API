package ao.multaplus.state.controller;

import ao.multaplus.state.dtos.StateDto;
import ao.multaplus.state.dtos.StateSaveDto;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.service.StatusServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@org.springframework.stereotype.Controller("statusController")

@RequestMapping("/api/status")
@RestController
public class StatusController {
    @Autowired
    public StatusServiceImpl statusService;

    @Operation(description = "List All Status", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return statusService.listar();
    }

    @Operation(description = "List Status By ID", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/listar/{id}")
    public Optional<Status> listbyid(@PathVariable long id){
        return statusService.busca(id);
    }


    @Operation(description = "Save New Status", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody StateSaveDto state){
        return statusService.cadastrar(state);
    }

    @Operation(description = "Update Status", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id,@RequestBody StateSaveDto state){
        return statusService.editar(id,state);
    }

    @Operation(description = "Update Status", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        return statusService.deletar(id);
    }

}