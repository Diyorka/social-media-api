package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.diyor.socialmediaapi.dto.subscription.ResponseSubscriptionDTO;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@RestController
@RequestMapping("/api/subscriptions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Контроллер для работы с подписками",
        description = "В этом контроллере есть возможности получения и отмены подписок"
)
public class SubscriptionController {
    SubscriptionService subscriptionService;

    @PostMapping("/unsubscribe/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Отписаться от пользователя"
    )
    public ResponseEntity<String> unSubscribeUser(@PathVariable Long id,
                                                @AuthenticationPrincipal User user){
        return subscriptionService.unsubscribeUser(id, user);
    }

    @GetMapping("/my-subscriptions")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получить список подписок пользователя"
    )
    public List<ResponseSubscriptionDTO> getMySubscriptions(@AuthenticationPrincipal User user){
        return subscriptionService.getMySubscriptions(user);
    }

    @GetMapping("/my-subscribers")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получит список подписчиков пользователя"
    )
    public List<ResponseSubscriptionDTO> getMySubscribers(@AuthenticationPrincipal User user){
        return subscriptionService.getMySubscribers(user);
    }

}
