package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassBoard;
import com.example.notice.board.domain.entity.ClassMiniComment;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassUpdateMiniCommentRequest {
    private String userId;
    private Long classBoarId;
    private Long commentId;
    private String comment;

    public ClassMiniComment toEntity(){
        return ClassMiniComment
                .builder()
                .userId(userId)
                .classBoardId(classBoarId)
                .classCommentId(commentId)
                .comments(comment)
                .updateAt(LocalDateTime.now())
                .build();
    }

}
