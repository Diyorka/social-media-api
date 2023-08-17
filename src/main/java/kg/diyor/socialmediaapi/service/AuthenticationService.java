package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.AuthenticationResponse;
import kg.diyor.socialmediaapi.dto.user.RequestUserDTO;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.http.ResponseEntity;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface AuthenticationService {
    ResponseEntity<String> register(RequestUserDTO userDTO);

    AuthenticationResponse authenticate(String email, String password);

    AuthenticationResponse refreshToken(String refreshToken);

    ResponseEntity<String> logout(User user);
}
