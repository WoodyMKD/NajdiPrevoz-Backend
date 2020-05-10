package tomatosolutions.najdiprevoz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;
import tomatosolutions.najdiprevoz.models.trips.TripStatus;

import java.util.List;

@Repository
public interface AppTripRepository extends JpaRepository<AppTrip, Long> {
    Page<AppTrip> findAllByCityFromAndCityToAndStatus(String cityFrom, String cityTo, TripStatus status, Pageable pageable);
    Page<AppTrip> findAllByCityFromAndCityTo(String cityFrom, String cityTo, Pageable pageable);
    Page<AppTrip> findAllByDriverAndStatus(User driver, TripStatus status, Pageable pageable);
    Page<AppTrip> findAllByDriver(User driver, Pageable pageable);
    Page<AppTrip> findAllByStatus(TripStatus status, Pageable pageable);
}
