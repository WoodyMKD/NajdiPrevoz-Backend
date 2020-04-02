package tomatosolutions.najdiprevoz.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.exceptions.BadRequestException;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.payloads.requests.trips.AppTripRequestDTO;
import tomatosolutions.najdiprevoz.repositories.AppTripRepository;
import tomatosolutions.najdiprevoz.repositories.CarRepository;
import tomatosolutions.najdiprevoz.repositories.TelNumberRepository;
import tomatosolutions.najdiprevoz.repositories.UserRepository;
import tomatosolutions.najdiprevoz.security.UserPrincipal;
import tomatosolutions.najdiprevoz.services.AppTripService;

import java.time.LocalDateTime;

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
    public AppTrip createAppTrip(AppTripRequestDTO newTrip, UserPrincipal user) {
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
    public Page<AppTrip> getAppTrips(int page, int size) {
        return this.appTripRepository.findAll(PageRequest.of(page, size, Sort.by("startTime").descending()));
    }

    @Override
    public Page<AppTrip> getAppTrips(String cityFrom, String cityTo, int page, int size) {
        return this.appTripRepository.findAllByCityFromAndCityTo(cityFrom, cityTo, PageRequest.of(page, size));
    }
}
