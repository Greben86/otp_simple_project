package otp.simple.project.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Сервис рассылки сообщений электронной почты
 */
@RequiredArgsConstructor
@Service
public class EmailNotificationService {

    private static final String SUBJECT = "OTP код";

    private final JavaMailSender emailSender;
    private final UserService userService;

    /**
     * Отправка OTP кода электронной почты
     *
     * @param otpCode OTP код
     */
    public void sendSimpleEmail(final String otpCode) {
        final var user = userService.getCurrentUser();

        final var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(SUBJECT);
        simpleMailMessage.setText(String.format("OTP код: %s", otpCode));
        emailSender.send(simpleMailMessage);
    }
}
