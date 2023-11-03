package com.example.notice.board.domain.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class NoticeFileRequest {

    private String adminId;
    private Long noticeId;
    private String fileUrl;
}
