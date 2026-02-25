package com.example.Spring_back.Reply;

import com.example.Spring_back.Reply.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
