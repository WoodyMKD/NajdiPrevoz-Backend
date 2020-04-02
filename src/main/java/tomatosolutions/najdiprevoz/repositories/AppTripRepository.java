package tomatosolutions.najdiprevoz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;

import java.util.List;

@Repository
public interface AppTripRepository extends JpaRepository<AppTrip, Long> {
    Page<AppTrip> findAllByCityFromAndCityTo(String cityFrom, String cityTo, Pageable pageable);
}
