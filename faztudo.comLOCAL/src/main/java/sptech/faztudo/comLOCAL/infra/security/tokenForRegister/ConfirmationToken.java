package sptech.faztudo.comLOCAL.infra.security.tokenForRegister;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sptech.faztudo.comLOCAL.domain.users.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @Column(unique = true, name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public ConfirmationToken(String token,
                             LocalDateTime createAt,
                             LocalDateTime expiresAt,
                             User user) {
        this.token = token;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
