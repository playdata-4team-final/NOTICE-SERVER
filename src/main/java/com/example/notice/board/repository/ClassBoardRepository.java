package com.example.notice.board.repository;

import com.example.notice.admin.dto.CLassDto;

import com.example.notice.board.domain.entity.ClassBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassBoardRepository
        extends JpaRepository<ClassBoard, Long> {
    @Query("DELETE from ClassBoard as c WHERE c.id = :id")
    void deleteByClassId(@Param("id") Long id);
    @Query("SELECT c from ClassBoard as c WHERE c.id = :id")
    Optional<CLassDto> findByClassId(@Param("id") Long id);

    @Query("select c from ClassBoard as c where c.userId = :userId and c.id = :classId")
    Optional<CLassDto> findByUserIdandClassId(@Param("userId") String userId, @Param("classId")Long classId);

}
