package kg.diyor.socialmediaapi.service.Impl;

import kg.diyor.socialmediaapi.dto.AuthenticationResponse;
import kg.diyor.socialmediaapi.dto.user.RequestUserDTO;
import kg.diyor.socialmediaapi.enums.Role;
import kg.diyor.socialmediaapi.enums.Status;
import kg.diyor.socialmediaapi.exception.NoAccessException;
import kg.diyor.socialmediaapi.exception.NotFoundException;
import kg.diyor.socialmediaapi.exception.TokenNotValidException;
import kg.diyor.socialmediaapi.exception.UserAlreadyExistException;
import kg.diyor.socialmediaapi.model.RefreshToken;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.RefreshTokenRepository;
import kg.diyor.socialmediaapi.repository.UserRepository;
import kg.diyor.socialmediaapi.service.AuthenticationService;
import kg.diyor.socialmediaapi.service.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {
    final UserRepository userRepository;

    final PasswordEncoder passwordEncoder;

    final JwtService jwtService;

    final AuthenticationManager authenticationManager;

    final RefreshTokenRepository refreshTokenRepository;

    @Value(value = "${app.refreshExpirationInMs}")
    long refreshExpirationInMs;

    @Override
    public ResponseEntity<String> register(RequestUserDTO userDTO) throws UserAlreadyExistException {
        if (userRepository.existsByEmail(userDTO.getEmail()))
            throw new UserAlreadyExistException("User with such email already exists");

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords don't match");
        }

        userRepository.save(buildUser(userDTO));

        return ResponseEntity.ok("Account was successfully registered");
    }

    @Override
    public AuthenticationResponse authenticate(String email, String password) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with such email wasn't found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new NoAccessException("Write password correctly");
        }

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = generateRefreshToken(user);
        if (refreshTokenRepository.existsByUser(user)) {
            refreshToken.setId(refreshTokenRepository.findByUser(user).getId());
        }
        refreshTokenRepository.save(refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(String refreshToken) {
        RefreshToken refToken = refreshTokenRepository.findByToken(refreshToken)
                .map(this::verifyExpiration)
                .orElseThrow(
                        () -> new TokenNotValidException("Token is not valid")
                );

        return AuthenticationResponse.builder()
                .accessToken(jwtService.generateToken(refToken.getUser()))
                .refreshToken(refToken.getToken())
                .build();
    }

    @Override
    public ResponseEntity<String> logout(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user);
        refreshTokenRepository.delete(refreshToken);
        return ResponseEntity.ok("Logged out successfully");
    }

    private User buildUser(RequestUserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
    }

    private RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshExpirationInMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        return refreshToken;
    }

    private RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenNotValidException("Token is already not valid");
        }

        return token;
    }
}
