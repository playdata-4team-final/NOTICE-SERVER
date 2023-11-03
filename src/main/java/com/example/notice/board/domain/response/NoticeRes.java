package com.example.notice.board.domain.response;

import com.example.notice.admin.dto.NoticeDto;
import com.example.notice.board.domain.entity.Notice;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeRes {
    private Long noticeId;
    private String userId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String fileUrl;

    public NoticeRes(Notice noticeDto) {
        this.noticeId = noticeDto.getId();
        this.userId = noticeDto.getUserId();
        this.email = noticeDto.getEmail();
        this.title = noticeDto.getEmail();
        this.createAt = noticeDto.getCreateAt();
        this.updateAt = noticeDto.getUpdateAt();
        this.fileUrl = noticeDto.getFileUrl();
    }
}
