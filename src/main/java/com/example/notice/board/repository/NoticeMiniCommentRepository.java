package com.example.notice.board.repository;

import com.example.notice.board.domain.entity.NoticeMiniComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeMiniCommentRepository
        extends JpaRepository<NoticeMiniComment,Long> {
}
