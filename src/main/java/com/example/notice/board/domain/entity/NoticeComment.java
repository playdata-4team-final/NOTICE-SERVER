package com.example.notice.board.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class NoticeComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userEmail;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long adminBoardId;

    public void deleteNoticeComment(){
        this.comments = "삭제된 게시물입니다.";
        this.userEmail ="비공개";
    };

    public void changeNoticeComment(String comments){
        this.comments = comments;
    };

}
