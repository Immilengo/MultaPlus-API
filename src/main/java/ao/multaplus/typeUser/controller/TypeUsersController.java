package ao.multaplus.typeUser.controller;

import ao.multaplus.typeUser.dtos.TypeUserUpdateDto;
import ao.multaplus.typeUser.dtos.TypeUsersDto;
import ao.multaplus.typeUser.response.TypeUsersResponse;
import ao.multaplus.typeUser.service.TypeUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-users")
@RequiredArgsConstructor
@Tag(name = "TypeUsers", description = "manege TypeUsers")
public class TypeUsersController {
    @Autowired
    private final TypeUserService typeUsersService;

    @Operation(summary = "Create a new userType")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserType created successfully"),
            @ApiResponse(responseCode = "400", description = " Invalid request")
    })
    @PostMapping
    public ResponseEntity<TypeUsersResponse> create(@RequestBody @Valid TypeUsersDto request) {
        return ResponseEntity.ok(typeUsersService.create(request));
    }

    @Operation(summary = "get a userType by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserType found"),
            @ApiResponse(responseCode = "404", description = "UserType not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TypeUsersResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(typeUsersService.getById(id));
    }

    @Operation(summary = "get all userTypes")
    @ApiResponse(responseCode = "200", description = "list of all userTypes")
    @GetMapping
    public ResponseEntity<List<TypeUsersResponse>> getAll() {
        return ResponseEntity.ok(typeUsersService.getAll());
    }

    @Operation(summary = "update a userType by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "userType updte successfully"),
            @ApiResponse(responseCode = "404", description = "userType not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TypeUsersResponse> update(@PathVariable Long id, @RequestBody @Valid TypeUserUpdateDto request) {
        return ResponseEntity.ok(typeUsersService.update(id, request));
    }

    @Operation(summary = "delete  a userType by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "userType deleted successfully"),
            @ApiResponse(responseCode = "404", description = "userType not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeUsersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
