package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.Notice;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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