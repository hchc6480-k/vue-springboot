package com.example.vuespringboot.controller;

import com.example.vuespringboot.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    // @ResponseStatus(HttpStatus.BAD_REQUEST) : 상태코드도 바꿔주고 싶을 때 사용
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // 서블릿 컨테이너까지 지저분하게 가지 않고, 정상 흐름으로 끝날 수 있음
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    // 상태 코드 지정
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    // 그냥 exception 사용
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "오류가 발생했습니다.");
    }


}
