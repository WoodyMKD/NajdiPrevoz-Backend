package tomatosolutions.najdiprevoz.services;

import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;

import java.util.List;

public interface UserService {
    List<Car> getUserCars(Long userId);
    Car addUserCar(Long userId, Car car);
    Car updateUserCar(Long userId, Long carId, Car updatedCar);

    TelNumber addUserTelNumber(Long userId, TelNumber newNumber);
    List<TelNumber> getUserTelNumbers(Long userId);
}
