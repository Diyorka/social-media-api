package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.dto.post.ResponsePostDTO;
import kg.diyor.socialmediaapi.model.Post;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);

    @Query("SELECT p FROM Post p WHERE p.user IN (" +
            "SELECT s.user FROM Subscription s " +
            "WHERE s.subscriber = :user)")
    Page<Post> findAllSubscribedUsersPosts(User user, Pageable pageable);
}
