package com.example.notice.board.domain.response;

import com.example.notice.board.domain.entity.ClassBoard;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClassBoardRes {

    private Long id;
    private String userId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String fileUrl;
    private Long lectureId;

    public ClassBoardRes(ClassBoard classBoard) {
        this.id = classBoard.getId();
        this.userId = classBoard.getUserId();
        this.email = classBoard.getEmail();
        this.title = classBoard.getTitle();
        this.createAt = classBoard.getCreateAt();
        this.updateAt = classBoard.getUpdateAt();
        this.fileUrl = classBoard.getFileUrl();
        this.lectureId = classBoard.getLectureId();
    }
}
