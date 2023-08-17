package kg.diyor.socialmediaapi.service.Impl;

import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.UserRepository;
import kg.diyor.socialmediaapi.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static kg.diyor.socialmediaapi.dto.user.ResponseUserDTO.toResponseUserDTO;
import static kg.diyor.socialmediaapi.dto.user.ResponseUserDTO.toResponseUserDTOs;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Override
    public ResponseUserDTO getMyInfo(User user) {
        return toResponseUserDTO(user);
    }

    @Override
    public List<ResponseUserDTO> searchUserByName(String name, User user) {
        return toResponseUserDTOs(userRepository.findAllByNameContainsIgnoreCase(name)
                .stream().filter(u -> !u.equals(user)).toList());
    }
}
