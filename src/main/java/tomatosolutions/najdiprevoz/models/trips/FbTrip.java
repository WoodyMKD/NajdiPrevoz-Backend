package tomatosolutions.najdiprevoz.models.trips;

import lombok.Data;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.auth.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "sk_kp")
public class FbTrip {
    @Id
    @GeneratedValue
    long id;

    String driverName;
    String postDate;
    String driverFacebookUrl;
    String postContent;

    public FbTrip() { }

    public FbTrip(
            String driverName, String postDate, String driverFacebookUrl, String postContent) {
        this.driverName = driverName;
        this.postDate = postDate;
        this.driverFacebookUrl = driverFacebookUrl;
        this.postContent = postContent;
    }
}
