package com.rationaldata.hoover.exceptions;

public class InvalidRequestException extends RuntimeException {
    private ExceptionCodeEnum errorCode;

    public InvalidRequestException(ExceptionCodeEnum errorCode) {
        super("");
        this.errorCode = errorCode;
    }

    public ExceptionCodeEnum getErrorCode() {
        return errorCode;
    }
}
