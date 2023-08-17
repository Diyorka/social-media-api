package kg.diyor.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshToken extends BaseEntity{
    @Column(nullable = false, unique = true)
    String token;

    @Column(name = "expiry_date", nullable = false)
    Instant expiryDate;

    @OneToOne
    User user;
}
