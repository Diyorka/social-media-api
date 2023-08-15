package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
