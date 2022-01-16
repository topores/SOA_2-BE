package com.example.soa_2.exception;

import javax.json.bind.JsonbException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonParseExceptionHandler extends ExceptionHandler
        implements ExceptionMapper<JsonbException> {
    @Override
    public Response toResponse(JsonbException exception) {
        return buildExceptionResponse(exception, 400);
    }
}