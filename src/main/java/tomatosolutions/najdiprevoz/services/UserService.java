package tomatosolutions.najdiprevoz.services;

import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;

import java.util.List;

public interface UserService {
    List<Car> getUserCars(Long userId);
    Car addUserCar(Long userId, Car car);
    Car updateUserCar(Long userId, Long carId, Car updatedCar);
    void deleteUserCar(Long userId, Long carId);

    TelNumber addUserTelNumber(Long userId, TelNumber newNumber);
    List<TelNumber> getUserTelNumbers(Long userId);
    void deleteUserTelNumber(Long userId, String number);

    Boolean canCreateTrip(Long userId);
}
