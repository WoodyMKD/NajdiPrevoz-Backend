package tomatosolutions.najdiprevoz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;

@Repository
public interface AppTripRepository extends JpaRepository<AppTrip, String> {

}
