package tomatosolutions.najdiprevoz.controllers.auth;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.exceptions.BadRequestException;
import tomatosolutions.najdiprevoz.models.payloads.API.APIResponse;
import tomatosolutions.najdiprevoz.models.payloads.AppTripDTO;
import tomatosolutions.najdiprevoz.models.payloads.CarDTO;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.utils.annotations.CurrentUser;
import tomatosolutions.najdiprevoz.services.UserService;
import tomatosolutions.najdiprevoz.models.payloads.security.UserPrincipal;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    private final UserService userService;
    ModelMapper modelMapper;

    public UserApi(UserService userService,
                   ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        if(currentUser == null) throw new BadRequestException("Неправилно барање!");
        return ResponseEntity.ok(new APIResponse(currentUser, HttpStatus.OK));
    }

    @PostMapping("/cars")
    public ResponseEntity<APIResponse> addUserCar(@CurrentUser UserPrincipal currentUser,
                                          @RequestBody CarDTO newCar) {
        Car car = modelMapper.map(newCar, Car.class);
        car = userService.addUserCar(currentUser.getId(), car);
        CarDTO result = modelMapper.map(car, CarDTO.class);
        return new ResponseEntity(new APIResponse(result, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public ResponseEntity<APIResponse> getUserCars(@CurrentUser UserPrincipal currentUser) {
        List<Car> cars = userService.getUserCars(currentUser.getId());
        List<CarDTO> result = cars.stream()
                .filter((c) -> !c.isDeleted())
                .map((c) -> modelMapper.map(c, CarDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new APIResponse(result, HttpStatus.OK));
    }

    @PatchMapping("/cars/{carId}")
    public ResponseEntity<APIResponse> updateUserCar(@CurrentUser UserPrincipal currentUser,
                                                     @PathVariable Long carId,
                                                     @RequestBody CarDTO updatedCar) {
        Car car = modelMapper.map(updatedCar, Car.class);
        car = userService.updateUserCar(currentUser.getId(), carId, car);
        CarDTO result = modelMapper.map(car, CarDTO.class);
        return ResponseEntity.ok(new APIResponse(result, HttpStatus.OK));
    }

    @DeleteMapping("/cars")
    public ResponseEntity<APIResponse> deleteUserCar(@CurrentUser UserPrincipal currentUser,
                                                     @RequestBody CarDTO toDelete) {
        userService.deleteUserCar(currentUser.getId(), toDelete.getId());
        return ResponseEntity.ok(new APIResponse(true, HttpStatus.OK));
    }

    @PostMapping("/telNumbers")
    public ResponseEntity<APIResponse> addUserTelNumber(@CurrentUser UserPrincipal currentUser,
                                                      @RequestBody TelNumber telNumber) {
        TelNumber newNumber = userService.addUserTelNumber(currentUser.getId(), telNumber);
        return new ResponseEntity(new APIResponse(newNumber, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping("/telNumbers")
    public ResponseEntity<APIResponse> getUserTelNumbers(@CurrentUser UserPrincipal currentUser) {
        List<TelNumber> telNumbers = userService.getUserTelNumbers(currentUser.getId());
        return ResponseEntity.ok(new APIResponse(telNumbers, HttpStatus.OK));
    }

    @DeleteMapping("/telNumbers")
    public ResponseEntity<APIResponse> deleteUserTelNumbers(@CurrentUser UserPrincipal currentUser,
                                                            @RequestBody TelNumber telNumber) {
        userService.deleteUserTelNumber(currentUser.getId(), telNumber.getNumber());
        return ResponseEntity.ok(new APIResponse(true, HttpStatus.OK));
    }

    @GetMapping("/canCreateTrip")
    public ResponseEntity<APIResponse> canCreateTrip(@CurrentUser UserPrincipal currentUser) {
        Boolean result = userService.canCreateTrip(currentUser.getId());
        return ResponseEntity.ok(new APIResponse(result, HttpStatus.OK));
    }
}
