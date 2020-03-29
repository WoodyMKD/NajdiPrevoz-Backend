package tomatosolutions.najdiprevoz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomatosolutions.najdiprevoz.models.trips.FbTrip;

@Repository
public interface FbTripRepository extends JpaRepository<FbTrip, String> {

}
