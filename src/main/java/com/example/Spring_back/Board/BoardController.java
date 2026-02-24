package com.example.Spring_back.Board;


import com.example.Spring_back.Board.model.Board;
import com.example.Spring_back.Board.model.BoardDto;
import com.example.Spring_back.User.UserRepository;
import com.example.Spring_back.User.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity posting(@RequestBody BoardDto.PostReq dto, Authentication authentication) {

        String email = authentication.getName();
        System.out.println(email);

        System.out.println("현재 로그인한 유저: " + authentication.getName()); // 콘솔에 찍히는 값 확인

        // 2. DB에서 실제 유저 엔티티 조회
        User writer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        dto.setWriter(writer);
        boardService.post(dto);

        System.out.println(writer.getUser_id());

        return ResponseEntity.ok("성공");
    }
    @GetMapping("/list")
    public ResponseEntity<List<Board>> list_all() {
        List<Board> list = boardService.list();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail/{idx}")
    public ResponseEntity<Board> board_detail(@PathVariable("idx") Long idx) {
        try {
            // 서비스 계층에서 idx로 게시글 조회
            Board boardDto = boardService.getPostById(idx);
            return ResponseEntity.ok(boardDto);
        } catch (RuntimeException e) {
            return ResponseEntity.ok(null);
        }
    }
}
