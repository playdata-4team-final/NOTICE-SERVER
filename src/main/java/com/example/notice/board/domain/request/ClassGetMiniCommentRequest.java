package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassMiniComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
