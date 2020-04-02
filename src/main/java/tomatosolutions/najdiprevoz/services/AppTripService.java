package tomatosolutions.najdiprevoz.services;

import org.springframework.data.domain.Page;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.payloads.requests.trips.AppTripRequestDTO;
import tomatosolutions.najdiprevoz.security.UserPrincipal;


public interface AppTripService {
    AppTrip createAppTrip(AppTripRequestDTO tripData, UserPrincipal user);

    Page<AppTrip> getAppTrips(int page, int size);
    Page<AppTrip> getAppTrips(String cityFrom, String cityTo, int page, int size);
}
