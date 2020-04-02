package tomatosolutions.najdiprevoz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomatosolutions.najdiprevoz.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}