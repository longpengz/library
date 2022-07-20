package com.longpengz.library.config;

import com.longpengz.restful.bean.API;
import com.longpengz.restful.config.RestExceptionConfig;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionConfig extends RestExceptionConfig {

    @Override
    @ExceptionHandler(value = {Exception.class})
    public API<Object> unknownException(Exception ex) {
        ex.printStackTrace();
        return API.e(500,ex.getMessage());
    }

}
