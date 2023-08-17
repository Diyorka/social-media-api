package kg.diyor.socialmediaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.diyor.socialmediaapi.dto.friendRequest.ResponseFriendRequestDTO;
import kg.diyor.socialmediaapi.model.User;
import kg.diyor.socialmediaapi.repository.FriendRequestRepository;
import kg.diyor.socialmediaapi.service.FriendRequestService;
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
@RequestMapping("/api/friend-requests")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Контроллер для работы с запросами в друзья",
        description = "В этом контроллере есть возможности отправления, получения и отмены запросов в друзья"
)
public class FriendRequestController {
    FriendRequestService friendRequestService;

    @PostMapping("/send-request/{user_id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Отправить запрос в друзья по айди пользователя"
    )
    public ResponseEntity<String> sendRequest(@PathVariable Long user_id,
                                              @AuthenticationPrincipal User user){
        return friendRequestService.sendRequest(user_id, user);
    }

    @PostMapping("/accept/{request_id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Принять запрос в друзья"
    )
    public ResponseEntity<String> acceptRequest(@PathVariable Long request_id,
                                                @AuthenticationPrincipal User user){
        return friendRequestService.acceptRequest(request_id, user);
    }

    @PostMapping("/decline/{request_id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Отклонить запрос в друзья"
    )
    public ResponseEntity<String> declineRequest(@PathVariable Long request_id,
                                                @AuthenticationPrincipal User user){
        return friendRequestService.declineRequest(request_id, user);
    }

    @DeleteMapping("/cancel/{request_id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Удалить/отменить запрос в друзья"
    )
    public ResponseEntity<String> cancelRequest(@PathVariable Long request_id,
                                                @AuthenticationPrincipal User user){
        return friendRequestService.cancelRequest(request_id, user);
    }

    @GetMapping("/my")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получить отправленные запросы пользователя"
    )
    public List<ResponseFriendRequestDTO> getMyRequests(@AuthenticationPrincipal User user){
        return friendRequestService.getMyRequests(user);
    }

    @GetMapping("/received")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Посмотреть полученные запросы в друзья"
    )
    public List<ResponseFriendRequestDTO> getReceivedRequests(@AuthenticationPrincipal User user){
        return friendRequestService.getReceivedRequests(user);
    }

}
