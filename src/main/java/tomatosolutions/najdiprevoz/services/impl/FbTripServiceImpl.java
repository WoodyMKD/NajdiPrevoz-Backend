package tomatosolutions.najdiprevoz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.trips.FbTrip;
import tomatosolutions.najdiprevoz.repositories.AppTripRepository;
import tomatosolutions.najdiprevoz.repositories.FbTripRepository;
import tomatosolutions.najdiprevoz.services.AppTripService;
import tomatosolutions.najdiprevoz.services.FbTripService;

import java.time.LocalDateTime;

@Service
public class FbTripServiceImpl implements FbTripService {
    private final FbTripRepository fbTripRepository;

    public FbTripServiceImpl(
            FbTripRepository fbTripRepository) {
        this.fbTripRepository = fbTripRepository;
    }

    @Override
    public FbTrip createFbTrip(
            String driverName, Long postDate, String driverFacebookUrl, String postContent) {
        FbTrip trip = new FbTrip(driverName, postDate, driverFacebookUrl, postContent);
        return this.fbTripRepository.save(trip);
    }

    @Override
    public Page<FbTrip> getFbTrips(int page, int size) {
        return this.fbTripRepository.findAll(PageRequest.of(page, size, Sort.by("postDate").descending()));
    }
}
