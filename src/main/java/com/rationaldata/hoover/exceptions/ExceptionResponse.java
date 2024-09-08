package com.rationaldata.hoover.exceptions;

import jakarta.ws.rs.core.Response;

public class ExceptionResponse {
    private ExceptionCodeEnum errorCode;

    public ExceptionResponse(ExceptionCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    public Response toResponse(Response.Status status) {
        return Response.status(status).entity(this).build();
    }

    public ExceptionCodeEnum getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "code=" + errorCode +
                '}';
    }
}
