package com.example.notice.board.domain.request;

import com.example.notice.board.domain.entity.ClassBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ClassDeleteRequest {
    private Long id;
    private String userId;

    public ClassBoard toEntity() {
        return ClassBoard.builder()
                .id(id)
                .userId(userId)
                .build();
    }
}
