package kg.diyor.socialmediaapi.dto.message;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class RequestMessageDTO {
    @NotEmpty(message = "Text can't be empty")
    String text;

    @NotNull(message = "Receiver id can't be null")
    Long receiverId;
}
