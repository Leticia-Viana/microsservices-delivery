package br.com.ada.orders.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Log4j2
public class OrdersException {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(final EntityNotFoundException exception) {

        log.error(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .message(exception.getMessage())
                        .build());
    }
}
