package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.NoticeComment;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NoticeUpdateMiniCommentRequest {
    private Long miniCommentId;
    private String comment;

    public NoticeMiniComment toEntity(){
        return NoticeMiniComment
                .builder()
                .id(miniCommentId)
                .comments(comment)
                .updateAt(LocalDateTime.now())
                .build();
    }
}
