package otp.simple.project.backend.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "otp_config")
public class OtpConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 5791855035263429986L;

    @Id
    @EqualsAndHashCode.Include
    private Long id;
    
    private Long expirationTime;

    private Integer length;
}
