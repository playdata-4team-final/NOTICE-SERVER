package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassMiniComment;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ClassMiniCommentRequest {
    private String userId;
    private Long classBoarId;
    private Long classCommentId;
    private String comment;

    public ClassMiniComment toEntity(){
        return ClassMiniComment
                .builder()
                .userId(userId)
                .classBoardId(classBoarId)
                .classCommentId(classCommentId)
                .comments(comment)
                .createAt(LocalDateTime.now())
                .build();
    }

}