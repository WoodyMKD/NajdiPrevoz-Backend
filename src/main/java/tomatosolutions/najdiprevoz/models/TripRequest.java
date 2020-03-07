package tomatosolutions.najdiprevoz.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "trip_requests")
public class TripRequest {
    @Id
    @GeneratedValue
    long id;

    String passenger; // Passenger (user) class
    LocalDateTime startTime;
    int numPassengers;
    String cityFrom; // City class (enum)
    String cityTo; // City class (enum)
    int status;
}
