package com.example.notice.board.repository;

import com.example.notice.admin.dto.NoticeCommentDto;
import com.example.notice.admin.dto.NoticeDto;
import com.example.notice.board.domain.entity.NoticeComment;
import com.example.notice.board.domain.response.NoticeCommentRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoticeCommentRepository
        extends JpaRepository<NoticeComment, Long> {

    @Query("SELECT n from NoticeComment as n WHERE n.adminBoardId = :id ")
    Optional<NoticeCommentDto> findByNoticeId(@Param("id") Long id);
}
