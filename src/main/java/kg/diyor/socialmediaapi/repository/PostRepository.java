package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.dto.post.ResponsePostDTO;
import kg.diyor.socialmediaapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
}
