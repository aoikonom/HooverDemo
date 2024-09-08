package com.rationaldata.hoover.exceptions;

import jakarta.ws.rs.core.Response;

import java.util.Objects;

public class ExceptionResponse {
    private ExceptionCodeEnum errorCode;

    public ExceptionResponse() {
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionResponse that = (ExceptionResponse) o;
        return errorCode == that.errorCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode);
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "code=" + errorCode +
                '}';
    }
}
