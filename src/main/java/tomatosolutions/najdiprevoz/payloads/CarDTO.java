package tomatosolutions.najdiprevoz.payloads;

import lombok.Data;

@Data
public class CarDTO {
    Long id;
    String manufacturer;
    String model;
    String color;
    String photoUrl;
}
