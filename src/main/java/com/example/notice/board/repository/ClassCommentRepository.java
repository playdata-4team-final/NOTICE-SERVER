package com.example.notice.board.repository;

import com.example.notice.board.domain.entity.ClassComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassCommentRepository
        extends JpaRepository<ClassComment, Long> {
}
