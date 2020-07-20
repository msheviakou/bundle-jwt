package com.msheviakou.bundlejwt.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class PasswordException extends HttpException {

    public PasswordException(String message) { super(message, BAD_REQUEST); }
}
