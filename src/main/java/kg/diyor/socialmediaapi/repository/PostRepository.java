package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
