package com.example.notice.admin.dto;

import com.example.notice.board.domain.entity.ClassComment;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClassCommentDto {

    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long classBoardId;

    public ClassCommentDto(Long id, String userId, String comments, LocalDateTime createAt, LocalDateTime updateAt, Long classBoardId) {
        this.id = id;
        this.userId = userId;
        this.comments = comments;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.classBoardId = classBoardId;
    }

    public ClassCommentDto(ClassComment classComment) {
        this.id = classComment.getId();
        this.userId = classComment.getUserId();
        this.comments = classComment.getComments();
        this.createAt = classComment.getCreateAt();
        this.updateAt = classComment.getUpdateAt();
        this.classBoardId = classComment.getClassBoardId();
    }

    public ClassComment toEntity(){
        return ClassComment
                .builder()
                .classBoardId(id)
                .userId(userId)
                .comments(comments)
                .createAt(createAt)
                .updateAt(updateAt)
                .id(id)
                .build();
    }
}
