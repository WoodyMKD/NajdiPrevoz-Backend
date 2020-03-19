package tomatosolutions.najdiprevoz.models.trips;

import lombok.Data;
import tomatosolutions.najdiprevoz.models.auth.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity(name = "trip_requests")
public class TripRequest {
    @Id
    @GeneratedValue
    long id;

    LocalDateTime startTime;
    int numPassengers;
    String cityFrom; // City class (enum)
    String cityTo; // City class (enum)
    TripStatus status;

    @ManyToOne
    User passenger;
}
