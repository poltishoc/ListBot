package test.spisoktest.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @Column(nullable = false)
    private long userId;

    public Message(String content, long userId) {
        this.content = content;
        this.userId = userId;
    }

}
