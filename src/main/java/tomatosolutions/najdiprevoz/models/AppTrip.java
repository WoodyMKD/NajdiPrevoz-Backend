package tomatosolutions.najdiprevoz.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "app_trips")
public class AppTrip {
    @Id
    @GeneratedValue
    long id;

    String driver; // Driver class
    String car; // Car class
    LocalDateTime startTime;
    int availableSeats;
    String cityFrom; // City class (enum)
    String cityTo; // City class (enum)
    int status;
}