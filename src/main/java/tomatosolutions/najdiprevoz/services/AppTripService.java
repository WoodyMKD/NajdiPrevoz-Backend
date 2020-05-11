package tomatosolutions.najdiprevoz.services;

import org.springframework.data.domain.Page;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.trips.TripStatus;
import tomatosolutions.najdiprevoz.payloads.TripRequestDTO;
import tomatosolutions.najdiprevoz.utils.security.UserPrincipal;


public interface AppTripService {
    AppTrip createAppTrip(TripRequestDTO tripData, UserPrincipal user);

    Page<AppTrip> getAppTrips(TripStatus status, int page, int size);
    Page<AppTrip> getAppTrips(String cityFrom, String cityTo, TripStatus status, int page, int size);
    Page<AppTrip> getUserAppTripsByUser(Long userId, TripStatus status, int page, int size);
}
