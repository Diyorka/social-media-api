package kg.diyor.socialmediaapi.service.Impl;

import kg.diyor.socialmediaapi.dto.subscription.ResponseSubscriptionDTO;
import kg.diyor.socialmediaapi.exception.NotFoundException;
import kg.diyor.socialmediaapi.model.Subscription;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.SubscriptionRepository;
import kg.diyor.socialmediaapi.repository.UserRepository;
import kg.diyor.socialmediaapi.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static kg.diyor.socialmediaapi.dto.subscription.ResponseSubscriptionDTO.toResponseSubscriptionDTOs;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionRepository subscriptionRepository;
    UserRepository userRepository;

    @Override
    public ResponseEntity<String> unsubscribeUser(Long id, User user) {
        User getUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with such id wasn't found"));

        Subscription subscription = subscriptionRepository.findBySubscriberAndUser(user, getUser).get();

        subscriptionRepository.delete(subscription);

        return ResponseEntity.ok("Successfully unsubscribed");
    }

    @Override
    public List<ResponseSubscriptionDTO> getMySubscriptions(User subscriber) {
        return toResponseSubscriptionDTOs(subscriptionRepository.findAllBySubscriber(subscriber));
    }

    @Override
    public List<ResponseSubscriptionDTO> getMySubscribers(User user) {
        return toResponseSubscriptionDTOs(subscriptionRepository.findAllByUser(user));
    }
}
