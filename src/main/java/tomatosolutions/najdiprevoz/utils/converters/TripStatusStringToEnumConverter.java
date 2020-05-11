package tomatosolutions.najdiprevoz.utils.converters;

import org.springframework.core.convert.converter.Converter;
import tomatosolutions.najdiprevoz.models.trips.TripStatus;

public class TripStatusStringToEnumConverter implements Converter<String, TripStatus> {
    @Override
    public TripStatus convert(String source) {
        return TripStatus.valueOf(source.toUpperCase());
    }
}