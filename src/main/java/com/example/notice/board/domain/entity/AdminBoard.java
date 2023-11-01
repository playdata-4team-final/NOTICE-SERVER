package com.example.notice.board.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@RequiredArgsConstructor
@Builder
public class AdminBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String adminId;
    @Column(unique = true)
    private String email;
    private String title;
    private String comment;
    private LocalDateTime creteAt;
    private LocalDateTime updateTime;
    @Column(columnDefinition = "파일을 올려주세요.")
    private String fileUrl;

    public void changeFileUrl(AdminBoard adminBoard){
        this.fileUrl = adminBoard.getFileUrl();
    }

    public void changeUpdateTime(){
        this.updateTime = LocalDateTime.now();
    }

}
