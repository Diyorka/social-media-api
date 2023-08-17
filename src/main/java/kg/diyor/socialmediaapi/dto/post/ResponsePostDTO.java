package kg.diyor.socialmediaapi.dto.post;

import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.Post;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

import static kg.diyor.socialmediaapi.dto.user.ResponseUserDTO.toResponseUserDTO;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponsePostDTO {
    Long id;

    String title;

    String content;

    String imageUrl;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    ResponseUserDTO user;

    public static ResponsePostDTO toResponsePostDTO(Post post){
        return ResponsePostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .user(toResponseUserDTO(post.getUser()))
                .build();
    }

    public static List<ResponsePostDTO> toResponsePostDTOs(List<Post> posts){
        return posts.stream().map(ResponsePostDTO::toResponsePostDTO).toList();
    }
}
