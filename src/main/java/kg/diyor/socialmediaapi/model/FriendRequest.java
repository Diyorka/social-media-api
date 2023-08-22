package kg.diyor.socialmediaapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "from_user_id")
    User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    User toUser;

    @CreationTimestamp
    LocalDateTime requestDate;
}
