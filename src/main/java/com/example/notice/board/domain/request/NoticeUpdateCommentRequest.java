package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.NoticeComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NoticeUpdateCommentRequest {
    private Long noticeId;
    private String comment;

    public NoticeComment toEntity(){
       return NoticeComment
               .builder()
               .id(noticeId)
               .comments(comment)
               .updateAt(LocalDateTime.now())
               .build();
    }
}


