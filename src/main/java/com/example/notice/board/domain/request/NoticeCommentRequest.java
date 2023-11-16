package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.NoticeComment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeCommentRequest {
    private String userId;
    private String userEmail;
    private Long adminBoardId;
    private String comment;

    public NoticeComment toEntity(){
       return NoticeComment
               .builder()
               .userId(userId)
               .userEmail(userEmail)
               .comments(comment)
               .adminBoardId(adminBoardId)
               .createAt(LocalDateTime.now())
               .build();
    }
}


