package ao.multaplus.user.controller;

import ao.multaplus.user.dtos.UserDto;
import ao.multaplus.user.entity.Users;
import ao.multaplus.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/user")
@Tag(name = "User", description = "User Endpoints")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Create User", description = "Create User")
    public ResponseEntity<Users> create(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto,null), HttpStatus.OK);
    }
}
