package tomatosolutions.najdiprevoz.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity(name = "tel_numbers")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TelNumber {
    @Id
    @NotBlank
    @Pattern(regexp = "^07([0-9]{7})$")
    String number;

    @JsonIgnore
    @ManyToOne
    User owner;

    @Column(name = "deleted")
    private boolean deleted = false;
}
