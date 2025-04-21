package otp.simple.project.backend.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import otp.simple.project.backend.domain.dto.UserDTO;
import otp.simple.project.backend.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Tag(name = "REST API: Пользователь")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Редактирование имени пользователя")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO editUser(@RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(userDTO);
        return userDTO;
    }
}
