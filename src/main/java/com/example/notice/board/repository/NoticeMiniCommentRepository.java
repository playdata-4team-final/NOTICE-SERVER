package com.example.notice.board.repository;

import com.example.notice.admin.dto.NoticeDto;
import com.example.notice.admin.dto.NoticeMiniCommentDto;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NoticeMiniCommentRepository
        extends JpaRepository<NoticeMiniComment,Long> {

    @Query("SELECT n from NoticeMiniComment as n WHERE n.adminBoardId = :id and n.noticeCommentId = :commentId")
    Optional<NoticeMiniCommentDto> findByNoticeIdComAndNoticeCommentId(@Param("id") Long id, @Param("commentId") Long commentId );


}
