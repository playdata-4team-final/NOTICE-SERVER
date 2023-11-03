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

    @Query("DELETE from Notice as n WHERE n.id = :id")
    void deleteByNoticeId(@Param("id") Long id);


    @Query("select n from Notice as n where n.userId = :adminId and n.id = :noticeId")
    Optional<NoticeDto> findByAdminIdandNoticeId(@Param("adminId") String adminId, @Param("noticeId")Long noticeId);

    @Query("SELECT n.fileUrl from Notice as n where n.id = :noticeId")
    Optional<NoticeDto> findFileUrlByNoticeId(@Param("noticeId")Long noticeId);
}
