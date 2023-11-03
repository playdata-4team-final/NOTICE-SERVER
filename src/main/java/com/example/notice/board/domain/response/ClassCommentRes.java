package com.example.notice.board.domain.response;

import com.example.notice.board.domain.entity.ClassComment;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClassCommentRes {

    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long classBoardId;

    public ClassCommentRes(ClassComment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.comments = comment.getComments();
        this.createAt = comment.getCreateAt();
        this.updateAt = comment.getUpdateAt();
        this.classBoardId = comment.getClassBoardId();
    }
}
