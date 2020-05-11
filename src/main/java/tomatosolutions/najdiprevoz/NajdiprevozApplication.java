package tomatosolutions.najdiprevoz;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tomatosolutions.najdiprevoz.utils.converters.propertymaps.AppTripPropertyMap;

@SpringBootApplication
@EnableScheduling
public class NajdiprevozApplication {

    public static void main(String[] args) {
        SpringApplication.run(NajdiprevozApplication.class, args);
    }

    // TODO: Login/Register Form messages

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new AppTripPropertyMap());
        return modelMapper;
    }
}
