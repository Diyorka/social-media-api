package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@RestController
@RequestMapping("/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Контроллер для работы с пользователями",
        description = "В этом контроллере есть возможности получения пользователей и их данных"
)
public class UserController {
    UserService userService;

    @GetMapping("/my-info")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получение информации о пользователе"
    )
    public ResponseUserDTO getMyInfo(@AuthenticationPrincipal User user) {
        return userService.getMyInfo(user);
    }

    @GetMapping("/search/{name}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Поиск пользователей по имени"
    )
    public List<ResponseUserDTO> searchUsersByName(@PathVariable String name,
                                                   @AuthenticationPrincipal User user) {
        return userService.searchUserByName(name, user);
    }
}
