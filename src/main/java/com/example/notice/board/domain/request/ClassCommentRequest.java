package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassComment;
import com.example.notice.board.domain.entity.NoticeComment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassCommentRequest {
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


