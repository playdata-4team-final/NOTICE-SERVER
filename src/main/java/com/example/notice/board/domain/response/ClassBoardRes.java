package com.example.notice.board.domain.response;

import com.example.notice.board.domain.entity.ClassBoard;
import com.example.notice.board.domain.entity.ClassFile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ClassBoardRes {

    private Long id;
    private String userId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<ClassFile> classFiles;
    private Long lectureId;

    public ClassBoardRes(ClassBoard classBoard) {
        this.id = classBoard.getId();
        this.userId = classBoard.getUserId();
        this.email = classBoard.getEmail();
        this.title = classBoard.getTitle();
        this.createAt = classBoard.getCreateAt();
        this.updateAt = classBoard.getUpdateAt();
        this.classFiles = classBoard.getClassFiles();
        this.lectureId = classBoard.getLectureId();
    }
}
