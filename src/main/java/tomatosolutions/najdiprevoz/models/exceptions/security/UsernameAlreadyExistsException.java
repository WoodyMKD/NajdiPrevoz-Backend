package tomatosolutions.najdiprevoz.models.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameAlreadyExistsException extends RuntimeException {
    private String username;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("Корисничкото име '%s' е зафатено!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
