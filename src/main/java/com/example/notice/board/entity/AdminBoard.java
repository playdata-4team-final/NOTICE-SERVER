package com.example.notice.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String adminId;
    private String email;
    private String title;
    private String comment;
    private LocalDateTime creteAt;
    private LocalDateTime updateTime;
    private String fileUrl;

}
