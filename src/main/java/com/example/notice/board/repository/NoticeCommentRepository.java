package com.example.notice.board.repository;

import com.example.notice.admin.dto.NoticeCommentDto;
import com.example.notice.admin.dto.NoticeDto;
import com.example.notice.board.domain.entity.NoticeComment;
import com.example.notice.board.domain.response.NoticeCommentRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NoticeCommentRepository
        extends JpaRepository<NoticeComment, Long> {

    @Query("SELECT n from NoticeComment as n WHERE n.adminBoardId = :id ")
    List<NoticeCommentDto> findByNoticeId(@Param("id") Long id);

    @Query("SELECT n from NoticeComment as n WHERE n.id = :id and n.userId = :userId")
    Optional<NoticeComment> findByUserId(@Param("id") Long id, @Param("userId") String userId);
}
