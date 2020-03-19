package tomatosolutions.najdiprevoz.services;

import org.springframework.data.domain.Page;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.User;

import java.time.LocalDateTime;

public interface AppTripService {
    AppTrip createAppTrip(
            User driver, Car car, TelNumber telNumber, LocalDateTime startTime, int availableSeats, String cityFrom, String cityTo);

    Page<AppTrip> getAppTrips(int page, int size);
}
