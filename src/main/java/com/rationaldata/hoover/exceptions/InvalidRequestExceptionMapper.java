package com.rationaldata.hoover.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Provider
public class InvalidRequestExceptionMapper {
    @ServerExceptionMapper
    public RestResponse<ExceptionResponse> mapException(InvalidRequestException ex) {
        return RestResponse.status(Response.Status.BAD_REQUEST, new ExceptionResponse(ex.getErrorCode()));
    }
}
