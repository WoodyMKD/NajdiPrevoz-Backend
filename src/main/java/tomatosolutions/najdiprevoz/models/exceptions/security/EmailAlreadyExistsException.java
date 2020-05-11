package tomatosolutions.najdiprevoz.models.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends RuntimeException {
    private String email;

    public EmailAlreadyExistsException(String email) {
        super(String.format("Електронската пошта '%s' е зафатена!", email));
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
