package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.post.ResponsePostDTO;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface PostService {
    ResponseEntity<ResponsePostDTO> addPost(String title, String content, MultipartFile image, User user);

    ResponseEntity<ResponsePostDTO> updatePost(Long id, String title, String content, MultipartFile image, User user);

    ResponseEntity<String> deletePost(Long id, User user);

    List<ResponsePostDTO> getMyPosts();

    List<ResponsePostDTO> getUsersPosts(Long userId);

    List<ResponsePostDTO> getSubscribedUsersPosts(User user);
}
