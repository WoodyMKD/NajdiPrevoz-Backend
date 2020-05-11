package tomatosolutions.najdiprevoz.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.exceptions.BadRequestException;
import tomatosolutions.najdiprevoz.models.exceptions.ResourceNotFoundException;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.models.trips.TripStatus;
import tomatosolutions.najdiprevoz.payloads.TripRequestDTO;
import tomatosolutions.najdiprevoz.repositories.AppTripRepository;
import tomatosolutions.najdiprevoz.repositories.CarRepository;
import tomatosolutions.najdiprevoz.repositories.TelNumberRepository;
import tomatosolutions.najdiprevoz.repositories.UserRepository;
import tomatosolutions.najdiprevoz.utils.security.UserPrincipal;
import tomatosolutions.najdiprevoz.services.AppTripService;

@Service
public class AppTripServiceImpl implements AppTripService {
    private final AppTripRepository appTripRepository;
    private final CarRepository carRepository;
    private TelNumberRepository telNumberRepository;
    private UserRepository userRepository;
    ModelMapper modelMapper;

    public AppTripServiceImpl(
            AppTripRepository appTripRepository,
            CarRepository carRepository,
            TelNumberRepository telNumberRepository,
            UserRepository userRepository) {
        this.appTripRepository = appTripRepository;
        this.carRepository = carRepository;
        this.telNumberRepository = telNumberRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public AppTrip createAppTrip(TripRequestDTO newTrip, UserPrincipal user) {
        AppTrip resultTrip = modelMapper.map(newTrip, AppTrip.class);
        User driver = userRepository.findById(user.getId()).orElse(null);
        Car car = carRepository.findById(newTrip.getCarId()).orElse(null);
        TelNumber number = telNumberRepository.findById(newTrip.getTelNumber()).orElse(null);

        if(driver == null || car == null)
            throw new BadRequestException("Настана грешка, обидете се повторно!");

        resultTrip.setDriver(driver);
        resultTrip.setCar(car);
        resultTrip.setTelNumber(number);
        resultTrip.setId(null);
        return this.appTripRepository.save(resultTrip);
    }

    @Override
    public Page<AppTrip> getAppTrips(TripStatus status, int page, int size) {
        return this.appTripRepository.findAllByStatus(status, PageRequest.of(page, size, Sort.by("startTime").descending()));
    }

    @Override
    public Page<AppTrip> getAppTrips(String cityFrom, String cityTo, TripStatus status, int page, int size) {
        if(status != TripStatus.ALL) {
            return this.appTripRepository.findAllByCityFromAndCityToAndStatus(cityFrom, cityTo, status, PageRequest.of(page, size));
        } else {
            return this.appTripRepository.findAllByCityFromAndCityTo(cityFrom, cityTo, PageRequest.of(page, size));
        }
    }

    @Override
    public Page<AppTrip> getUserAppTripsByUser(Long userId, TripStatus status, int page, int size) {
        User driver = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if(status != TripStatus.ALL) {
            return this.appTripRepository.findAllByDriverAndStatus(driver, status, PageRequest.of(page, size, Sort.by("startTime").descending()));
        } else {
            return this.appTripRepository.findAllByDriver(driver, PageRequest.of(page, size, Sort.by("startTime").descending()));
        }
    }
}
