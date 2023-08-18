package kg.diyor.socialmediaapi.service.Impl;

import kg.diyor.socialmediaapi.dto.friendship.ResponseFriendshipDTO;
import kg.diyor.socialmediaapi.exception.NotFoundException;
import kg.diyor.socialmediaapi.model.Friendship;
import kg.diyor.socialmediaapi.model.Subscription;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.FriendshipRepository;
import kg.diyor.socialmediaapi.repository.SubscriptionRepository;
import kg.diyor.socialmediaapi.repository.UserRepository;
import kg.diyor.socialmediaapi.service.FriendshipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static kg.diyor.socialmediaapi.dto.friendship.ResponseFriendshipDTO.toResponseFriendshipDTOs;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {
    FriendshipRepository friendshipRepository;
    SubscriptionRepository subscriptionRepository;

    @Override
    public List<ResponseFriendshipDTO> getMyFriends(User user) {
        return toResponseFriendshipDTOs(friendshipRepository.findAllByUser(user));
    }

    @Override
    public ResponseEntity<String> deleteFriend(Long friendship_id, User user) {
        Friendship friendship = friendshipRepository.findById(friendship_id)
                        .orElseThrow(() -> new NotFoundException("Friendship wasn't found"));

        Friendship friendship1 = friendshipRepository.findByFriend(user).get();

        Subscription subscription = subscriptionRepository.findBySubscriberAndUser(user, friendship.getFriend()).get();

        subscriptionRepository.delete(subscription);
        friendshipRepository.delete(friendship1);
        friendshipRepository.delete(friendship);

        return ResponseEntity.ok("Friend was deleted");
    }
}
