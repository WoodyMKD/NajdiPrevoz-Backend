package tomatosolutions.najdiprevoz.models.trips;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.auth.User;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity(name = "app_trips")
public class AppTrip {
    @Id
    @GeneratedValue
    @Column(updatable= false)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    User driver;

    @OneToOne(fetch = FetchType.LAZY)
    Car car;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    TelNumber telNumber;

    Long startTime;
    int availableSeats;
    int price;
    String cityFrom;
    String cityTo;

    TripStatus status;

    @Column(nullable = false)
    private Long creationDate;

    @PrePersist
    protected void prePersist() {
        if (this.creationDate == null) creationDate = new Date().getTime();
        if (this.status == null) status = TripStatus.ACTIVE;
    }

    public AppTrip() { }
}