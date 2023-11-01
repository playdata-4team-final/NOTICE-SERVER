package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ClassUpdateCommentRequest {
    private String userId;
    private Long classBoarId;
    private String comment;

    public ClassComment toEntity(){
       return ClassComment
               .builder()
               .userId(userId)
               .comments(comment)
               .classBoardId(classBoarId)
               .createAt(LocalDateTime.now())
               .build();
    }
}


