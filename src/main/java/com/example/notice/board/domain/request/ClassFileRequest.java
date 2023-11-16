package com.example.notice.board.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassFileRequest {

    private String professorId;
    private Long classId;
    private String fileName;
    private MultipartFile file;
}
