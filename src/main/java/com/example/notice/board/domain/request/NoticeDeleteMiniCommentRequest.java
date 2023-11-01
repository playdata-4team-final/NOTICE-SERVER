package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.NoticeComment;
import com.example.notice.board.domain.entity.NoticeMiniComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeDeleteMiniCommentRequest {
    private Long id;
    private String userId;
    private Long noticeCommentId;
    private Long adminBoarId;

    public NoticeMiniComment toEntity(){
        return NoticeMiniComment
                .builder()
                .id(id)
                .noticeCommentId(noticeCommentId)
                .userId(userId)
                .adminBoardId(adminBoarId)
                .build();
    }
}
