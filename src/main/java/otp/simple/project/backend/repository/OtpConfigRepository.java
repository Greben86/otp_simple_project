package otp.simple.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otp.simple.project.backend.domain.model.OtpConfig;

/**
 * Репозиторий OTP-конфигурации
 */
@Repository
public interface OtpConfigRepository extends JpaRepository<OtpConfig, Long> {
}
