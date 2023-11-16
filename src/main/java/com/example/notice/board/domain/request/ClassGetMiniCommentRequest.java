package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassMiniComment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassGetMiniCommentRequest {
    private Long classBoarId;
    private Long classCommentId;

    public ClassMiniComment toEntity(){
        return ClassMiniComment
                .builder()
                .classBoardId(classBoarId)
                .classCommentId(classCommentId)
                .build();
    }

}
