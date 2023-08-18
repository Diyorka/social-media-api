package kg.diyor.socialmediaapi.service.Impl;

import kg.diyor.socialmediaapi.dto.comment.ResponseCommentDTO;
import kg.diyor.socialmediaapi.exception.NoAccessException;
import kg.diyor.socialmediaapi.exception.NotFoundException;
import kg.diyor.socialmediaapi.model.Comment;
import kg.diyor.socialmediaapi.model.Post;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.CommentRepository;
import kg.diyor.socialmediaapi.repository.PostRepository;
import kg.diyor.socialmediaapi.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static kg.diyor.socialmediaapi.dto.comment.ResponseCommentDTO.toResponseCommentDTO;
import static kg.diyor.socialmediaapi.dto.comment.ResponseCommentDTO.toResponseCommentDTOs;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;

    @Override
    public List<ResponseCommentDTO> getPostComments(Long postId) {
        return toResponseCommentDTOs(commentRepository.findAllByPostId(postId));
    }

    @Override
    public ResponseCommentDTO addComment(Long postId, String text, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post with such id wasn't found"));

        Comment comment = Comment.builder()
                .text(text)
                .post(post)
                .user(user)
                .build();

        commentRepository.save(comment);

        return toResponseCommentDTO(comment);
    }

    @Override
    public ResponseCommentDTO updateComment(Long commentId, String newText, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment with such id wasn't found"));

        if(!user.equals(comment.getUser())){
            throw new NoAccessException("You can't update this comment");
        }

        comment.setText(newText);
        commentRepository.save(comment);

        return toResponseCommentDTO(comment);
    }

    @Override
    public ResponseEntity<String> deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment with such id wasn't found "));

        if(!user.equals(comment.getUser())){
            throw new NoAccessException("You can't delete this comment");
        }

        commentRepository.delete(comment);

        return ResponseEntity.ok("Comment was successfully deleted");
    }
}
