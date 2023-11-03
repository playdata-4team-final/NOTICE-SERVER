package com.example.notice.admin.dto;

import com.example.notice.board.domain.entity.Notice;
import com.example.notice.board.domain.entity.NoticeComment;

import java.time.LocalDateTime;

public class NoticeCommentDto {
    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long adminBoardId;

    public NoticeCommentDto(NoticeComment noticeComment) {
        this.id = noticeComment.getId();
        this.userId = noticeComment.getUserId();
        this.comments = noticeComment.getComments();
        this.createAt = noticeComment.getCreateAt();
        this.updateAt = noticeComment.getUpdateAt();
        this.adminBoardId = noticeComment.getAdminBoardId();
    }

    public NoticeCommentDto(Long id, String userId, String comments, LocalDateTime createAt, LocalDateTime updateAt, Long adminBoardId) {
        this.id = id;
        this.userId = userId;
        this.comments = comments;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.adminBoardId = adminBoardId;
    }

    public NoticeComment toEntity(){
        return NoticeComment
                .builder()
                .id(id)
                .userId(userId)
                .comments(comments)
                .createAt(createAt)
                .updateAt(updateAt)
                .adminBoardId(adminBoardId)
                .build();
    }
}
