package tomatosolutions.najdiprevoz.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "cars")
public class Car {
    @Id
    @GeneratedValue
    long id;

    String manufacturer;
    String model;
    String color;
    String photoUrl;
}
