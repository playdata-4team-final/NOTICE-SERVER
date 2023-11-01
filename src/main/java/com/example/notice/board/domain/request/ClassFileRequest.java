package com.example.notice.board.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClassFileRequest {

    private String adminId;
    private Long noticeId;
    private String fileUrl;
}
