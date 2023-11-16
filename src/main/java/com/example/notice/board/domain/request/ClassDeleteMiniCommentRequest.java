package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassMiniComment;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassDeleteMiniCommentRequest {
    private String userId;
    private Long classBoarId;
    private Long classCommentId;
    private Long id;

    public ClassMiniComment toEntity(){
        return ClassMiniComment
                .builder()
                .id(id)
                .userId(userId)
                .classBoardId(classBoarId)
                .classCommentId(classCommentId)
                .build();
    }

}
