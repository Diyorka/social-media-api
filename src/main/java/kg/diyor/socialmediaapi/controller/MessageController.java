package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.diyor.socialmediaapi.dto.message.RequestMessageDTO;
import kg.diyor.socialmediaapi.dto.message.ResponseMessageDTO;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.service.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@RestController
@RequestMapping("/api/messages")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(
        name = "Контроллер для работы с сообщениями",
        description = "В этом контроллере есть возможности отправки, изменения и удаления сообщений"
)
public class MessageController {
    MessageService messageService;

    @PostMapping("/send")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Отправка сообщения"
    )
    public ResponseEntity<String> sendMessage(@RequestBody RequestMessageDTO messageDTO,
                                              @AuthenticationPrincipal User user){
        return messageService.sendMessage(messageDTO, user);
    }

    @PutMapping("/update/{message_id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Изменение сообщения"
    )
    public ResponseMessageDTO updateMessage(@PathVariable Long message_id,
                                            @RequestParam String newText,
                                            @AuthenticationPrincipal User user){
        return messageService.updateMessage(message_id, newText, user);
    }

    @DeleteMapping("/delete/{message_id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Удаление сообщения"
    )
    public ResponseEntity<String> deleteMessage(@PathVariable Long message_id,
                                                @AuthenticationPrincipal User user){
        return messageService.deleteMessage(message_id, user);
    }
}
