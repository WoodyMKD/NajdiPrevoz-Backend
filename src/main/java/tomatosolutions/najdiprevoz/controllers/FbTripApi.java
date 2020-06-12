package tomatosolutions.najdiprevoz.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomatosolutions.najdiprevoz.models.trips.FbTrip;
import tomatosolutions.najdiprevoz.models.payloads.API.APIResponse;
import tomatosolutions.najdiprevoz.services.FbTripService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://134.122.92.122:8080"})
@RequestMapping(path = "/api/fbTrips")
public class FbTripApi {
    private final FbTripService fbTripService;

    public FbTripApi(FbTripService fbTripService) {
        this.fbTripService = fbTripService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<APIResponse> createFacebookTrip(@RequestParam("driverName") String driverName,
                                     @RequestParam("postDate") Long postDate,
                                     @RequestParam("driverFacebookUrl") String driverFacebookUrl,
                                     @RequestParam("postContent") String postContent) {
        FbTrip trip = fbTripService.createFbTrip(driverName, postDate, driverFacebookUrl, postContent);
        return new ResponseEntity(new APIResponse(trip, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse> getFbTrips(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                                               @RequestHeader(name = "page-size", defaultValue = "6", required = false) int size) {
        Page<FbTrip> trips = fbTripService.getFbTrips(page, size);
        return ResponseEntity.ok(new APIResponse(trips, HttpStatus.OK));
    }
}
