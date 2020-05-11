package tomatosolutions.najdiprevoz.services.impl;

import org.modelmapper.ModelMapper;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.repositories.AppTripRepository;
import tomatosolutions.najdiprevoz.repositories.CarRepository;
import tomatosolutions.najdiprevoz.repositories.UserRepository;
import tomatosolutions.najdiprevoz.services.CarService;

public class CarServiceImpl implements CarService {
    ModelMapper modelMapper;
    private final CarRepository carRepository;
    private UserRepository userRepository;

    public CarServiceImpl(
            ModelMapper modelMapper,
            CarRepository carRepository,
            UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
}
