package com.example.notice.admin.dto;

import com.example.notice.board.domain.entity.NoticeMiniComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeMiniCommentDto {
    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long adminBoardId;
    private Long noticeCommentId;

    public NoticeMiniCommentDto(NoticeMiniComment noticeMiniComment) {
        this.id = noticeMiniComment.getId();
        this.userId = noticeMiniComment.getUserId();
        this.comments = noticeMiniComment.getComments();
        this.createAt = noticeMiniComment.getCreateAt();
        this.updateAt = noticeMiniComment.getUpdateAt();
        this.adminBoardId = noticeMiniComment.getAdminBoardId();
        this.noticeCommentId = noticeMiniComment.getNoticeCommentId();
    }

    public NoticeMiniCommentDto(Long id, String userId, String comments, LocalDateTime createAt, LocalDateTime updateAt, Long adminBoardId, Long noticeCommentId) {
        this.id = id;
        this.userId = userId;
        this.comments = comments;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.adminBoardId = adminBoardId;
        this.noticeCommentId = noticeCommentId;
    }


    public NoticeMiniComment toEntity(){
        return NoticeMiniComment
                .builder()
                .id(id)
                .userId(userId)
                .comments(comments)
                .createAt(createAt)
                .updateAt(updateAt)
                .adminBoardId(adminBoardId)
                .noticeCommentId(noticeCommentId)
                .build();

    }
}
