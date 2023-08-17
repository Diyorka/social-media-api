package kg.diyor.socialmediaapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class RequestUserDTO {
    @Email(message = "Write email correctly")
    String email;

    @NotEmpty(message = "Username can't be empty")
    String name;

    @Size(min = 4, max = 50, message = "Password should contain from 4 to 50 characters")
    String password;

    String confirmPassword;
}
