package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.diyor.socialmediaapi.dto.post.ResponsePostDTO;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@RestController
@RequestMapping("/api/posts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(
        name = "Контроллер для работы с постами",
        description = "В этом контроллере есть возможности добавления, изменения, получения и удаления постов"
)
public class PostController {
    PostService postService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Добавление поста"
    )
    public ResponseEntity<ResponsePostDTO> addPost(@RequestParam String title,
                                                   @RequestParam String content,
                                                   @RequestPart(required = false) MultipartFile image,
                                                   @AuthenticationPrincipal User user) {
        return postService.addPost(title, content, image, user);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Обновление поста"
    )
    public ResponseEntity<ResponsePostDTO> updatePost(@PathVariable Long id,
                                                   @RequestParam String title,
                                                   @RequestParam String content,
                                                   @RequestPart(required = false) MultipartFile image,
                                                   @AuthenticationPrincipal User user) {
        return postService.updatePost(id, title, content, image, user);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Удаление поста"
    )
    public ResponseEntity<String> deletePostById(@PathVariable Long id,
                                                 @AuthenticationPrincipal User user){
        return postService.deletePost(id, user);
    }

    @GetMapping("/my-posts")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получение постов пользователя"
    )
    public List<ResponsePostDTO> getMyPosts(){
        return postService.getMyPosts();
    }

    @GetMapping("/of/{user_id}")
    @Operation(
            summary = "Получение постов пользователя по айди"
    )
    public List<ResponsePostDTO> getUsersPosts(@PathVariable Long user_id){
        return postService.getUsersPosts(user_id);
    }

    @GetMapping("/of-subscribed-users")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получение постов всех пользователей, на которых подписан, сортируя по дате"
    )
    public Page<ResponsePostDTO> getSubscribedUsersPosts(@PageableDefault Pageable pageable,
                                                         @AuthenticationPrincipal User user){
        return postService.getSubscribedUsersPosts(user, pageable);
    }

}
