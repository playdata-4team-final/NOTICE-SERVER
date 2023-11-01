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
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String adminId;
    @Column(unique = true)
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    @Column(columnDefinition = "파일을 올려주세요.")
    private String fileUrl;


}
