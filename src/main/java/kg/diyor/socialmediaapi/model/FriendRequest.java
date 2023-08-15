package kg.diyor.socialmediaapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Entity
@Table(name = "friend_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendRequest extends BaseEntity{
    @ManyToOne
    User fromUser;

    @ManyToOne
    User toUser;

    @CreationTimestamp
    LocalDateTime requestDate;
}
