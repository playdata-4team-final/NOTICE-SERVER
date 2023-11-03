package com.example.notice.board.domain.response;

import com.example.notice.board.domain.entity.NoticeComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeCommentRes {
    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long adminBoardId;

    public NoticeCommentRes(NoticeComment noticeComment) {
        this.id = noticeComment.getId();
        this.userId = noticeComment.getUserId();
        this.comments = noticeComment.getComments();
        this.createAt = noticeComment.getCreateAt();
        this.updateAt = noticeComment.getUpdateAt();
        this.adminBoardId = noticeComment.getAdminBoardId();
    }
}
