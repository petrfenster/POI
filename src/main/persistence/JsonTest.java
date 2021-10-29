package persistence;


import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPOI(String name, String type, Rating rating, GeoLocation geoLocation,
                            HoursOfOperation hoursOfOperation, double price, POI poi) {
        assertEquals(name, poi.getName());
        assertEquals(type, poi.getType());
        assertEquals(rating.getAverageRating(), poi.getRating().getAverageRating());
        assertEquals(geoLocation.getCityName(), poi.getGeoLocation().getCityName());
        assertEquals(geoLocation.getStreetName(), poi.getGeoLocation().getStreetName());
        assertEquals(geoLocation.getStreetNumber(), poi.getGeoLocation().getStreetNumber());
        assertEquals(geoLocation.getProvince(), poi.getGeoLocation().getProvince());
        assertEquals(geoLocation.getZipCode(), poi.getGeoLocation().getZipCode());
        assertEquals(hoursOfOperation.getStartHour(), poi.getHoursOfOperation().getStartHour());
        assertEquals(hoursOfOperation.getStartMinute(), poi.getHoursOfOperation().getStartMinute());
        assertEquals(hoursOfOperation.getEndHour(), poi.getHoursOfOperation().getEndHour());
        assertEquals(hoursOfOperation.getEndMinute(), poi.getHoursOfOperation().getEndMinute());
        assertEquals(price, poi.getPrice());
        assertEquals(rating.getNumRatings(), poi.getRating().getNumRatings());
        for (int i = 0; i < rating.getReviews().size(); i++) {
            assertEquals(rating.getReviews().get(i).getReviewer(),
                    poi.getRating().getReviews().get(i).getReviewer());
            assertEquals(rating.getReviews().get(i).getReview(),
                    poi.getRating().getReviews().get(i).getReview());
        }
    }
}
