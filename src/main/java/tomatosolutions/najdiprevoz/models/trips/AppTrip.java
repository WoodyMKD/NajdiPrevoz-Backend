package tomatosolutions.najdiprevoz.models.trips;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.auth.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "app_trips")
public class AppTrip {
    @Id
    @GeneratedValue
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    User driver;

    @OneToOne(fetch = FetchType.LAZY)
    Car car;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    TelNumber telNumber;

    LocalDateTime startTime;
    int availableSeats;
    int price;
    String cityFrom; // City class (enum)
    String cityTo; // City class (enum)
    TripStatus status;

    public AppTrip() { }

    public AppTrip(
            User driver, Car car, TelNumber telNumber, LocalDateTime startTime, int availableSeats, String cityFrom, String cityTo) {
        this.driver = driver;
        this.car = car;
        this.telNumber = telNumber;
        this.startTime = startTime;
        this.availableSeats = availableSeats;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.status = TripStatus.ACTIVE;
    }
}