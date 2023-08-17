package kg.diyor.socialmediaapi.dto.subscription;

import kg.diyor.socialmediaapi.dto.user.ResponseUserDTO;
import kg.diyor.socialmediaapi.model.Subscription;
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
public class ResponseSubscriptionDTO {
    Long id;

    ResponseUserDTO subscriber;

    ResponseUserDTO user;

    LocalDateTime subscriptionDate;

    public static ResponseSubscriptionDTO toResponseSubscriptionDTO(Subscription subscription){
        return ResponseSubscriptionDTO.builder()
                .id(subscription.getId())
                .user(toResponseUserDTO(subscription.getUser()))
                .subscriber(toResponseUserDTO(subscription.getSubscriber()))
                .subscriptionDate(subscription.getSubscriptionDate())
                .build();
    }

    public static List<ResponseSubscriptionDTO> toResponseSubscriptionDTOs(List<Subscription> subscriptions){
        return subscriptions.stream().map(ResponseSubscriptionDTO::toResponseSubscriptionDTO).toList();
    }
}
