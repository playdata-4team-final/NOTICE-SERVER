package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.NoticeComment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NoticeCommentRequest {
    private String userId;
    private Long adminBoarId;
    private String comment;

    public NoticeComment toEntity(){
       return NoticeComment
               .builder()
               .userId(userId)
               .comments(comment)
               .adminBoardId(adminBoarId)
               .createAt(LocalDateTime.now())
               .build();
    }
}


