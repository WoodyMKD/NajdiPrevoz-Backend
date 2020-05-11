package tomatosolutions.najdiprevoz.controllers.auth;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.exceptions.BadRequestException;
import tomatosolutions.najdiprevoz.payloads.API.APIResponse;
import tomatosolutions.najdiprevoz.payloads.CarDTO;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.utils.annotations.CurrentUser;
import tomatosolutions.najdiprevoz.services.UserService;
import tomatosolutions.najdiprevoz.utils.security.UserPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    private final UserService userService;
    ModelMapper modelMapper;

    public UserApi(UserService userService) {
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        if(currentUser == null) throw new BadRequestException("Неправилно барање!");
        return ResponseEntity.ok(new APIResponse(currentUser, HttpStatus.OK));
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addUserCar(@CurrentUser UserPrincipal currentUser,
                                          @RequestBody CarDTO newCar) {
        Car car = modelMapper.map(newCar, Car.class);
        car = userService.addUserCar(currentUser.getId(), car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public ResponseEntity<APIResponse> getUserCars(@CurrentUser UserPrincipal currentUser) {
        List<Car> cars = userService.getUserCars(currentUser.getId());
        return ResponseEntity.ok(new APIResponse(cars, HttpStatus.OK));
    }

    @PatchMapping("/cars/{carId}")
    public ResponseEntity<APIResponse> updateUserCar(@CurrentUser UserPrincipal currentUser,
                                                     @PathVariable Long carId,
                                                     @RequestBody CarDTO updatedCar) {
        Car car = modelMapper.map(updatedCar, Car.class);
        car = userService.updateUserCar(currentUser.getId(), carId, car);

        return ResponseEntity.ok(new APIResponse(car, HttpStatus.OK));
    }

    @PostMapping("/telNumbers")
    public ResponseEntity<APIResponse> addUserTelNumber(@CurrentUser UserPrincipal currentUser,
                                                      @RequestBody TelNumber telNumber) {

        TelNumber newNumber = userService.addUserTelNumber(currentUser.getId(), telNumber);
        return ResponseEntity.ok(new APIResponse(newNumber, HttpStatus.OK));
    }

    @GetMapping("/telNumbers")
    public ResponseEntity<APIResponse> getUserTelNumbers(@CurrentUser UserPrincipal currentUser) {
        List<TelNumber> telNumbers = userService.getUserTelNumbers(currentUser.getId());
        return ResponseEntity.ok(new APIResponse(telNumbers, HttpStatus.OK));
    }
}
