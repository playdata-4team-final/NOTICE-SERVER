package com.example.notice.global.exception.handler;

import com.example.notice.global.domain.response.LmsResponse;
import com.example.notice.global.exception.ClientException;
import com.example.notice.global.exception.DuplicateException;
import com.example.notice.global.exception.MethodException;
import com.example.notice.global.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@Slf4j
@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientException.class)
    protected LmsResponse<String> handleClientException(
            ClientException ex) {
        String responseBody = "Client Exception: " + ex.getMessage();
        return new LmsResponse<>(HttpStatus.BAD_REQUEST, responseBody,"CLIENT-EXCEPTION" , "ERROR", LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    protected LmsResponse<String> handleNotFondException(
            NotFoundException ex) {
        String responseBody = "NotFound Exception: " + ex.getMessage();
        return new LmsResponse<>(HttpStatus.NOT_FOUND, responseBody,"NOTFOUND-EXCEPTION" , "ERROR", LocalDateTime.now());
    }

    @ExceptionHandler(DuplicateException.class)
    protected LmsResponse<String> handleDuplicateException(
            DuplicateException ex) {
        String responseBody = "Duplicate Exception: " + ex.getMessage();
        return new LmsResponse<>(HttpStatus.BAD_REQUEST, responseBody,"DUPLICATE-EXCEPTION" , "ERROR", LocalDateTime.now());
    }

    @ExceptionHandler(MethodException.class)
    protected LmsResponse<String> handleMethodException(
            MethodException ex) {
        String responseBody = "Method Exception: " + ex.getMessage();
        return new LmsResponse<>(HttpStatus.BAD_REQUEST, responseBody,"METHOD-EXCEPTION" , "ERROR", LocalDateTime.now());
    }


}