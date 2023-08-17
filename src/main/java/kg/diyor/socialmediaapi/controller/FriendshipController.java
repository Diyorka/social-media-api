package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.diyor.socialmediaapi.dto.friendship.ResponseFriendshipDTO;
import kg.diyor.socialmediaapi.model.Friendship;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.service.FriendshipService;
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
@RequestMapping("/api/friends")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(
        name = "Контроллер для работы с друзьями",
        description = "В этом контроллере есть возможности получения и удаления из друзей"
)
public class FriendshipController {
    FriendshipService friendshipService;

    @GetMapping("/my")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получение друзей пользователя"
    )
    public List<ResponseFriendshipDTO> getMyFriends(@AuthenticationPrincipal User user){
        return friendshipService.getMyFriends(user);
    }

    @DeleteMapping("/delete/{friendship_id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Удаление пользователя из друзей"
    )
    public ResponseEntity<String> deleteFriend(@PathVariable Long friendship_id,
                                               @AuthenticationPrincipal User user){
        return friendshipService.deleteFriend(friendship_id, user);
    }

}
