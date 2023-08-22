package kg.diyor.socialmediaapi.dto.chat;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import kg.diyor.socialmediaapi.dto.message.ResponseMessageDTO;
import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.Chat;
import kg.diyor.socialmediaapi.model.Message;
import kg.diyor.socialmediaapi.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

import static kg.diyor.socialmediaapi.dto.message.ResponseMessageDTO.toResponseMessageDTOs;
import static kg.diyor.socialmediaapi.dto.user.ResponseUserDTO.toResponseUserDTO;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseChatDTO {
    Long id;

    ResponseUserDTO participant1;

    ResponseUserDTO participant2;

    List<ResponseMessageDTO> messages;

    public static ResponseChatDTO toResponseChatDTO(Chat chat){
        return ResponseChatDTO.builder()
                .id(chat.getId())
                .participant1(toResponseUserDTO(chat.getParticipant1()))
                .participant2(toResponseUserDTO(chat.getParticipant2()))
                .messages(toResponseMessageDTOs(chat.getMessages()))
                .build();
    }

    public static List<ResponseChatDTO> toResponseChatDTOs(List<Chat> chats){
        return chats.stream().map(ResponseChatDTO::toResponseChatDTO).toList();
    }
}
