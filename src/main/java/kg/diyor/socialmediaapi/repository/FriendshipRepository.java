package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
