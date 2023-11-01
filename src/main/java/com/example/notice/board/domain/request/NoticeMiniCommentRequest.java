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

public class NoticeMiniCommentRequest {
    private String userId;
    private Long adminBoarId;
    private Long noticeCommentId;
    private String comment;

    public NoticeMiniComment toEntity(){
        return NoticeMiniComment
                .builder()
                .userId(userId)
                .adminBoardId(adminBoarId)
                .noticeCommentId(noticeCommentId)
                .comments(comment)
                .build();
    }
}
