package kg.diyor.socialmediaapi.dto.comment;

import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.Comment;
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
public class ResponseCommentDTO {
    Long id;

    String text;

    ResponseUserDTO user;

    LocalDateTime createdAt;

    public static ResponseCommentDTO toResponseCommentDTO(Comment comment){
        return ResponseCommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .user(toResponseUserDTO(comment.getUser()))
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static List<ResponseCommentDTO> toResponseCommentDTOs(List<Comment> comments){
        return comments.stream().map(ResponseCommentDTO::toResponseCommentDTO).toList();
    }
}
