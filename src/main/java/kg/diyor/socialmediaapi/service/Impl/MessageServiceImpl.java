package kg.diyor.socialmediaapi.service.Impl;

import kg.diyor.socialmediaapi.dto.message.RequestMessageDTO;
import kg.diyor.socialmediaapi.dto.message.ResponseMessageDTO;
import kg.diyor.socialmediaapi.exception.NoAccessException;
import kg.diyor.socialmediaapi.exception.NotFoundException;
import kg.diyor.socialmediaapi.model.Chat;
import kg.diyor.socialmediaapi.model.Friendship;
import kg.diyor.socialmediaapi.model.Message;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.ChatRepository;
import kg.diyor.socialmediaapi.repository.FriendshipRepository;
import kg.diyor.socialmediaapi.repository.MessageRepository;
import kg.diyor.socialmediaapi.repository.UserRepository;
import kg.diyor.socialmediaapi.service.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static kg.diyor.socialmediaapi.dto.message.ResponseMessageDTO.toResponseMessageDTO;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository;
    ChatRepository chatRepository;
    UserRepository userRepository;
    FriendshipRepository friendshipRepository;

    @Override
    public ResponseEntity<String> sendMessage(RequestMessageDTO messageDTO, User sender) {
        User receiver = userRepository.findById(messageDTO.getReceiverId())
                .orElseThrow(() -> new NotFoundException("User with such id wasn't found"));

        if(!friendshipRepository.existsByUserAndFriend(sender, receiver)){
            throw new NoAccessException("Receiver is not your friend");
        }

        Chat chat = chatRepository.findByParticipants(sender, receiver)
                .orElse(Chat.builder()
                        .participant1(sender)
                        .participant2(receiver)
                        .build());

        Message message = Message.builder()
                .text(messageDTO.getText())
                .chat(chat)
                .sender(sender)
                .build();

        chatRepository.save(chat);
        messageRepository.save(message);

        return ResponseEntity.ok("Message was successfully sent");
    }

    @Override
    public ResponseMessageDTO updateMessage(Long messageId, String text, User sender) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NotFoundException("Message with such id wasn't found"));

        if(!message.getSender().equals(sender)){
            throw new NoAccessException("You can't update this message");
        }

        message.setText(text);
        messageRepository.save(message);

        return toResponseMessageDTO(message);
    }

    @Override
    public ResponseEntity<String> deleteMessage(Long messageId, User sender) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NotFoundException("Message with such id wasn't found"));

        if(!message.getSender().equals(sender)){
            throw new NoAccessException("You can't delete this message");
        }

        if(message.getChat().getMessages().size() == 1){
            chatRepository.delete(message.getChat());
        }else {
            message.getChat().getMessages().remove(message);
            chatRepository.save(message.getChat());
        }

        return ResponseEntity.ok("Message was successfully deleted");
    }
}
