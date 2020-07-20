package com.msheviakou.bundlejwt.exception.handler;

import com.msheviakou.bundlejwt.exception.HttpException;
import com.msheviakou.bundlejwt.exception.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class HttpExceptionHandler implements GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity handleHttpException(HttpException exception) {
        log.error(exception.getClass().getName() + ":" + exception.getMessage());

        HttpStatus httpStatus = exception.getHttpStatus();
        ApiError apiError = ApiError.builder()
                .status(httpStatus.value())
                .error(httpStatus.name())
                .message(exception.getMessage())
                .build();

        return buildResponseEntity(apiError);
    }
}
