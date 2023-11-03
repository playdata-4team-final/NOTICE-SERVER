package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.Notice;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeGetMiniCommentRequest {
    private Long noticeId;
    private Long commentId;

    public NoticeMiniComment toEntity() {
        return NoticeMiniComment
                .builder()
                .noticeCommentId(commentId)
                .adminBoardId(noticeId)
                .build();
    }
}