package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.message.RequestMessageDTO;
import kg.diyor.socialmediaapi.dto.message.ResponseMessageDTO;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.http.ResponseEntity;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface MessageService {
    ResponseEntity<String> sendMessage(RequestMessageDTO messageDTO, User sender);

    ResponseMessageDTO updateMessage(Long messageId, String text, User sender);

    ResponseEntity<String> deleteMessage(Long messageId, User sender);
}
