package ao.multaplus.gender.controller;

import ao.multaplus.gender.dtos.GenderDto;
import ao.multaplus.gender.dtos.GenderDtoList;
import ao.multaplus.gender.entity.Genders;
import ao.multaplus.gender.service.GenderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/gender")
public class GenderController {
    private final GenderServiceImpl genderService;
    public GenderController(GenderServiceImpl genderService) {
        this.genderService = genderService;
    }

    @Operation(description = "List All gender", tags = "gender")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/listar")
    public ResponseEntity<?> listar(GenderDtoList genderDtoList){
        return genderService.lista();
    }

    @Operation(description = "Find a gender", tags = "gender")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/listar/{id}")
    public Optional<Genders> buscar(@PathVariable long id){
        return genderService.busca(id);
    }

    @Operation(description = "Save a gender", tags = "gender")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody GenderDto genderDto){
        return genderService.cadastra(genderDto);
    }

    @Operation(description = "Delete a gender", tags = "gender")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        return genderService.deleta(id);
    }

    @Operation(description = "Edit a gender", tags = "gender")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id,@RequestBody GenderDto genderDtoList){
        return genderService.edita(id,genderDtoList);
    }
}
