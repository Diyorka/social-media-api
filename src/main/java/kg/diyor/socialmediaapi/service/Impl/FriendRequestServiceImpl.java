package kg.diyor.socialmediaapi.service.Impl;

import kg.diyor.socialmediaapi.dto.friendRequest.ResponseFriendRequestDTO;
import kg.diyor.socialmediaapi.exception.NoAccessException;
import kg.diyor.socialmediaapi.exception.NotFoundException;
import kg.diyor.socialmediaapi.model.FriendRequest;
import kg.diyor.socialmediaapi.model.Friendship;
import kg.diyor.socialmediaapi.model.Subscription;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.FriendRequestRepository;
import kg.diyor.socialmediaapi.repository.FriendshipRepository;
import kg.diyor.socialmediaapi.repository.SubscriptionRepository;
import kg.diyor.socialmediaapi.repository.UserRepository;
import kg.diyor.socialmediaapi.service.FriendRequestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static kg.diyor.socialmediaapi.dto.friendRequest.ResponseFriendRequestDTO.toResponseFriendRequestDTOs;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {
    FriendRequestRepository friendRequestRepository;
    SubscriptionRepository subscriptionRepository;
    FriendshipRepository friendshipRepository;
    UserRepository userRepository;

    @Override
    public ResponseEntity<String> sendRequest(Long id, User sender) {
        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with such id wasn't found"));

        FriendRequest friendRequest = FriendRequest.builder()
                .fromUser(sender)
                .toUser(targetUser)
                .build();

        Subscription subscription = Subscription.builder()
                .subscriber(sender)
                .user(targetUser)
                .build();

        subscriptionRepository.save(subscription);
        friendRequestRepository.save(friendRequest);

        return ResponseEntity.ok("Request was successfully sent");
    }

    @Override
    public ResponseEntity<String> cancelRequest(Long requestId, User user) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request with such id wasn't found"));

        if(!friendRequest.getFromUser().equals(user)){
            throw new NoAccessException("It's not your request");
        }

        Subscription subscription = subscriptionRepository.findBySubscriberAndUser(friendRequest.getFromUser(),
                friendRequest.getToUser()).get();

        subscriptionRepository.delete(subscription);
        friendRequestRepository.delete(friendRequest);

        return ResponseEntity.ok("Successfully canceled request");
    }

    @Override
    public List<ResponseFriendRequestDTO> getMyRequests(User user) {
        return toResponseFriendRequestDTOs(friendRequestRepository.findAllByFromUser(user));
    }

    @Override
    public List<ResponseFriendRequestDTO> getReceivedRequests(User user) {
        return toResponseFriendRequestDTOs(friendRequestRepository.findAllByToUser(user));
    }

    @Override
    public ResponseEntity<String> acceptRequest(Long requestId, User user) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request with such id wasn't found"));

        if(!friendRequest.getToUser().equals(user)){
            throw new NoAccessException("This request wasn't sent to you");
        }

        Subscription subscription = Subscription.builder()
                .subscriber(user)
                .user(friendRequest.getFromUser())
                .build();

        Friendship friendship = Friendship.builder()
                .friend(friendRequest.getFromUser())
                .user(user)
                .build();

        subscriptionRepository.save(subscription);
        friendshipRepository.save(friendship);

        friendRequestRepository.delete(friendRequest);
        return ResponseEntity.ok("Request was successfully accepted");
    }

    @Override
    public ResponseEntity<String> declineRequest(Long requestId, User user) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request with such id wasn't found"));

        if(!friendRequest.getToUser().equals(user)){
            throw new NoAccessException("This request wasn't sent to you");
        }

        friendRequestRepository.delete(friendRequest);
        return ResponseEntity.ok("Request was successfully declined");
    }
}
