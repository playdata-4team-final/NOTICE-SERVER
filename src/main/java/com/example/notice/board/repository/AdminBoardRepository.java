package com.example.notice.board.repository;

import com.example.notice.board.domain.entity.AdminBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminBoardRepository
        extends JpaRepository<AdminBoard, Long> {

    @Query("select a from AdminBoard as a where a.adminId = :adminId")
    Optional<AdminBoard> findByAdminId(@Param("adminId") String adminId);
}
