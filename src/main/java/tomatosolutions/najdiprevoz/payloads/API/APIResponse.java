package tomatosolutions.najdiprevoz.payloads.API;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class APIResponse {
    private HttpStatus status;
    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private Object response;

    public APIResponse(Object response, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.response = response;
        this.statusCode = status.value();
        this.status = status;
    }
}
