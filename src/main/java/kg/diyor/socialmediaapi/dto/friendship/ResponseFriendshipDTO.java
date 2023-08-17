package kg.diyor.socialmediaapi.dto.friendship;

import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.Friendship;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static kg.diyor.socialmediaapi.dto.user.ResponseUserDTO.toResponseUserDTO;

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
public class ResponseFriendshipDTO {
    Long id;

    ResponseUserDTO friend;

    public static ResponseFriendshipDTO toResponseFriendshipDTO(Friendship friendship){
        return ResponseFriendshipDTO.builder()
                .id(friendship.getId())
                .friend(toResponseUserDTO(friendship.getFriend()))
                .build();
    }

    public static List<ResponseFriendshipDTO> toResponseFriendshipDTOs(List<Friendship> friendships){
        return friendships.stream().map(ResponseFriendshipDTO::toResponseFriendshipDTO).toList();
    }
}
