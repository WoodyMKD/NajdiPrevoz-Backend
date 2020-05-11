package tomatosolutions.najdiprevoz.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;
import tomatosolutions.najdiprevoz.models.auth.Role;
import tomatosolutions.najdiprevoz.models.auth.RoleName;
import tomatosolutions.najdiprevoz.repositories.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataFiller {
    public static final List<Role> roles = new ArrayList<>();
    public final RoleRepository roleRepository;

    public DataFiller(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (this.roleRepository.count() == 0) {
            roles.add(new Role(RoleName.USER));
            roles.add(new Role(RoleName.DRIVER));
            this.roleRepository.saveAll(roles);
            System.out.println("User roles added!");
        }
    }
}
