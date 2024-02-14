package io.nozistance.dsme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Boolean subscribed;

    private String firstName;
    private String lastName;
}
