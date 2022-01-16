package com.example.soa_2.exception;

import javax.ws.rs.core.Response;

public class ExceptionHandler {
    protected Response buildExceptionResponse(Exception exception, int code) {
        return Response.status(code).entity(exception.getLocalizedMessage()).build();
    }
}
