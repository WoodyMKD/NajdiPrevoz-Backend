package tomatosolutions.najdiprevoz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomatosolutions.najdiprevoz.models.auth.Role;
import tomatosolutions.najdiprevoz.models.auth.RoleName;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
