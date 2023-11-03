package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ClassCreateRequest {
    private Long id;
    private String userId;
    private String email;
    private String title;
    private LocalDateTime createAt;
    private String fileUrl;

    public ClassBoard toEntity() {
        return ClassBoard.builder()
                .id(id)
                .userId(userId)
                .email(email)
                .title(title)
                .createAt(createAt)
                .updateAt(LocalDateTime.now())
                .build();
    }
}
