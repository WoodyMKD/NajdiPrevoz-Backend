package tomatosolutions.najdiprevoz.payloads.requests.trips;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AppTripRequestDTO {
    Long carId;
    String telNumber;
    Long startTime;
    int availableSeats;
    int price;
    String cityFrom;
    String cityTo;
}
