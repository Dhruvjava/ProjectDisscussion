package org.cb.advice.handler;

import org.apache.coyote.Response;
import org.cb.exception.NotificationsExceptions;
import org.cb.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class NotificationHandler {

    @ExceptionHandler(NotificationsExceptions.class)
    public ResponseEntity<ProblemDetail> handleNotificationsException(NotificationsExceptions e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getCode());
        problemDetail.setDetail(e.getMessage());
        Optional.ofNullable(e.getErrors()).filter(Utils::isNotEmpty).ifPresent(err -> {
            problemDetail.setProperty("errors", e.getErrors());
        });
        return ResponseEntity.badRequest().body(problemDetail);
    }

}
