package com.example.Spring_back.Board.model;

import com.example.Spring_back.Reply.model.ReplyDto;
import com.example.Spring_back.User.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

    @Getter
    public static class PostReq {
        private Long idx;
        private String title;
        private String content;
        private String category;
        @Setter
        private User user;

        public Board toEntity() {
            return Board.builder()
                    .idx(this.idx)
                    .title(this.title)
                    .content(this.content)
                    .category(this.category)
                    .user(this.user)
                    .build();
        }
    }
    @Builder
    @Getter
    public static class PostRes {
        private Long idx;
        private String title;
        private String content;
        private String category;
        private String email;
        private int viewCount;
        private LocalDateTime createdAt;
        private List<ReplyDto.ReplyRes> replyList;

        public static PostRes form(Board entity) {
            return PostRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .category(entity.getCategory())
                    .email(entity.getUser().getUser_id())
                    .viewCount(entity.getViewCount())
                    .createdAt(entity.getCreatedAt())
                    .replyList(entity.getReply_list().stream().map(ReplyDto.ReplyRes::form).toList())
                    .build();
        }
    }

}
