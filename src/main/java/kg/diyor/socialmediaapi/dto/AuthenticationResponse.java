package kg.diyor.socialmediaapi.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String accessToken;

    String refreshToken;
}
