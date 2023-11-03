package com.example.notice.board.domain.response;

import com.example.notice.board.domain.entity.ClassMiniComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClassMiniCommentRes {

    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long classBoardId;
    private Long classCommentId;

    public ClassMiniCommentRes(ClassMiniComment classMiniComment) {
        this.id = classMiniComment.getId();
        this.userId = classMiniComment.getUserId();
        this.comments = classMiniComment.getComments();;
        this.createAt = classMiniComment.getCreateAt();
        this.updateAt = classMiniComment.getUpdateAt();
        this.classBoardId = classMiniComment.getClassBoardId();
        this.classCommentId = classMiniComment.getClassCommentId();
    }
}
