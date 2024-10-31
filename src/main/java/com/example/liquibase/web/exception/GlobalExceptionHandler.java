package com.example.liquibase.web.exception;

import com.example.liquibase.web.exception.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  /*  @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<String>> handleException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            if (!errors.containsKey(error.getField())) {
                List<String> errorList = new ArrayList<>();
                errorList.add(error.getDefaultMessage());
                errors.put(error.getField(), errorList);
            } else {
                errors.get(error.getField()).add(error.getDefaultMessage());
            }
        });
        return errors;
    }*/


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleInvalidUserException(UserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
