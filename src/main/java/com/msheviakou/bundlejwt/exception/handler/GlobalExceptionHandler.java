package com.msheviakou.bundlejwt.exception.handler;

import com.msheviakou.bundlejwt.exception.model.ApiError;
import com.msheviakou.bundlejwt.exception.model.ApiErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

interface GlobalExceptionHandler {

    default ResponseEntity buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    default ResponseEntity buildResponseEntity(ApiErrorDetails apiErrorDetails) {
        return new ResponseEntity<>(apiErrorDetails, HttpStatus.valueOf(apiErrorDetails.getStatus()));
    }
}
