package com.webrise.testproject.controller;

import com.webrise.testproject.model.dto.ExceptionDTO;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice("com.webrise.testproject.controller")
@Slf4j
@AllArgsConstructor
@Hidden
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO handleException(HttpServletRequest req, Exception e) {
        log.error("Error in exceptionHandler. Path {}", req.getRequestURI(), e);
        return new ExceptionDTO(
                "Internal_error_title",
                "",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleEntityNotFoundException(HttpServletRequest req, Exception e) {
        log.error("Error in exceptionHandler. Path {}", req.getRequestURI(), e);
        return new ExceptionDTO(
                "bad_request",
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());
    }
}
