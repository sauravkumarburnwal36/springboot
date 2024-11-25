package com.begineer.springbootweb.springbootweb.advices;

import com.begineer.springbootweb.springbootweb.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleResourceNotFoundError(ResourceNotFoundException exception){
        APIError apiError=APIError
                .builder().status(HttpStatus.NOT_FOUND)
                .message("Resource Not Found").build();
        return buildApiResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<?>> handleInternalServerError(Exception exception){
        APIError apiError=APIError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildApiResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<?>> handleMethodArgumentNotValidError(MethodArgumentNotValidException exception){
        List<String> errors=exception.getBindingResult().getAllErrors().stream().map(error->error.getDefaultMessage())
                .collect(Collectors.toList());
        APIError apiError=APIError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input Validation Failed")
                .subErrors(errors).build();
       return buildApiResponseEntity(apiError);
    }
    private ResponseEntity<APIResponse<?>> buildApiResponseEntity(APIError apiError){
        return new ResponseEntity<>(new APIResponse<>(apiError),apiError.getStatus());
    }
}