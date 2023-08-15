package kg.diyor.socialmediaapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@Entity
@Table(name = "friendship")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Friendship extends BaseEntity{
    @ManyToOne
    User user;

    @ManyToOne
    User friend;
}
