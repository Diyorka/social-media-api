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
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message extends BaseEntity{
    String text;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    User sender;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    Chat chat;

    @CreationTimestamp
    LocalDateTime createdAt;
}
