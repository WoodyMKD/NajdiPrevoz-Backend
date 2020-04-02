package tomatosolutions.najdiprevoz.services.impl;

import org.modelmapper.ModelMapper;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.repositories.AppTripRepository;
import tomatosolutions.najdiprevoz.repositories.CarRepository;
import tomatosolutions.najdiprevoz.repositories.UserRepository;
import tomatosolutions.najdiprevoz.services.CarService;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private UserRepository userRepository;
    ModelMapper modelMapper;

    public CarServiceImpl(
            CarRepository carRepository,
            UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }
}
