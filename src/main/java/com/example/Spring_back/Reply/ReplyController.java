package com.example.Spring_back.Reply;

import com.example.Spring_back.Reply.model.Reply;
import com.example.Spring_back.Reply.model.ReplyDto;
import com.example.Spring_back.User.model.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;

    @RequestMapping("/reply/{board_idx}")
    private ResponseEntity post(
            @AuthenticationPrincipal AuthUserDetails user,
            @PathVariable Long board_idx,
            @RequestBody ReplyDto.ReplyReq dto) {

        ReplyDto.ReplyRes result = replyService.save(board_idx, user.getIdx(), dto);

        return ResponseEntity.ok(
                result
        );
    }
}
