package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}
