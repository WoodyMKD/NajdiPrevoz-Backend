package tomatosolutions.najdiprevoz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.repositories.AppTripRepository;
import tomatosolutions.najdiprevoz.services.AppTripService;

import java.time.LocalDateTime;

@Service
public class AppTripServiceImpl implements AppTripService {
    private final AppTripRepository appTripRepository;

    public AppTripServiceImpl(
            AppTripRepository appTripRepository) {
        this.appTripRepository = appTripRepository;
    }

    long id;

    @Override
    public AppTrip createAppTrip(
            User driver, Car car, TelNumber telNumber, LocalDateTime startTime, int availableSeats, String cityFrom, String cityTo) {
        AppTrip trip = new AppTrip(driver, car, telNumber, startTime, availableSeats, cityFrom, cityTo);
        return this.appTripRepository.save(trip);
    }

    @Override
    public Page<AppTrip> getAppTrips(int page, int size) {
        return this.appTripRepository.findAll(PageRequest.of(page, size, Sort.by("startTime")));
    }
}
