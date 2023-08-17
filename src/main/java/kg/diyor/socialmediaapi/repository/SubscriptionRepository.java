package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.Subscription;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findBySubscriberAndUser(User fromUser, User toUser);

    List<Subscription> findAllBySubscriber(User subscriber);

    List<Subscription> findAllByUser(User user);
}
