package com.example.notice.board.repository;

import com.example.notice.board.domain.entity.ClassMiniComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassMiniCommentRepository extends JpaRepository<ClassMiniComment,Long> {
        }
