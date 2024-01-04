package org.cb.handler;

import org.cb.exception.InvalidRoleRqException;
import org.cb.exception.InvalidUsersRqException;
import org.cb.exception.RolesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class UsersHandler {

    @ExceptionHandler(InvalidUsersRqException.class)
    public ResponseEntity<ProblemDetail> handleInvalidRolesException(InvalidUsersRqException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getCode());
        problemDetail.setDetail(e.getMessage());
        Optional.ofNullable(e.getErrors()).ifPresent(errors -> {
            problemDetail.setProperty("errors", e.getErrors());
        });
        return ResponseEntity.badRequest().body(problemDetail);
    }

//    @ExceptionHandler(RolesNotFoundException.class)
//    public ResponseEntity<ProblemDetail> handleRoleNotFoundException(RolesNotFoundException e) {
//        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
//        problemDetail.setTitle(e.getCode());
//        problemDetail.setDetail(e.getMessage());
//        return ResponseEntity.badRequest().body(problemDetail);
//    }

}
