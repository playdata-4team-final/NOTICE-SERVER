package com.example.notice.global.exception;

import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException{
    private String errorMsg;

    public DuplicateException(String s) {
        this.errorMsg = s;
    }

}
