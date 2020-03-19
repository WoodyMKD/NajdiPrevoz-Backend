package tomatosolutions.najdiprevoz.models.trips;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "fb_trips")
public class FbTrip {
    @Id
    @GeneratedValue
    long id;

    String driverName;
    String driverFacebookUrl;
    String postContent;
}
