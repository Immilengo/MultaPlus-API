package ao.multaplus.province.controller;

import ao.multaplus.province.dtos.ProviceUpdateDto;
import ao.multaplus.province.dtos.ProvinceDto;
import ao.multaplus.province.response.ProvinceResponse;
import ao.multaplus.province.service.ProvincesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
@SuppressWarnings("unused")
@RequiredArgsConstructor
@Tag(name = "Provinces", description = "Manege Provinces")
public class ProvinceController {
    @Autowired
    private final ProvincesService provincesService;
    @Operation(summary = "Get all provinces", description = "Get a list of Provinces")
    @ApiResponse(responseCode = "200", description = "List Return Sucessfuly")
    @GetMapping
    public ResponseEntity <List<ProvinceResponse>> findAll(){
        return ResponseEntity.ok(provincesService.findAll());
    }
    @Operation(summary = "Get a Province By ID", description = "Recupera uma província específica com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Província encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Província não encontrada")
    })
    @GetMapping("{id}")
    public ResponseEntity <ProvinceResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(provincesService.findById(id));
    }
    @Operation(summary = "Criar uma nova província", description = "Adiciona uma nova província ao sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Província criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<ProvinceResponse> create(@RequestBody  @Valid ProvinceDto requestdtos){
        return ResponseEntity.status(HttpStatus.CREATED).body(provincesService.create(requestdtos));
    }@Operation(summary = "Atualizar uma província", description = "Atualiza as informações de uma província existente com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Província atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Província não encontrada")
    })

    @PutMapping("{id}")
    public ResponseEntity<ProvinceResponse> update(@PathVariable Long id, @RequestBody @Valid ProviceUpdateDto requestdtos){
        return ResponseEntity.ok(provincesService.update(id, requestdtos));
    }
    @Operation(summary = "Excluir (logicamente) uma província", description = "Altera o estado da província para 'eliminado' em vez de removê-la do banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Província excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Província não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        provincesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
