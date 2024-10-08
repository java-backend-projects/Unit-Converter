package ru.sug4chy.unitconverter.controller.advices;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sug4chy.unitconverter.dto.ErrorDto;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleBadRequest(
            RuntimeException ex,
            WebRequest webRequest,
            HttpServletRequest httpServletRequest
    ) {
        return handleExceptionInternal(
                ex,
                new ErrorDto(ex.getMessage(), httpServletRequest.getRequestURI()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                webRequest
        );
    }
}
