package com.example.notice.board.repository;

import com.example.notice.board.domain.entity.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeCommentRepository
        extends JpaRepository<NoticeComment, Long> {
}
