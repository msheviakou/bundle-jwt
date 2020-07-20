package com.msheviakou.bundlejwt.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class TokenException extends HttpException {

    public TokenException(String message) { super(message, BAD_REQUEST); }
}
