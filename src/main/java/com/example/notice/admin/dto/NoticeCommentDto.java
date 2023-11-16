package com.example.notice.admin.dto;

import com.example.notice.board.domain.entity.Notice;
import com.example.notice.board.domain.entity.NoticeComment;

import java.time.LocalDateTime;

public class NoticeCommentDto {
    private Long id;
    private String userEmail;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long adminBoardId;

    public NoticeCommentDto(NoticeComment noticeComment) {
        this.id = noticeComment.getId();
        this.userEmail = noticeComment.getUserEmail();
        this.comments = noticeComment.getComments();
        this.createAt = noticeComment.getCreateAt();
        this.updateAt = noticeComment.getUpdateAt();
        this.adminBoardId = noticeComment.getAdminBoardId();
    }

    public NoticeCommentDto(Long id, String userEmail, String comments, LocalDateTime createAt, LocalDateTime updateAt, Long adminBoardId) {
        this.id = id;
        this.userEmail = userEmail;
        this.comments = comments;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.adminBoardId = adminBoardId;
    }

    public NoticeComment toEntity(){
        return NoticeComment
                .builder()
                .id(id)
                .userEmail(userEmail)
                .comments(comments)
                .createAt(createAt)
                .updateAt(updateAt)
                .adminBoardId(adminBoardId)
                .build();
    }
}
