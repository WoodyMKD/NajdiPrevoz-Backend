package tomatosolutions.najdiprevoz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomatosolutions.najdiprevoz.models.Car;
import tomatosolutions.najdiprevoz.models.auth.TelNumber;

@Repository
public interface TelNumberRepository extends JpaRepository<TelNumber, String> {

}