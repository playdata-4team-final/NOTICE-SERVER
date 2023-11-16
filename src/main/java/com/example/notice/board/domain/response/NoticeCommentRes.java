package com.example.notice.board.domain.response;

import com.example.notice.board.domain.entity.NoticeComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeCommentRes {
    private Long id;
    private String userEmail;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long adminBoardId;

    public NoticeCommentRes(NoticeComment noticeComment) {
        this.id = noticeComment.getId();
        this.userEmail = noticeComment.getUserEmail();
        this.comments = noticeComment.getComments();
        this.createAt = noticeComment.getCreateAt();
        this.updateAt = noticeComment.getUpdateAt();
        this.adminBoardId = noticeComment.getAdminBoardId();
    }
}
