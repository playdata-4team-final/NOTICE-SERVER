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
public class ClassBoard {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String userId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String fileUrl;
    private Long lectureId;

}
