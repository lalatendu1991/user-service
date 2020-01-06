package com.Lalatendu.User.userservice.exception;


import com.Lalatendu.User.userservice.bean.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserExcption.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public final ResponseEntity<Object> handleAllExceptions(UserExcption ex) {

        ErrorsResponse errorResponse = ErrorsResponse.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
