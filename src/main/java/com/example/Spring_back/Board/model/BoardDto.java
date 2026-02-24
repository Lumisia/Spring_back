package com.example.Spring_back.Board.model;

import com.example.Spring_back.User.model.User;
import lombok.Getter;
import lombok.Setter;

public class BoardDto {

    @Getter
    public static class PostReq {
        private Long idx;
        private String title;
        private String content;
        private String category;
        @Setter
        private User writer;

        public Board toEntity() {
            return Board.builder()
                    .idx(this.idx)
                    .title(this.title)
                    .content(this.content)
                    .category(this.category)
                    .writer(this.writer)
                    .build();
        }
    }
}
