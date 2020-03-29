package tomatosolutions.najdiprevoz.services;

import org.springframework.data.domain.Page;
import tomatosolutions.najdiprevoz.models.trips.FbTrip;


public interface FbTripService {
    FbTrip createFbTrip(
            String driverName, String postDate, String driverFacebookUrl, String postContent);

    Page<FbTrip> getFbTrips(int page, int size);
}
