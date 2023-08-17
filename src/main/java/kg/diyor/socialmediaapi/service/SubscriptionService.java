package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.subscription.ResponseSubscriptionDTO;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface SubscriptionService {
    ResponseEntity<String> unsubscribeUser(Long id, User user);

    List<ResponseSubscriptionDTO> getMySubscriptions(User user);

    List<ResponseSubscriptionDTO> getMySubscribers(User user);
}
