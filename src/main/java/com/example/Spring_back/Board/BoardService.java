package com.example.Spring_back.Board;

import com.example.Spring_back.Board.model.Board;
import com.example.Spring_back.Board.model.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository br;

    public void post(BoardDto.PostReq dto) {
        Board post = dto.toEntity();

        br.save(post);
    }
    public List<Board> list() {
        return br.findAll();
    }

    public Board getPostById(Long idx) {

        return br.findByIdx(idx);
    }
}
