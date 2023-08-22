package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.chat.ResponseChatDTO;
import kg.diyor.socialmediaapi.model.User;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface ChatService {
    List<ResponseChatDTO> getMyChats(User user);

    ResponseChatDTO getChatById(Long chatId, User user);
}
