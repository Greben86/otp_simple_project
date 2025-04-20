package otp.simple.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otp.simple.project.backend.domain.model.OtpCode;
import otp.simple.project.backend.domain.model.OtpStatus;
import otp.simple.project.backend.domain.model.User;

import java.util.Optional;

/**
 * Репозиторий категорий
 */
@Repository
public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findByOperationIdAndUser(Long id, User user);
    Optional<OtpCode> findByOperationIdAndStatusAndUser(Long id, OtpStatus status, User user);
    boolean existsByOperationIdAndStatusAndUser(Long id, OtpStatus status, User user);
    boolean existsByCodeAndStatusAndUser(String code, OtpStatus status, User user);
}
