package com.example.notice.admin.dto;

import com.example.notice.board.domain.entity.ClassMiniComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClassMiniCommentDto {
    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long classBoardId;
    private Long classCommentId;

    public ClassMiniCommentDto(Long id, String userId, String comments, LocalDateTime createAt, LocalDateTime updateAt, Long classBoardId, Long classCommentId) {
        this.id = id;
        this.userId = userId;
        this.comments = comments;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.classBoardId = classBoardId;
        this.classCommentId = classCommentId;
    }

    public ClassMiniCommentDto(ClassMiniComment classMiniComment) {
        this.id = classMiniComment.getId();
        this.userId = classMiniComment.getUserId();
        this.comments = classMiniComment.getComments();
        this.createAt = classMiniComment.getCreateAt();
        this.updateAt = classMiniComment.getUpdateAt();
        this.classBoardId = classMiniComment.getClassBoardId();
        this.classCommentId = classMiniComment.getClassCommentId();
    }

    public ClassMiniComment toEntity(){
       return ClassMiniComment
               .builder()
               .id(id)
               .userId(userId)
               .comments(comments)
               .createAt(createAt)
               .updateAt(updateAt)
               .classBoardId(classBoardId)
               .classCommentId(classCommentId)
               .build();
    }
}
