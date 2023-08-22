package kg.diyor.socialmediaapi.service.Impl;

import kg.diyor.socialmediaapi.dto.chat.ResponseChatDTO;
import kg.diyor.socialmediaapi.exception.NoAccessException;
import kg.diyor.socialmediaapi.exception.NotFoundException;
import kg.diyor.socialmediaapi.model.Chat;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.ChatRepository;
import kg.diyor.socialmediaapi.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static kg.diyor.socialmediaapi.dto.chat.ResponseChatDTO.toResponseChatDTO;
import static kg.diyor.socialmediaapi.dto.chat.ResponseChatDTO.toResponseChatDTOs;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    ChatRepository chatRepository;

    @Override
    public List<ResponseChatDTO> getMyChats(User user) {
        return toResponseChatDTOs(chatRepository.findAllByParticipant1OrParticipant2(user, user));
    }

    @Override
    public ResponseChatDTO getChatById(Long chatId, User user) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NotFoundException("Chat with such id wasn't found"));

        if(!chat.getParticipant1().equals(user) &&
            !chat.getParticipant2().equals(user)){
            throw new NoAccessException("It's not your chat");
        }

        return toResponseChatDTO(chat);
    }
}
