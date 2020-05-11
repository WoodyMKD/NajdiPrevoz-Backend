package tomatosolutions.najdiprevoz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Where;
import tomatosolutions.najdiprevoz.models.auth.User;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {
    @Id
    @GeneratedValue
    Long id;

    String manufacturer;
    String model;
    String color;
    String photoUrl;

    @JsonIgnore
    @ManyToOne
    User owner;

    @Column(name = "deleted")
    private boolean deleted = false;
}
