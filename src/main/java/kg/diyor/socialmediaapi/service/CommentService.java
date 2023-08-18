package kg.diyor.socialmediaapi.service;

import kg.diyor.socialmediaapi.dto.comment.ResponseCommentDTO;
import kg.diyor.socialmediaapi.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

public interface CommentService {
    List<ResponseCommentDTO> getPostComments(Long postId);
    ResponseCommentDTO addComment(Long postId, String text, User user);

    ResponseCommentDTO updateComment(Long commentId, String newText, User user);

    ResponseEntity<String> deleteComment(Long commentId, User user);
}
