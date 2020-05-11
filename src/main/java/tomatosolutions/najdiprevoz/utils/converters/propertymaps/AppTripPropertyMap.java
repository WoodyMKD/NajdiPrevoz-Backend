package tomatosolutions.najdiprevoz.utils.converters.propertymaps;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.models.payloads.AppTripDTO;
import tomatosolutions.najdiprevoz.models.payloads.security.UserDTO;
import tomatosolutions.najdiprevoz.models.payloads.security.UserPrincipal;
import tomatosolutions.najdiprevoz.models.trips.AppTrip;

public class AppTripPropertyMap extends PropertyMap<AppTrip, AppTripDTO> {
    Converter<User, UserDTO> userToUserPrincipal = new Converter<User, UserDTO>() {
        public UserDTO convert(MappingContext<User, UserDTO> context) {
            User source = context.getSource();
            UserDTO destination = new UserDTO();

            if(source.getId() != null) {
                destination.setId(source.getId());
                destination.setName(source.getName());
            }

            return destination;
        }
    };

    @Override
    protected void configure() {
        using(userToUserPrincipal).map(source.getDriver()).setDriver(null);
    }
}