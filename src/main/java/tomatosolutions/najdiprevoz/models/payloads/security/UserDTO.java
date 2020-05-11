package tomatosolutions.najdiprevoz.models.payloads.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
}
