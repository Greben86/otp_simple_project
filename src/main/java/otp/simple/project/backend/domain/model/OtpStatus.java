package otp.simple.project.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Статус OTP-кода
 */
@RequiredArgsConstructor
public enum OtpStatus {

    ACTIVE("Активен"),
    EXPIRED("Пророчен"),
    USED("Использован");

    @Getter(onMethod_ = @JsonValue)
    private final String name;

    private static final Map<String, OtpStatus> MAP = Stream.of(values())
            .collect(Collectors.toMap(OtpStatus::getName, Function.identity()));

    @JsonCreator
    public static OtpStatus forValue(final String value) {
        return MAP.get(value);
    }
}
