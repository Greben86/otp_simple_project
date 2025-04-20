package otp.simple.project.backend.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import otp.simple.project.backend.domain.model.OtpStatus;

@Schema(description = "OTP код: DTO ответа")
public record OtpCodeResponse(
        @Schema(description = "Идентификатор операции", example = "1")
        @NotBlank
        long id,

        @Schema(description = "Статус OTP кода", example = "Активен")
        @NotBlank
        OtpStatus status) {
}
