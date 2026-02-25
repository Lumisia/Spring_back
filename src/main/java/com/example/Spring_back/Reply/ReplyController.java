package com.example.Spring_back.Reply;

import com.example.Spring_back.Reply.model.Reply;
import com.example.Spring_back.Reply.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/reply")
@RequiredArgsConstructor
@RestController
public class ReplyController {
//    private final ReplyService replyService;

    @RequestMapping("/post")
    private static void post(ReplyDto.ReplyReq dto) {
    }

}
