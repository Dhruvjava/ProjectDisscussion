package org.cb.handler;

import org.cb.exception.InvalidPermissionsRqException;
import org.cb.exception.InvalidRoleRqException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class RolesHandler {

    @ExceptionHandler(InvalidRoleRqException.class)
    public ResponseEntity<ProblemDetail> handleInvalidPermissionsException(InvalidRoleRqException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getCode());
        problemDetail.setDetail(e.getMessage());
        Optional.ofNullable(e.getErrors()).ifPresent(errors -> {
            problemDetail.setProperty("errors", e.getErrors());
        });
        return ResponseEntity.badRequest().body(problemDetail);
    }

}
