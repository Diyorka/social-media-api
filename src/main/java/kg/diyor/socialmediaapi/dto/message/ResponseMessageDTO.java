package kg.diyor.socialmediaapi.dto.message;

import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.Message;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

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
public class ResponseMessageDTO {
    Long id;

    String text;

    ResponseUserDTO sender;

    LocalDateTime createdAt;

    public static ResponseMessageDTO toResponseMessageDTO(Message message){
        return ResponseMessageDTO.builder()
                .id(message.getId())
                .text(message.getText())
                .sender(toResponseUserDTO(message.getSender()))
                .createdAt(message.getCreatedAt())
                .build();
    }

    public static List<ResponseMessageDTO> toResponseMessageDTOs(List<Message> messages){
        return messages.stream().map(ResponseMessageDTO::toResponseMessageDTO).toList();
    }
}
