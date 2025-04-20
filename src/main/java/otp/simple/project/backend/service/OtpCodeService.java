package otp.simple.project.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otp.simple.project.backend.domain.dto.OtpCodeActivateRequest;
import otp.simple.project.backend.domain.dto.OtpCodeCreateRequest;
import otp.simple.project.backend.domain.dto.OtpCodeResponse;
import otp.simple.project.backend.domain.model.OtpCode;
import otp.simple.project.backend.domain.model.OtpStatus;
import otp.simple.project.backend.exception.LogicException;
import otp.simple.project.backend.repository.OtpCodeRepository;

import java.util.Random;
import java.util.function.Predicate;

/**
 * Сервис управления категориями
 */
@RequiredArgsConstructor
@Service
@Transactional
public class OtpCodeService {

    private static final String ALPHABET = "1234567890";
    private final Random random = new Random();

    private final OtpConfigurationService configurationService;
    private final OtpCodeRepository repository;
    private final UserService userService;
    private final EmailNotificationService emailNotificationService;

    /**
     * Добавление категории
     *
     * @param request данные категории
     * @return новая категория
     */
    public OtpCodeResponse createCode(final OtpCodeCreateRequest request) {
        var user = userService.getCurrentUser();
        if (repository.existsByOperationIdAndStatusAndUser(request.operationId(), OtpStatus.ACTIVE, user)) {
            throw new LogicException("Найден активный OTP-код для операции");
        }

        var config = configurationService.getConfiguration();

        var code = new OtpCode();
        code.setUser(user);
        code.setStatus(OtpStatus.ACTIVE);
        code.setOperationId(request.operationId());
        code.setCode(generateKey(config.length(), value ->
                repository.existsByCodeAndStatusAndUser(value, OtpStatus.ACTIVE, user)));
        code.setExpirationTime(code.getExpirationTime());
        repository.save(code);

        emailNotificationService.sendSimpleEmail(code.getCode());

        return convertToResponse(code);
    }

    /**
     * Редактирование категории
     *
     * @param id идентификатор категории
     * @param request данные категории
     * @return обновленная категория
     */
    public OtpCodeResponse activateCode(final Long id, final OtpCodeActivateRequest request) {
        var user = userService.getCurrentUser();
        if (!repository.existsByOperationIdAndStatusAndUser(request.operationId(), OtpStatus.ACTIVE, user)) {
            throw new LogicException("Активный OTP-код для операции не найден");
        }

        var code = repository.findByOperationIdAndStatusAndUser(id, OtpStatus.ACTIVE, user)
                .orElseThrow(IllegalStateException::new);
        code.setStatus(OtpStatus.USED);
        repository.save(code);

        return convertToResponse(code);
    }

    /**
     * Выборка всех категорий пользователя
     *
     * @return список категорий
     */
    public OtpCodeResponse getCodeInfo(Long id) {
        var user = userService.getCurrentUser();
        return repository.findByOperationIdAndUser(id, user)
                .map(this::convertToResponse)
                .orElseThrow(() -> new LogicException("OTP-код не найден"));
    }

    private OtpCodeResponse convertToResponse(OtpCode input) {
        return new OtpCodeResponse(input.getId(), input.getStatus());
    }

    /**
     * Метод формирования уникального кода ссылки для короткой ссылки
     * Каждый символ кода выбирается случайным образом, после чего код проверяется на совпадение с уже
     * сохраненными активными кодами, если совпадений нет, то код принимается, если нет - пробуем еще раз
     *
     * @param size размер кода
     * @param existChecker лямбда для проверки уникальности кода
     *
     * @return уникальный для пользователя код
     */
    private String generateKey(final int size, final Predicate<String> existChecker) {
        String key;
        do {
            // Генерация строки случайным образом
            var sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            }
            key = sb.toString();
        } while (existChecker.test(key));

        return key;
    }
}
