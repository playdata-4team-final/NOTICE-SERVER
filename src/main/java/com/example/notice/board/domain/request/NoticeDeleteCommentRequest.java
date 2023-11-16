package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.NoticeComment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
               .userEmail("비공개")
               .adminBoardId(adminBoarId)
               .build();
    }
}


