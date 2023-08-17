package kg.diyor.socialmediaapi.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.diyor.socialmediaapi.dto.post.ResponsePostDTO;
import kg.diyor.socialmediaapi.exception.FileEmptyException;
import kg.diyor.socialmediaapi.exception.NoAccessException;
import kg.diyor.socialmediaapi.exception.NotFoundException;
import kg.diyor.socialmediaapi.model.Post;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.PostRepository;
import kg.diyor.socialmediaapi.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static kg.diyor.socialmediaapi.dto.post.ResponsePostDTO.toResponsePostDTO;
import static kg.diyor.socialmediaapi.dto.post.ResponsePostDTO.toResponsePostDTOs;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    PostRepository postRepository;

    @Override
    public ResponseEntity<ResponsePostDTO> addPost(String title, String content, MultipartFile image, User user) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();

        if (image != null && !image.isEmpty()) {
            post.setImageUrl(saveImage(image));
        }

        postRepository.save(post);
        return ResponseEntity.ok(toResponsePostDTO(post));
    }

    @Override
    public ResponseEntity<ResponsePostDTO> updatePost(Long id, String title, String content, MultipartFile image, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post with such id wasn't found"));

        if(!post.getUser().equals(user)){
            throw new NoAccessException("You can't update this post");
        }

        post.setTitle(title);
        post.setContent(content);

        if (image != null && !image.isEmpty()) {
            post.setImageUrl(saveImage(image));
        }

        postRepository.save(post);
        return ResponseEntity.ok(toResponsePostDTO(post));
    }

    @Override
    public ResponseEntity<String> deletePost(Long id, User user) {
        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Post wasn't found"));

        if(!post.getUser().equals(user)){
            throw new NoAccessException("You can't delete this post");
        }

        postRepository.deleteById(id);
        return ResponseEntity.ok("Post was successfully deleted");
    }

    @Override
    public List<ResponsePostDTO> getMyPosts() {
        return toResponsePostDTOs(postRepository.findAll());
    }

    @Override
    public List<ResponsePostDTO> getUsersPosts(Long userId) {
        return toResponsePostDTOs(postRepository.findAllByUserId(userId));
    }

    @Override
    public List<ResponsePostDTO> getSubscribedUsersPosts(User user) {
        return toResponsePostDTOs(postRepository.findAllSubscribedUsersPosts(user));
    }

    @SneakyThrows
    public String saveImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileEmptyException("File can't be empty");
        }

        final String urlKey = "cloudinary://753949556892917:SCszCjA1duCgeAaMxDP-7Qq3dP8@dja0nqat2";

        File saveFile = Files.createTempFile(
                        System.currentTimeMillis() + "",
                        Objects.requireNonNull
                                        (file.getOriginalFilename(), "File should have extension")
                                .substring(file.getOriginalFilename().lastIndexOf("."))
                )
                .toFile();

        file.transferTo(saveFile);

        Cloudinary cloudinary = new Cloudinary((urlKey));

        Map upload = cloudinary.uploader().upload(saveFile, ObjectUtils.emptyMap());

        return (String) upload.get("url");
    }
}
