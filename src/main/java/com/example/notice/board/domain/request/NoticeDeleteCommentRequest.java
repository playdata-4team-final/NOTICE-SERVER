package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.NoticeComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeDeleteCommentRequest {
    private Long id;
    private String userId;
    private Long adminBoarId;

    public NoticeComment toEntity(){
       return NoticeComment
               .builder()
               .id(id)
               .userId(userId)
               .adminBoardId(adminBoarId)
               .build();
    }
}


