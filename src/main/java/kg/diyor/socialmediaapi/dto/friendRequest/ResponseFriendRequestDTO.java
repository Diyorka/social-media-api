package kg.diyor.socialmediaapi.dto.friendRequest;

import jakarta.persistence.ManyToOne;
import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.FriendRequest;
import kg.diyor.socialmediaapi.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
public class ResponseFriendRequestDTO {
    Long id;

    ResponseUserDTO toUser;

    ResponseUserDTO fromUser;

    LocalDateTime requestDate;

    public static ResponseFriendRequestDTO toResponseFriendRequestDTO(FriendRequest friendRequest){
        return ResponseFriendRequestDTO.builder()
                .id(friendRequest.getId())
                .toUser(toResponseUserDTO(friendRequest.getToUser()))
                .fromUser(toResponseUserDTO(friendRequest.getFromUser()))
                .requestDate(friendRequest.getRequestDate())
                .build();
    }

    public static List<ResponseFriendRequestDTO> toResponseFriendRequestDTOs(List<FriendRequest> friendRequests){
        return friendRequests.stream().map(ResponseFriendRequestDTO::toResponseFriendRequestDTO).toList();
    }
}
