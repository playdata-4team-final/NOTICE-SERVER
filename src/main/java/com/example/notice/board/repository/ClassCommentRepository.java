package com.example.notice.board.repository;

import com.example.notice.board.domain.entity.ClassComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassCommentRepository
        extends JpaRepository<ClassComment, Long> {
}
