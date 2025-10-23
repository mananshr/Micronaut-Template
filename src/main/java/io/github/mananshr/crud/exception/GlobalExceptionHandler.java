package io.github.mananshr.crud.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.data.exceptions.EmptyResultException;
import jakarta.inject.Singleton;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class GlobalExceptionHandler implements ExceptionHandler<Exception, HttpResponse> {
    
    @Override
    public HttpResponse handle(HttpRequest request, Exception exception) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        
        if (exception instanceof ApiException) {
            ApiException apiException = (ApiException) exception;
            status = apiException.getStatus();
        } else if (exception instanceof EmptyResultException) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        response.put("message", exception.getMessage());
        response.put("status", status.getCode());
        response.put("path", request.getPath());
        response.put("timestamp", Instant.now().toString());
        
        return HttpResponse
            .status(status)
            .body(response);
    }
}
