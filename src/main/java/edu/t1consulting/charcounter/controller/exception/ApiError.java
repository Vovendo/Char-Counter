package edu.t1consulting.charcounter.controller.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ApiError {

    private HttpStatus status;
    private OffsetDateTime timestamp;
    private List<String> errors;
    private String message;

    public ApiError(HttpStatus status, OffsetDateTime timestamp, List<String> errors, String message) {
        this.status = status;
        this.timestamp = timestamp;
        this.errors = errors;
        this.message = message;
    }

    public ApiError(HttpStatus status, OffsetDateTime timestamp, String message, String error) {
        super();
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        errors = Collections.singletonList(error);
    }
}
