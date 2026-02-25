package com.example.Spring_back.User.model;

import com.example.Spring_back.Board.model.Board;
import com.example.Spring_back.Reply.model.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(unique = true, nullable = false)
    private String email;
    private String user_id;

    @Setter
    private String password;

    @Setter
    private boolean enable;
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boardList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reply> replyList;
}
