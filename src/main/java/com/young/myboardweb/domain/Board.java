package com.young.myboardweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    private String title;
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(updatable = false)
    private Long cnt = 0L;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.getBoardList().add(this);
    }

}
