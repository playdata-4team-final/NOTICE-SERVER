package com.example.notice.board.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClassBoard {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long lectureId;

    @OneToMany(mappedBy = "classBoard")
    private List<ClassFile> classFiles;

}
