package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NoticeCreateRequest {
    private Long id;
    private String adminId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private String fileUrl;

    public Notice toEntity() {
        return Notice.builder()
                .id(id)
                .adminId(adminId)
                .email(email)
                .title(title)
                .createAt(createAt)
                .updateAt(LocalDateTime.now())
                .build();
    }
}