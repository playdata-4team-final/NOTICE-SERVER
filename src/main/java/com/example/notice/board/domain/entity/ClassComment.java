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
public class ClassComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Column(unique = true)
    private Long classBoardId;

    public void changeClassComment(String comments){
        this.comments = comments;
    };

    public void deleteClassComment(){
        this.comments = "삭제된 게시물입니다.";
    };

}
