package tomatosolutions.najdiprevoz.controllers.auth;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.exceptions.BadRequestException;
import tomatosolutions.najdiprevoz.payloads.requests.auth.CarDTO;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.payloads.requests.trips.AppTripRequestDTO;
import tomatosolutions.najdiprevoz.payloads.responses.auth.UserIdentityAvailability;
import tomatosolutions.najdiprevoz.repositories.CarRepository;
import tomatosolutions.najdiprevoz.repositories.TelNumberRepository;
import tomatosolutions.najdiprevoz.repositories.UserRepository;
import tomatosolutions.najdiprevoz.security.CurrentUser;
import tomatosolutions.najdiprevoz.security.UserPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApi {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final TelNumberRepository telNumberRepository;
    ModelMapper modelMapper;

    public UserApi(UserRepository userRepository,
                   CarRepository carRepository,
                   TelNumberRepository telNumberRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.telNumberRepository = telNumberRepository;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/user/me")
    public UserPrincipal getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return currentUser;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value="username") String username){
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @PostMapping("/user/cars")
    public ResponseEntity<Car> addCar(@CurrentUser UserPrincipal currentUser,
                                      @RequestBody CarDTO newCar) {

        // SERVICE LAYER
        Car resultCar = modelMapper.map(newCar, Car.class);
        User owner = userRepository.findById(currentUser.getId()).get();

        resultCar.setId(null);
        resultCar.setOwner(owner);

        resultCar = this.carRepository.save(resultCar);
        // ==============

        return new ResponseEntity<>(resultCar, HttpStatus.CREATED);
    }

    @GetMapping("/user/cars")
    public ResponseEntity<List<Car>> getUserCars(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findById(currentUser.getId()).get();
        return new ResponseEntity<>(user.getCars(), HttpStatus.OK);
    }

    @PatchMapping("/user/cars/{carId}")
    public ResponseEntity<Car> updateCar(@CurrentUser UserPrincipal currentUser,
                                            @PathVariable Long carId,
                                            @RequestBody CarDTO updatedCar) {
        // Service Layer ...
        User user = userRepository.findById(currentUser.getId()).get();
        Car car = user.getCars().stream()
                .filter((c) -> c.getId() == carId)
                .findFirst()
                .orElse(null);

        car.setManufacturer(updatedCar.getManufacturer());
        car.setModel(updatedCar.getModel());
        car.setColor(updatedCar.getColor());
        // ====================

        return new ResponseEntity<>(this.carRepository.save(car), HttpStatus.OK);
    }

    @PostMapping("/user/telNumbers")
    public ResponseEntity<TelNumber> addTelNumber(@CurrentUser UserPrincipal currentUser,
                                                  @RequestBody TelNumber telNumber) {

        // SERVICE LAYER
        User owner = userRepository.findById(currentUser.getId()).get();
        telNumber.setOwner(owner);

        this.telNumberRepository.save(telNumber);
        // ==============

        return new ResponseEntity<>(telNumber, HttpStatus.CREATED);
    }

    @GetMapping("/user/telNumbers")
    public List<TelNumber> getUserTelNumbers(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findById(currentUser.getId()).get();
        return user.getTelNumbers();
    }
}
