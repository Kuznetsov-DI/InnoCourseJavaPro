package spring.web.lesson.controller.handlers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.web.lesson.dto.error.PaymentErrorResponse;
import spring.web.lesson.exception.NotEnoughBalanceException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<PaymentErrorResponse> handleNoEnoughBalanceException(NotEnoughBalanceException ex) {
        PaymentErrorResponse error = new PaymentErrorResponse(
                LocalDateTime.now(),
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<PaymentErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        PaymentErrorResponse error = new PaymentErrorResponse(
                LocalDateTime.now(),
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
