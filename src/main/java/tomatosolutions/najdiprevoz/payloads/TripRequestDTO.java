package tomatosolutions.najdiprevoz.payloads;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TripRequestDTO {
    Long carId;
    String telNumber;
    Long startTime;
    int availableSeats;
    int price;
    String cityFrom;
    String cityTo;
}
