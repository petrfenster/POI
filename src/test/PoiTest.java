import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class PoiTest extends JsonTest {
    private POI poi;
    private Rating testRating;
    private GeoLocation testGeoLocation;
    private HoursOfOperation testHours;
    private Rating galleryRating;
    private GeoLocation galleryGeo;
    private HoursOfOperation galleryHours;
    private double galleryPrice;

    @BeforeEach
    public void setUp() {
        galleryGeo = new GeoLocation("750", "Hornby St", "Vancouver",
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
        testGeoLocation = new GeoLocation("2205", "Main Mall", "Vancouver",
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

    @Test
    public void testToJson() {
        JSONObject jsonObject = poi.toJson();
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        double averageRating = jsonObject.getDouble("ratingAverageRating");
        String streetName = jsonObject.getString("geoStreetName");
        String streetNumber = jsonObject.getString("geoStreetNumber");
        String cityName = jsonObject.getString("geoCityName");
        String province = jsonObject.getString("geoProvince");
        String zipCode = jsonObject.getString("geoZipCode");
        int startHour = jsonObject.getInt("startHour");
        int endHour = jsonObject.getInt("endHour");
        int startMinute = jsonObject.getInt("startMinute");
        int endMinute = jsonObject.getInt("endMinute");
        double price = jsonObject.getDouble("price");
        JSONArray ratingReviewsJson = jsonObject.getJSONArray("ratingReviews");
        List<Review> reviews = reviewsFromJson(ratingReviewsJson);
        Rating rating = new Rating(reviews, reviews.size(), averageRating);
        GeoLocation geoLocation = new GeoLocation(streetNumber, streetName, cityName, province, zipCode);
        HoursOfOperation hoursOfOperation = new HoursOfOperation(startHour, startMinute, endHour, endMinute);
        POI poi_test = new POI(name, type, rating, geoLocation, hoursOfOperation, price);
        checkPOI(poi.getName(), poi.getType(), poi.getRating(), poi.getGeoLocation(), poi.getHoursOfOperation(),
                poi.getPrice(), poi_test);
    }

    public List<Review> reviewsFromJson(JSONArray jsonArray) {
        List<Review> ratingReviws  = new ArrayList<>();
        for (Object json: jsonArray) {
            JSONObject nextReview = (JSONObject) json;
            ratingReviws.add(reviewFromJsonObject(nextReview));
        }
        return ratingReviws;
    }

    public Review reviewFromJsonObject(JSONObject jsonObject) {
        String reviewer = jsonObject.getString("reviewer");
        String review = jsonObject.getString("review");
        Review jsonReview = new Review(reviewer, review);
        return jsonReview;
    }




}
