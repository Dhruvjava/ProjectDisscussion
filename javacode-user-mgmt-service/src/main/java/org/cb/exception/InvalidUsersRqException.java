package org.cb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.rs.ErrorRs;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidUsersRqException extends RuntimeException{

    private String code;

    private String message;

    private List<ErrorRs> errors;

    public InvalidUsersRqException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
