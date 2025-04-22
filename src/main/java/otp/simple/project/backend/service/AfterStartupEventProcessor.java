package otp.simple.project.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import otp.simple.project.backend.domain.dto.OtpConfigurationDTO;
import otp.simple.project.backend.exception.LogicException;

/**
 * Проверка конфигурации OTP-кодов при старте приложения
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AfterStartupEventProcessor {

    private final OtpConfigurationService otpConfigurationService;

    // Время жизни OTP-кода по умолчанию
    @Value("${configuration.default.expirationTime}")
    private long expirationTime;

    // Длинна OTP-кода по умолчанию
    @Value("${configuration.default.length}")
    private int length;

    /**
     * Обработка события старта приложения
     */
    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        try {
            final var configuration = otpConfigurationService.getConfiguration();
        } catch (LogicException e) {
            log.info("Добавление конфигурации по умолчанию");
            otpConfigurationService.updateConfiguration(new OtpConfigurationDTO(expirationTime, length));
        }
    }
}
