package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.FriendRequest;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findAllByFromUser(User user);

    List<FriendRequest> findAllByToUser(User user);
}
