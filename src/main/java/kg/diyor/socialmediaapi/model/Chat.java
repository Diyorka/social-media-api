package kg.diyor.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Entity
@Table(name = "chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "participant1_id")
    User participant1;

    @ManyToOne
    @JoinColumn(name = "participant2_id")
    User participant2;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Message> messages;
}
