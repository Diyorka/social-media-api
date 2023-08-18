package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.diyor.socialmediaapi.dto.comment.ResponseCommentDTO;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@RestController
@RequestMapping("/api/comments")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(
        name = "Контроллер для работы с комментариями",
        description = "В этом контроллере есть возможности добавления, изменения, получения и удаления комментариев"
)
public class CommentController {
    CommentService commentService;

    @GetMapping("/{postId})")
    @Operation(
            summary = "Получить комментарии к посту"
    )
    public List<ResponseCommentDTO> getPostComments(@PathVariable Long postId){
        return commentService.getPostComments(postId);
    }

    @PostMapping("/add/{postId}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Добавить комментарий к посту"
    )
    public ResponseCommentDTO addComment(@PathVariable Long postId,
                                         @RequestParam String text,
                                         @AuthenticationPrincipal User user){
        return commentService.addComment(postId, text, user);
    }

    @PutMapping("/update/{commentId}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Обновить комментарий к посту"
    )
    public ResponseCommentDTO updateComment(@PathVariable Long commentId,
                                            @RequestParam String newText,
                                            @AuthenticationPrincipal User user){
        return commentService.updateComment(commentId, newText, user);
    }

    @DeleteMapping("/{commentId}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Удалить комментарий"
    )
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId,
                                                @AuthenticationPrincipal User user){
        return commentService.deleteComment(commentId, user);
    }
}
