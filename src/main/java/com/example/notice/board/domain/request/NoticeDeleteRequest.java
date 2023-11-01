package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NoticeDeleteRequest {
    private Long id;
    private String adminId;
    public Notice toEntity() {
        return Notice.builder()
                .id(id)
                .adminId(adminId)
                .build();
    }
}