package com.example.notice.admin.dto;

import com.example.notice.board.domain.entity.ClassBoard;
import com.example.notice.board.domain.entity.ClassFile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<ClassFile> classFiles;

    public CLassDto(String userId, Long classId, String email, String title, LocalDateTime createAt, LocalDateTime updateTime, List<ClassFile> classFiles) {
        this.userId = userId;
        this.classId = classId;
        this.email = email;
        this.title = title;
        this.createAt = createAt;
        this.updateTime = updateTime;
        this.classFiles = classFiles;
    }
    public CLassDto(ClassBoard classBoard) {
        this.userId= classBoard.getUserId();
        this.classId = classBoard.getId();
        this.email = classBoard.getEmail();
        this.title = classBoard.getTitle();
        this.createAt = classBoard.getCreateAt();
        this.updateTime = classBoard.getUpdateAt();
        this.classFiles = classBoard.getClassFiles();
    }

    public ClassBoard toEntity(){
        return ClassBoard
                .builder()
                .userId(userId)
                .id(classId)
                .email(email)
                .title(title)
                .createAt(createAt)
                .updateAt(updateTime)
                .classFiles(classFiles)
                .build();
    }


    public void changeUpdateTime(){
        this.updateTime = LocalDateTime.now();
    }


}
