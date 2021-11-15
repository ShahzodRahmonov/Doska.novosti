package com.company.controller;

import com.company.exceptions.NewsNotFoundException;
import com.company.exceptions.UserNotFoundException;
import com.company.exceptions.UserRoleException;
import com.company.exceptions.UsersLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> handlerException(RuntimeException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({UsersLoginException.class})
    public ResponseEntity<?> handlerException(UsersLoginException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({UserRoleException.class})
    public ResponseEntity<?> handlerException(UserRoleException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({NewsNotFoundException.class})
    public ResponseEntity<?> handlerException(NewsNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
