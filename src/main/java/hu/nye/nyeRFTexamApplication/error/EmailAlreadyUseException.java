package hu.nye.nyeRFTexamApplication.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to represent when user already exists.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email address already in use.")
public class EmailAlreadyUseException extends RuntimeException{
    public EmailAlreadyUseException(String message) {
        super(message);
    }
}
