package com.example.Spring_back.Reply;

import com.example.Spring_back.Reply.model.Reply;
import com.example.Spring_back.Reply.model.ReplyDto;
import com.example.Spring_back.User.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyDto.ReplyRes save(Long board_idx, Long user_idx, ReplyDto.ReplyReq dto) {

        Reply reply = dto.toEntity(board_idx, user_idx);
        reply = replyRepository.save(reply);

        return ReplyDto.ReplyRes.form(reply);
    }
}
