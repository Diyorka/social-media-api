package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.User;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface UserService {
    ResponseUserDTO getMyInfo(User user);

    List<ResponseUserDTO> searchUserByName(String name, User user);

}
