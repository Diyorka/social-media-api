package kg.diyor.socialmediaapi.repository;

import kg.diyor.socialmediaapi.dto.chat.ResponseChatDTO;
import kg.diyor.socialmediaapi.model.Chat;
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
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByParticipant1AndParticipant2(User participant1, User participant2);
    List<Chat> findAllByParticipant1OrParticipant2(User user1, User user2);

    default Optional<Chat> findByParticipants(User participant1, User participant2) {
        Optional<Chat> chat = findByParticipant1AndParticipant2(participant1, participant2);
        if (chat.isPresent()) {
            return chat;
        } else {
            return findByParticipant1AndParticipant2(participant2, participant1);
        }
    }
}
