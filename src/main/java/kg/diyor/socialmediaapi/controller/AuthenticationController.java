package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.diyor.socialmediaapi.dto.AuthenticationResponse;
import kg.diyor.socialmediaapi.dto.user.RequestUserDTO;
import kg.diyor.socialmediaapi.exception.UserAlreadyExistException;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@RestController
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Контроллер для авторизации/регистрации",
        description = "В этом контроллере есть возможности авторизации, регистрации, обновления токена и выхода"
)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/register")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Регистрация нового аккаунта"
    )
    public ResponseEntity<String> register(@Valid @RequestBody RequestUserDTO userDTO) throws UserAlreadyExistException {
        return authenticationService.register(userDTO);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Авторизация активированного аккаунта"
    )
    public AuthenticationResponse authenticate(@RequestParam String email,
                                               @RequestParam String password) {
        return authenticationService.authenticate(email, password);
    }

    @PostMapping("/refresh")
    @Operation(
            summary = "Обновление токена"
    )
    public AuthenticationResponse refresh(@RequestParam String refreshToken) {
        return authenticationService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Выход из аккаунта"
    )
    public ResponseEntity<String> logout(@AuthenticationPrincipal User user) {
        return authenticationService.logout(user);
    }

}
