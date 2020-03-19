package tomatosolutions.najdiprevoz.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity(name = "tel_numbers")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TelNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @JsonIgnore
    @ManyToOne
    User owner;

    @NotBlank
    @Pattern(regexp = "([0-9]{9})$")
    String number;
}
