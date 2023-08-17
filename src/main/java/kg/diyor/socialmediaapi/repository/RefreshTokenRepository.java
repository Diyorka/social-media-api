package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.model.RefreshToken;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByUser(User user);

    RefreshToken findByUser(User user);

    Optional<RefreshToken> findByToken(String refreshToken);
}
