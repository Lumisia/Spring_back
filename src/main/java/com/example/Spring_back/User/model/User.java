package com.example.Spring_back.User.model;

import jakarta.persistence.*;
import lombok.*;

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
}
