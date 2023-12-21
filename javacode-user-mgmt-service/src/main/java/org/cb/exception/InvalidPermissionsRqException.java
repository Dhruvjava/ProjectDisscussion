package org.cb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.rs.ErrorRs;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class InvalidPermissionsRqException extends RuntimeException {

    private String code;

    private String message;

    private List<ErrorRs> errors;

    public InvalidPermissionsRqException(String code, String message, List<ErrorRs> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public InvalidPermissionsRqException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
