package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.friendRequest.ResponseFriendRequestDTO;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface FriendRequestService {
    ResponseEntity<String> sendRequest(Long id, User sender);

    ResponseEntity<String> cancelRequest(Long requestId, User user);

    List<ResponseFriendRequestDTO> getMyRequests(User user);

    List<ResponseFriendRequestDTO> getReceivedRequests(User user);

    ResponseEntity<String> acceptRequest(Long requestId, User user);

    ResponseEntity<String> declineRequest(Long requestId, User user);
}
