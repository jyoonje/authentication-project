package com.yoonje.authenticationexample.common.advice;

import com.yoonje.authenticationexample.member.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.yoonje.authenticationexample.common.HttpStatusResponseEntity.RESPONSE_BAD_REQUEST;
import static com.yoonje.authenticationexample.common.HttpStatusResponseEntity.RESPONSE_NOT_FOUND;

// 추후 클라이언트에게 예외 메세지 알려주는 문구 추가해야됨.
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<HttpStatus> memberNotFoundException(Exception e){
        return RESPONSE_NOT_FOUND;
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpStatus> MissingRequestHeaderException(Exception e) {
        e.printStackTrace();
        return RESPONSE_BAD_REQUEST;
    }
}
