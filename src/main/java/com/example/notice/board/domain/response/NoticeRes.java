package com.example.notice.board.domain.response;

import com.example.notice.admin.dto.NoticeDto;
import com.example.notice.board.domain.entity.Notice;
import com.example.notice.board.domain.entity.NoticeFile;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class NoticeRes {
    private Long noticeId;
    private String userId;
    private String email;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<NoticeFile> noticeFiles;

    public NoticeRes(Notice noticeDto) {
        this.noticeId = noticeDto.getId();
        this.userId = noticeDto.getUserId();
        this.email = noticeDto.getEmail();
        this.title = noticeDto.getEmail();
        this.content =noticeDto.getContent();
        this.createAt = noticeDto.getCreateAt();
        this.updateAt = noticeDto.getUpdateAt();
        this.noticeFiles = noticeDto.getNoticeFiles();
    }
}
