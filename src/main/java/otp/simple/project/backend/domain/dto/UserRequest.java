package otp.simple.project.backend.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Пользователь: DTO запроса")
public record UserRequest(
        @Schema(description = "Имя пользователя", example = "Вася")
        @Size(min = 1, max = 50, message = "Имя пользователя должно содержать от 1 до 50 символов")
        @NotBlank(message = "Поле не может быть пустым")
        String username,

        @Schema(description = "Email пользователя", example = "your_email@example.com")
        @Email(message = "Адрес Email должен быть валидным")
        @NotBlank(message = "Поле не может быть пустым")
        String email) {
}
