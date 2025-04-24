package otp.simple.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otp.simple.project.backend.domain.model.OtpCode;
import otp.simple.project.backend.domain.model.Status;
import otp.simple.project.backend.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий OTP-кодов
 */
@Repository
public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findByOperationId(Long id);
    Optional<OtpCode> findByOperationIdAndUser(Long id, User user);
    Optional<OtpCode> findByOperationIdAndStatusAndUser(Long id, Status status, User user);
    List<OtpCode> findByStatus(Status status);
    List<OtpCode> findByStatusAndUser(Status status, User user);
    boolean existsByOperationIdAndStatusAndUser(Long id, Status status, User user);
    boolean existsByCodeAndStatusAndUser(String code, Status status, User user);
}
