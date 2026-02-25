package com.example.Spring_back.Board.model;

import com.example.Spring_back.Reply.model.Reply;
import com.example.Spring_back.User.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 조회수: 기본값 0 설정
//    @Builder.Default
    @Setter
    @Column(nullable = false)
    private int viewCount = 0;

    // 작성일: 생성 시 자동 기록
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // User 엔티티의 idx를 외래키로 참조 (N:1 관계)
    @ManyToOne
    @JoinColumn(name = "user_idx")
    @Setter
    private User user;

    // 데이터 저장 전 실행되는 메서드 (작성일 및 조회수 초기화 방어 로직)
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        // Builder 사용 시 기본값이 무시될 경우를 대비
        if (this.viewCount < 0) this.viewCount = 0;
    }

    // 제목이나 내용 수정을 위한 Setter (필요한 경우만 사용)
    @Setter
    private String category;

    @OneToMany(mappedBy = "board")
    private List<Reply> reply_list;
}