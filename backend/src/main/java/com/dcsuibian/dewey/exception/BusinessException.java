package com.dcsuibian.dewey.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private int code = 400;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }
}
