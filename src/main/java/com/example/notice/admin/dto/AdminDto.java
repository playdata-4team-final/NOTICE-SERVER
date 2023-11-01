package com.example.notice.admin.dto;

import com.example.notice.board.entity.AdminBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AdminDto {

    private String id;
    private String email;
    private String title;
    private String comment;
    private LocalDateTime createAt;
    private LocalDateTime updateTime;
    private String fileUrl;

    public AdminBoard toEntity(){
        return AdminBoard
                .builder()
                .adminId(id)
                .email(email)
                .title(title)
                .comment(comment)
                .creteAt(createAt)
                .updateTime(LocalDateTime.now())
                .build();
    }
}
