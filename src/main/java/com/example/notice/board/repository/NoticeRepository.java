package com.example.notice.board.repository;

import com.example.notice.admin.dto.NoticeDto;
import com.example.notice.board.domain.entity.Notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository
        extends JpaRepository<Notice, Long> {

    @Query("DELETE from Notice as a WHERE a.id = :id")
    void deleteByNoticeId(@Param("id") Long id);
    @Query("SELECT a from Notice as a WHERE a.id = :id")
    Optional<NoticeDto> findByNoticeId(@Param("id") Long id);

    @Query("select a from Notice as a where a.adminId = :adminId and a.id = :noticeId")
    Optional<NoticeDto> findByAdminIdandNoticeId(@Param("adminId") String adminId, @Param("noticeId")Long noticeId);
}
