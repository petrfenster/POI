import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class PoiTest {
    private POI poi;
    private Rating galleryRating;
    private HoursOfOperation galleryHours;
    private GeoLocation galleryGeo;
    private double galleryPrice;
    private Rating testRating;
    private GeoLocation testGeoLocation;
    private HoursOfOperation testHours;

    @BeforeEach
    public void setUp() {
        galleryGeo = new GeoLocation(750, "Hornby St", "Vancouver",
                "BC", "V6Z 2H7");

        Review gallery1 = new Review("Jack", "Beautiful paintings");
        Review gallery2 = new Review("Vaibhav", "Could be better");
        Review gallery3 = new Review("Petr", "Overpriced");
        Review gallery4 = new Review("Kash", "Van Gogh");
        Review gallery5 = new Review("Shaon", "Friendly staff");

        List<Review> galleryReviews = new ArrayList<>();

        galleryReviews.add(gallery1);
        galleryReviews.add(gallery2);
        galleryReviews.add(gallery3);
        galleryReviews.add(gallery4);
        galleryReviews.add(gallery5);

        galleryRating = new Rating(galleryReviews, 5, 3.9);
        galleryHours = new HoursOfOperation(10, 0, 19, 30);
        galleryPrice = 49.99;


        poi = new POI("Vancouver Art Gallery", "Museum", galleryRating, galleryGeo,
                galleryHours, galleryPrice);
    }

    @Test
    public void testConstructor() {
        assertEquals("Vancouver Art Gallery", poi.getName());
        assertEquals("Museum", poi.getType());
        assertEquals(galleryRating, poi.getRating());
        assertEquals(galleryGeo, poi.getGeoLocation());
        assertEquals(galleryHours, poi.getHoursOfOperation());
        assertEquals(galleryPrice, poi.getPrice());
    }

    @Test
    public void testSetPOI() {
        Review test1 = new Review("Kate", "Good");
        Review test2 = new Review("Paige", "Bad");
        Review test3 = new Review("Nancy", "Toronto gallery is better");
        Review test4 = new Review("Kevin", "Meh");
        Review test5 = new Review("Patrick", "I love it!");
        List<Review> testReviews = new ArrayList<>();
        testReviews.add(test1);
        testReviews.add(test2);
        testReviews.add(test3);
        testReviews.add(test4);
        testReviews.add(test5);
        testRating = new Rating(testReviews, 5, 3.9);
        testGeoLocation = new GeoLocation(2205, "Main Mall", "Vancouver",
                "BC", "V6T 1Z4");
        testHours = new HoursOfOperation(5, 15, 15, 46);

        poi.setName("Montreal Gallery");
        assertEquals("Montreal Gallery", poi.getName());
        poi.setType("Beach");
        assertEquals("Beach", poi.getType());
        poi.setRating(testRating);
        assertEquals(testRating, poi.getRating());
        poi.setGeoLocation(testGeoLocation);
        assertEquals(testGeoLocation, poi.getGeoLocation());
        poi.setHoursOfOperation(testHours);
        assertEquals(testHours, poi.getHoursOfOperation());
        poi.setPrice(89.99);
        assertEquals(89.99, poi.getPrice());
    }



}
