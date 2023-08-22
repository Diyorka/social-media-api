package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.diyor.socialmediaapi.dto.chat.ResponseChatDTO;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@RestController
@RequestMapping("/api/chats")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(
        name = "Контроллер для работы с чатами",
        description = "В этом контроллере есть возможности получения чатов"
)
public class ChatController {
    ChatService chatService;

    @GetMapping("/my")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получение всех чатов пользователя"
    )
    public List<ResponseChatDTO> getMyChats(@AuthenticationPrincipal User user){
        return chatService.getMyChats(user);
    }

    @GetMapping("/{chat_id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получение чата по айди"
    )
    public ResponseChatDTO getChatById(@PathVariable Long chat_id,
                                       @AuthenticationPrincipal User user){
        return chatService.getChatById(chat_id, user);
    }

}
