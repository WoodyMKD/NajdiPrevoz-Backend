package tomatosolutions.najdiprevoz.payloads.API;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class APIException {
    private HttpStatus status;
    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<APISubError> subErrors;

    private APIException() {
        timestamp = LocalDateTime.now();
    }

    public APIException(HttpStatus status) {
        this();
        this.status = status;
        this.statusCode = status.value();
    }

    public APIException(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public APIException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
