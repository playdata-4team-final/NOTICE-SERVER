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

    private String userId;
    private Long noticeId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String fileUrl;

    public NoticeDto(String userId, Long noticeId, String email, String title, LocalDateTime createAt, LocalDateTime updateAt, String fileUrl) {
        this.userId = userId;
        this.noticeId = noticeId;
        this.email = email;
        this.title = title;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.fileUrl = fileUrl;
    }

    public NoticeDto(Notice notice) {
        this.userId = notice.getAdminId();
        this.noticeId = notice.getId();
        this.email = notice.getEmail();
        this.title = notice.getTitle();
        this.createAt = notice.getCreateAt();
        this.updateAt = notice.getUpdateAt();
        this.fileUrl = notice.getFileUrl();

    }

    public void changeFileUrl(String fileUrl){
        this.fileUrl = fileUrl;
    }

    public void deleteFileUrl(){
        this.fileUrl = "파일을 올려주세요";
    }

    public void changeUpdateTime(){
        this.updateAt = LocalDateTime.now();
    }

}
