package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}
