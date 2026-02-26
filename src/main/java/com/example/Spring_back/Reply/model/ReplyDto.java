package com.example.Spring_back.Reply.model;

import com.example.Spring_back.Board.model.Board;
import com.example.Spring_back.Board.model.BoardDto;
import com.example.Spring_back.User.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReplyDto {

    @Getter
    public static class ReplyReq {
        private String content;

        public Reply toEntity(Long board_idx, Long user_idx) {
            return Reply.builder()
                    .board(Board.builder()
                            .idx(board_idx)
                            .build()
                    )
                    .user(User.builder()
                            .idx(user_idx)
                            .build())
                    .content(this.content)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ReplyRes {
        private Long board_idx;
        private String username;
        private String content;
        private LocalDateTime createdAt;

        public static ReplyRes form(Reply entity) {
            return ReplyRes.builder()
                    .board_idx(entity.getBoard().getIdx())
                    .username(entity.getUser().getUser_id())
                    .content(entity.getContent())
                    .createdAt(entity.getCreatedAt())
                    .build();
        }
    }
}
