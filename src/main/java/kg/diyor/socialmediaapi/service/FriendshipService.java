package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.friendship.ResponseFriendshipDTO;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface FriendshipService {
    List<ResponseFriendshipDTO> getMyFriends(User user);

    ResponseEntity<String> deleteFriend(Long friendship_id, User user);
}
