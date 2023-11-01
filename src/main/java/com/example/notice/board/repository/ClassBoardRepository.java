package com.example.notice.board.repository;

import com.example.notice.board.domain.entity.AdminBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassBoardRepository
        extends JpaRepository<AdminBoard, Long> {


}
