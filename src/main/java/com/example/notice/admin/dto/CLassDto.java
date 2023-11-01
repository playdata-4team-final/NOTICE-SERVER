package com.example.notice.admin.dto;

import com.example.notice.board.domain.entity.ClassBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CLassDto {

    private String userId;
    private Long classId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateTime;
    private String fileUrl;

    public CLassDto(String userId, Long classId, String email, String title, LocalDateTime createAt, LocalDateTime updateTime, String fileUrl) {
        this.userId = userId;
        this.classId = classId;
        this.email = email;
        this.title = title;
        this.createAt = createAt;
        this.updateTime = updateTime;
        this.fileUrl = fileUrl;
    }
    public CLassDto(ClassBoard classBoard) {
        this.userId= classBoard.getUserId();
        this.classId = classBoard.getId();
        this.email = classBoard.getEmail();
        this.title = classBoard.getTitle();
        this.createAt = classBoard.getCreateAt();
        this.updateTime = classBoard.getUpdateAt();
        this.fileUrl = classBoard.getFileUrl();
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
