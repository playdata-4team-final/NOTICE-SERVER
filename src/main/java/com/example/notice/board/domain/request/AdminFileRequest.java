package com.example.notice.board.domain.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AdminFileRequest {

    private String adminId;
    private String fileUrl;
}
