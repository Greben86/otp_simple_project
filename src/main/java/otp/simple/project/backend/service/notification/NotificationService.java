package otp.simple.project.backend.service.notification;

import otp.simple.project.backend.domain.model.User;

/**
 * Уведомление пользователя
 */
public interface NotificationService {

    /**
     * Отправка OTP кода
     *
     * @param user пользователь
     * @param otpCode OTP код
     */
    boolean sendOtpCode(User user, String otpCode);
}
