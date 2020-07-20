package com.msheviakou.bundlejwt.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

public class DuplicateResourceException extends HttpException {

    public DuplicateResourceException(String message) { super(message, CONFLICT); }
}
