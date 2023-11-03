package com.example.notice.board.repository;

import com.example.notice.admin.dto.NoticeDto;
import com.example.notice.admin.dto.NoticeMiniCommentDto;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoticeMiniCommentRepository
        extends JpaRepository<NoticeMiniComment,Long> {

    @Query("SELECT n from NoticeMiniComment as n WHERE n.adminBoardId = :id and n.noticeCommentId = : commentId")
    Optional<NoticeMiniCommentDto> findByNoticeIdandComAndNoticeCommentId(@Param("id") Long id, @Param("commentId") Long commentId );


}
