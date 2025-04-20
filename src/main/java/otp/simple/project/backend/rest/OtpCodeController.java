package otp.simple.project.backend.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import otp.simple.project.backend.domain.dto.OtpCodeActivateRequest;
import otp.simple.project.backend.domain.dto.OtpCodeCreateRequest;
import otp.simple.project.backend.domain.dto.OtpCodeResponse;
import otp.simple.project.backend.domain.dto.OtpConfigurationDTO;
import otp.simple.project.backend.service.OtpCodeService;
import otp.simple.project.backend.service.OtpConfigurationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("otp")
@Tag(name = "REST API: OTP-коды")
public class OtpCodeController {

    private final OtpConfigurationService otpConfigurationService;
    private final OtpCodeService otpCodeService;

    @Operation(summary = "Создание OTP-кода")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OtpCodeResponse createCode(@RequestBody @Valid OtpCodeCreateRequest code) {
        return otpCodeService.createCode(code);
    }

    @Operation(summary = "Редактирование категории")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{operationId}/activate",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OtpCodeResponse useCode(@PathVariable("id") Long id, @RequestBody @Valid OtpCodeActivateRequest code) {
        return otpCodeService.activateCode(id, code);
    }

    @Operation(summary = "Редактирование категории")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{operationId}/get",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OtpCodeResponse getCodeInfo(@PathVariable("id") Long id) {
        return otpCodeService.getCodeInfo(id);
    }

    @Operation(summary = "Добавление/обновление конфигурации")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/configuration/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OtpConfigurationDTO updateConfiguration(@RequestBody @Valid OtpConfigurationDTO configuration) {
        return otpConfigurationService.updateConfiguration(configuration);
    }

    @Operation(summary = "Получение конфигурации")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/configuration/get",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OtpConfigurationDTO getConfiguration() {
        return otpConfigurationService.getConfiguration();
    }
}
