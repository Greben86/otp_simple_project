package otp.simple.project.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otp.simple.project.backend.domain.dto.OtpConfigurationDTO;
import otp.simple.project.backend.domain.model.OtpConfig;
import otp.simple.project.backend.exception.LogicException;
import otp.simple.project.backend.repository.OtpConfigRepository;

/**
 * Сервис управления транзакциями
 */
@RequiredArgsConstructor
@Service
@Transactional
public class OtpConfigurationService {

    public static final long CONFIG_ID = 1L;

    private final OtpConfigRepository repository;

    /**
     * Обновление конфигурации
     *
     * @param request данные конфигурации
     */
    public void updateConfiguration(final OtpConfigurationDTO request) {
        var config = repository.findById(CONFIG_ID)
                .orElseGet(OtpConfig::new);

        config.setId(CONFIG_ID);
        config.setExpirationTime(request.expirationTime());
        config.setLength(request.length());

        repository.save(config);
    }

    /**
     * Обновление конфигурации
     *
     * @return данные конфигурации
     */
    public OtpConfigurationDTO getConfiguration() {
        var config = repository.findById(CONFIG_ID)
                .orElseThrow(() -> new LogicException("Конфигурация OTP не найдена"));

        return new OtpConfigurationDTO(config.getExpirationTime(), config.getLength());
    }
}
