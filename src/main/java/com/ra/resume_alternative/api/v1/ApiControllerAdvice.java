package com.ra.resume_alternative.api.v1;
import java.time.LocalDateTime;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiControllerAdvice {
    public class ErrorMessage {
        @JsonProperty
        HttpStatus status;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime timestamp;
        @JsonProperty
        String message;
        @JsonProperty
        String debugMessage;
        @JsonProperty
        String path;

        public ErrorMessage(HttpStatus status, String message,String path,  Throwable ex) {
            this.status = status;
            this.timestamp = LocalDateTime.now();
            this.message = message;
            // this.debugMessage = ex.getLocalizedMessage();
            this.path = path;
        }
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException e, HttpServletRequest req) {
        return ResponseEntity.badRequest().body(
            new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                "Malformed Request", 
                req.getRequestURI().toString(), 
                e));
    }
}
