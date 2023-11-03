package com.example.notice.board.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
@Builder
public class NoticeMiniComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long adminBoardId;
    private Long noticeCommentId;

    public void deleteNoticeMiniComment(){
        this.comments = "삭제된 게시물입니다.";
    };

    public void changeNoticeMiniComment(String comments){
        this.comments = comments;
    };
}
