package com.example.Spring_back.Board;

import com.example.Spring_back.Board.model.Board;
import com.example.Spring_back.Board.model.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByTitle(String title);

    Board findByIdx(Long idx);

    Optional<Board> findByContent(String content);
}
