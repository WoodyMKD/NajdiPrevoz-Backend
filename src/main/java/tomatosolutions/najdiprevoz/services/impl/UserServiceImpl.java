package tomatosolutions.najdiprevoz.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.models.exceptions.ResourceNotFoundException;
import tomatosolutions.najdiprevoz.repositories.CarRepository;
import tomatosolutions.najdiprevoz.repositories.TelNumberRepository;
import tomatosolutions.najdiprevoz.repositories.UserRepository;
import tomatosolutions.najdiprevoz.models.payloads.security.UserPrincipal;
import tomatosolutions.najdiprevoz.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final TelNumberRepository telNumberRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            CarRepository carRepository,
            TelNumberRepository telNumberRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.telNumberRepository = telNumberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Корисникот '" + usernameOrEmail + "' не е пронајден.")
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }

    @Override
    public List<Car> getUserCars(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return user.getCars();
    }

    @Override
    public Car addUserCar(Long userId, Car newCar) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        newCar.setId(null);
        newCar.setOwner(user);

        return this.carRepository.save(newCar);
    }

    @Override
    public Car updateUserCar(Long userId, Long carId, Car updatedCar) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Car car = user.getCars().stream()
                .filter((c) -> c.getId().equals(carId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User", "Car Id", carId));

        car.setManufacturer(updatedCar.getManufacturer());
        car.setModel(updatedCar.getModel());
        car.setColor(updatedCar.getColor());

        return this.carRepository.save(car);
    }

    @Override
    public void deleteUserCar(Long userId, Long carId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Car toDelete = user.getCars().stream()
                .filter((c) -> c.getId().equals(carId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        toDelete.setDeleted(true);
        this.carRepository.save(toDelete);
    }

    @Override
    public TelNumber addUserTelNumber(Long userId, TelNumber newNumber) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        newNumber.setOwner(user);
        return this.telNumberRepository.save(newNumber);
    }

    @Override
    public List<TelNumber> getUserTelNumbers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return user.getTelNumbers().stream()
                .filter((t) -> !t.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserTelNumber(Long userId, String number) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        TelNumber toDelete = user.getTelNumbers().stream()
                .filter((t) -> t.getNumber().equals(number))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("TelNumber", "number", number));
        toDelete.setDeleted(true);
        this.telNumberRepository.save(toDelete);
    }

    @Override
    public Boolean canCreateTrip(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return user.getCars().stream().filter((c) -> !c.isDeleted()).count() > 0 &&
                user.getTelNumbers().stream().filter((t) -> !t.isDeleted()).count() > 0;
    }
}