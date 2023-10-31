package com.example.notice.global.exception;

import lombok.Getter;

@Getter
public class MethodException extends RuntimeException{
    private String errorMsg;

    public MethodException(String s) {
        this.errorMsg = s;
    }

}
