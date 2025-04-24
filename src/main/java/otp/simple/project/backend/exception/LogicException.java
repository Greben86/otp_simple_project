package otp.simple.project.backend.exception;

/**
 * Логическая ошибка
 */
public class LogicException extends RuntimeException {

    public LogicException(String message) {
        super(message);
    }
}
