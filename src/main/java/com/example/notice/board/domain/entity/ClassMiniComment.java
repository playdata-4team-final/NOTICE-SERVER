package com.example.notice.board.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClassMiniComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long classBoardId;
    private Long classCommentId;

    public void changeClassMiniComment(String comments){
        this.comments = comments;
    };

    public void deleteClassMiniComment(){
        this.comments = "삭제된 게시물입니다.";
    };

}
