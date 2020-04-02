package tomatosolutions.najdiprevoz.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.payloads.responses.trips.AppTripResponseDTO;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.payloads.requests.trips.AppTripRequestDTO;
import tomatosolutions.najdiprevoz.security.CurrentUser;
import tomatosolutions.najdiprevoz.security.UserPrincipal;
import tomatosolutions.najdiprevoz.services.AppTripService;

import javax.validation.Valid;

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
                                              @RequestBody AppTripRequestDTO tripRequest) {
        AppTrip newTrip = appTripService.createAppTrip(tripRequest, currentUser);
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }

    @GetMapping
    public Page<AppTrip> getAppTrips(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size) {
        return this.appTripService.getAppTrips(page, size);
    }

    @GetMapping("/byCity")
    public Page<AppTrip> getAppTripsByCity(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size,
                                     @RequestParam(name = "cityFrom") String cityFrom,
                                     @RequestParam(name = "cityTo") String cityTo) {
        return this.appTripService.getAppTrips(cityFrom, cityTo, page, size);
    }
}
