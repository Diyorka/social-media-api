package kg.diyor.socialmediaapi.dto.user;

import kg.diyor.socialmediaapi.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

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
public class ResponseUserDTO {
    Long id;

    String name;

    String email;

    LocalDateTime createdAt;

    public static ResponseUserDTO toResponseUserDTO(User user){
        return ResponseUserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static List<ResponseUserDTO> toResponseUserDTOs(List<User> users){
        return users.stream().map(ResponseUserDTO::toResponseUserDTO).toList();
    }
}
