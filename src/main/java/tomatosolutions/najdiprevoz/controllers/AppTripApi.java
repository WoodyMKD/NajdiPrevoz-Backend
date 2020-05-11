package tomatosolutions.najdiprevoz.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.models.trips.TripStatus;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.payloads.TripRequestDTO;
import tomatosolutions.najdiprevoz.annotations.CurrentUser;
import tomatosolutions.najdiprevoz.utils.security.UserPrincipal;
import tomatosolutions.najdiprevoz.services.AppTripService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/appTrips", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class AppTripApi {
    private final AppTripService appTripService;

    public AppTripApi(AppTripService appTripService) {
        this.appTripService = appTripService;
    }

    @PostMapping
    public ResponseEntity<AppTrip> createTrip(@CurrentUser UserPrincipal currentUser,
                                              @RequestBody TripRequestDTO tripRequest) {
        AppTrip newTrip = appTripService.createAppTrip(tripRequest, currentUser);
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }

    @GetMapping
    public Page<AppTrip> getAppTrips(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size,
                                     @RequestParam(name = "status", defaultValue = "all") TripStatus tripStatus) {
        return this.appTripService.getAppTrips(tripStatus, page, size);
    }

    @GetMapping("/byUser")
    public Page<AppTrip> getAppTripsByUser(@CurrentUser UserPrincipal currentUser,
                                             @RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                             @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size,
                                             @RequestParam(name = "status", defaultValue = "all") TripStatus tripStatus) {
        return this.appTripService.getUserAppTripsByUser(currentUser.getId(), tripStatus, page, size);
    }

    @GetMapping("/byCity")
    public Page<AppTrip> getAppTripsByCity(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                           @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size,
                                           @RequestParam(name = "cityFrom") String cityFrom,
                                           @RequestParam(name = "cityTo") String cityTo,
                                           @RequestParam(name = "status", defaultValue = "all") TripStatus tripStatus) {
        return this.appTripService.getAppTrips(cityFrom, cityTo, tripStatus, page, size);
    }
}
