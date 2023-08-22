package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.Friendship;
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
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findAllByUser(User user);

    Optional<Friendship> findByUserAndFriend(User user, User friend);

    Optional<Friendship> findByFriend(User user);

    boolean existsByUserAndFriend(User user, User friend);
}
