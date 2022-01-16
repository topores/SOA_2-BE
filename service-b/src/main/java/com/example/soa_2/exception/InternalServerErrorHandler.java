package com.example.soa_2.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorHandler extends ExceptionHandler
        implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        return buildExceptionResponse(exception, 500);
    }
}