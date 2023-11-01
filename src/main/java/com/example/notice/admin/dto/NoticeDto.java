package com.example.notice.admin.dto;

import com.example.notice.board.domain.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NoticeDto {

    private String id;
    private Long noticeId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateTime;
    private String fileUrl;

    public NoticeDto(String id, Long noticeId, String email, String title, LocalDateTime createAt, LocalDateTime updateTime, String fileUrl) {
        this.id = id;
        this.noticeId = noticeId;
        this.email = email;
        this.title = title;
        this.createAt = createAt;
        this.updateTime = updateTime;
        this.fileUrl = fileUrl;
    }

    public NoticeDto(Notice notice) {
        this.id = notice.getAdminId();
        this.noticeId = notice.getId();
        this.email = notice.getEmail();
        this.title = notice.getTitle();
        this.createAt = notice.getCreateAt();
        this.updateTime = notice.getUpdateTime();
        this.fileUrl = notice.getFileUrl();

    }

    public void changeFileUrl(String fileUrl){
        this.fileUrl = fileUrl;
    }

    public void deleteFileUrl(){
        this.fileUrl = "파일을 올려주세요";
    }

    public void changeUpdateTime(){
        this.updateTime = LocalDateTime.now();
    }

}
