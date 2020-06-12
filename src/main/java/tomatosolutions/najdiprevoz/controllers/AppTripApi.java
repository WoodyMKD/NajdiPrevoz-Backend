package tomatosolutions.najdiprevoz.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.models.trips.TripStatus;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.payloads.API.APIResponse;
import tomatosolutions.najdiprevoz.models.payloads.AppTripDTO;
import tomatosolutions.najdiprevoz.utils.annotations.CurrentUser;
import tomatosolutions.najdiprevoz.models.payloads.security.UserPrincipal;
import tomatosolutions.najdiprevoz.services.AppTripService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://134.122.92.122:8080"})
@RequestMapping(path = "/api/appTrips", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class AppTripApi {
    private final AppTripService appTripService;
    ModelMapper modelMapper;

    public AppTripApi(
            AppTripService appTripService,
            ModelMapper modelMapper) {
        this.appTripService = appTripService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<APIResponse> createTrip(@CurrentUser UserPrincipal currentUser,
                                              @RequestBody AppTripDTO tripData) {
        AppTrip newTrip = convertToEntity(tripData);
        newTrip = appTripService.createAppTrip(currentUser.getId(), tripData.getCarId(), newTrip);
        AppTripDTO result = convertToDto(newTrip);
        return new ResponseEntity(new APIResponse(result, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<APIResponse> getAppTrips(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size,
                                     @RequestParam(name = "status", defaultValue = "all") TripStatus tripStatus) {
        Page<AppTrip> trips = this.appTripService.getAppTrips(tripStatus, page, size);
        Page<AppTripDTO> result = trips.map(this::convertToDto);
        return ResponseEntity.ok(new APIResponse(result, HttpStatus.OK));
    }

    @GetMapping("/byUser")
    public ResponseEntity<APIResponse> getAppTripsByUser(@CurrentUser UserPrincipal currentUser,
                                             @RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                             @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size,
                                             @RequestParam(name = "status", defaultValue = "all") TripStatus tripStatus) {
        Page<AppTrip> trips = this.appTripService.getUserAppTripsByUser(currentUser.getId(), tripStatus, page, size);
        Page<AppTripDTO> result = trips.map(this::convertToDto);
        return ResponseEntity.ok(new APIResponse(result, HttpStatus.OK));
    }

    @GetMapping("/byCity")
    public ResponseEntity<APIResponse> getAppTripsByCity(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                                          @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size,
                                                          @RequestParam(name = "cityFrom") String cityFrom,
                                                          @RequestParam(name = "cityTo") String cityTo,
                                                          @RequestParam(name = "status", defaultValue = "all") TripStatus tripStatus) {
        Page<AppTrip> trips = this.appTripService.getAppTrips(cityFrom, cityTo, tripStatus, page, size);
        Page<AppTripDTO> result = trips.map(this::convertToDto);
        return ResponseEntity.ok(new APIResponse(result, HttpStatus.OK));
    }

    private AppTripDTO convertToDto(AppTrip trip) {
        AppTripDTO tripDTO = modelMapper.map(trip, AppTripDTO.class);
        return tripDTO;
    }

    private AppTrip convertToEntity(AppTripDTO tripDTO) {
        AppTrip trip = modelMapper.map(tripDTO, AppTrip.class);
        return trip;
    }
}
