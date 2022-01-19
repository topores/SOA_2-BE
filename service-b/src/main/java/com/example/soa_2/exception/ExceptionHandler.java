package com.example.soa_2.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.exception.ConstraintViolationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ExceptionHandler implements ExceptionMapper<RuntimeException> {

    private static Response.Status status;

    @SneakyThrows
    @Override
    public Response toResponse(RuntimeException exception) {
        return Arrays.stream(this.getClass().getDeclaredMethods())
                     .filter(m -> m.getName().startsWith("register"))
                     .map(method -> {
                         List<Class<? extends Throwable>> applicableExceptions = Arrays.stream(method.getAnnotation(Handle.class).value())
                                                                                       .collect(Collectors.toList());
                         status = method.getAnnotation(Handle.class).status();
                         return applicableExceptions.stream()
                                                    .filter(ae -> exception.getClass().equals(ae))
                                                    .collect(Collectors.toList());
                     })
                     .flatMap(Collection::stream)
                     .map(ex -> buildResponseException(ex.cast(exception), status))
                     .findFirst().orElseGet(() -> buildDefaultResponseException(exception));
    }

    protected Response buildResponseException(Throwable exception, Response.Status status) {
        return Response
                .status(status)
                .entity(ApiErrorResponse.build(exception, status))
                .build();
    }

    private Response buildDefaultResponseException(Throwable exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ApiErrorResponse.build(exception, Response.Status.BAD_REQUEST))
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiErrorResponse implements Serializable {
        private String message;
        private String reasonPhrase;
        private int status;

        public static ApiErrorResponse build(Throwable exception, Response.Status status) {
            return ApiErrorResponse.builder()
                                   .message(adjustExceptionMessage(exception))
                                   .status(status.getStatusCode())
                                   .reasonPhrase(status.getReasonPhrase())
                                   .build();
        }

        private static String adjustExceptionMessage(Throwable exception) {
            if (exception.getCause() != null) {
                Throwable cause = exception.getCause();
                if (exception.getCause().getClass().isAssignableFrom(ConstraintViolationException.class)) {
                    String message = ((ConstraintViolationException) cause).getSQLException().getMessage();
                    return message.substring(message.indexOf("Key"));
                } else if (exception.getCause().getClass().isAssignableFrom(ParseException.class)) {
                    return ((ParseException) cause).getMessage();
                } else {
                    return exception.getMessage();
                }
            } else {
                return exception.getMessage();
            }
        }
    }
}
