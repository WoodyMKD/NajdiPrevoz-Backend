package tomatosolutions.najdiprevoz.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.services.AppTripService;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/appTrips", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class AppTripApi {
    private final AppTripService appTripService;

    public AppTripApi(AppTripService appTripService) {
        this.appTripService = appTripService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppTrip createIngredient(@RequestParam("driver") User driver,
                                    @RequestParam("car") Car car,
                                    @RequestParam("telNumber") TelNumber telNumber,
                                    @RequestParam("startTime") LocalDateTime startTime,
                                    @RequestParam("availableSeats") int availableSeats,
                                    @RequestParam("cityFrom") String cityFrom,
                                    @RequestParam("cityTo") String cityTo) {
        AppTrip result = appTripService.createAppTrip(driver, car, telNumber, startTime, availableSeats, cityFrom, cityTo);
        return result;
    }

    @GetMapping
    public Page<AppTrip> getAppTrips(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestHeader(name = "page-size", defaultValue = "5", required = false) int size) {
        return this.appTripService.getAppTrips(page, size);
    }
}
