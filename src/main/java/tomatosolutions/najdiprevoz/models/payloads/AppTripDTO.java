package tomatosolutions.najdiprevoz.models.payloads;

import lombok.Data;
import tomatosolutions.najdiprevoz.models.payloads.security.UserDTO;
import tomatosolutions.najdiprevoz.models.payloads.security.UserPrincipal;
import tomatosolutions.najdiprevoz.models.trips.TripStatus;

@Data
public class AppTripDTO {
    Long id;
    Long driverId;
    Long carId;
    UserDTO driver;
    CarDTO car;
    String telNumber;
    Long startTime;
    int availableSeats;
    int price;
    String cityFrom;
    String cityTo;
    TripStatus status;
    private Long creationDate;
}
