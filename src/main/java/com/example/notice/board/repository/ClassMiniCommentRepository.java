package com.example.notice.board.repository;

import com.example.notice.admin.dto.ClassMiniCommentDto;
import com.example.notice.admin.dto.NoticeMiniCommentDto;
import com.example.notice.board.domain.entity.ClassMiniComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClassMiniCommentRepository
        extends JpaRepository<ClassMiniComment,Long> {

        @Query("SELECT c from ClassMiniComment as c WHERE c.classBoardId = :id and c.classCommentId = :commentId")
        Optional<ClassMiniCommentDto> findByClassIdComAndClassCommentId(@Param("id") Long id, @Param("commentId") Long commentId );
}
