import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonTest;
import ui.InitialPOI;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedCollectionTest extends JsonTest {
    private FeedCollection feedCollection;
    private List<POI> poiList1;
    private List<POI> poiList2;
    private POI wreck;


    @BeforeEach
    public void setUp() {
        InitialPOI initialPOI = new InitialPOI();
        POI capilano = initialPOI.initializeCapilano();
        POI telus = initialPOI.initializeCapilano();
        wreck = initialPOI.initializeWreck();
        POI kinton = initialPOI.initializeKinton();
        poiList1 = new ArrayList<>();
        poiList2 = new ArrayList<>();
        poiList1.add(capilano);
        poiList1.add(telus);
        feedCollection = new FeedCollection("POI application");
        feedCollection.addToList(capilano);
        feedCollection.addToList(telus);
        poiList2.add(wreck);
        poiList2.add(kinton);
    }

    @Test
    public void testSetName() {
        feedCollection.setName("Test name");
        assertEquals("Test name", feedCollection.getName());
    }

    @Test
    public void testGetName() {
        assertEquals("POI application", feedCollection.getName());
    }

    @Test
    public void testSetPointList() {
        feedCollection.setPoiList(poiList2);
        assertEquals(poiList2, feedCollection.getPoiList());
    }

    @Test
    public void testGetPointList() {
        assertEquals(poiList1, feedCollection.getPoiList());
    }

    @Test
    public void testAddToList() {
        feedCollection.addToList(wreck);
        assertEquals(wreck, feedCollection.getPoiList().get(feedCollection.getPoiList().size() - 1));
    }

    @Test
    public void testToJson() {
        JSONObject json = feedCollection.toJson();
        assertEquals(feedCollection.getName(), json.getString("name"));
        testPoiListToJson();
    }

    @Test
    public void testPoiListToJson() {
        JSONArray jsonArray = feedCollection.poiListToJson();
        assertEquals(feedCollection.getPoiList().size(), jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            Object object = jsonArray.get(i);
            JSONObject jsonObject = (JSONObject) object;
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
            POI poi = feedCollection.getPoiList().get(i);
            checkPOI(poi.getName(), poi.getType(), poi.getRating(), poi.getGeoLocation(), poi.getHoursOfOperation(),
                    poi.getPrice(), poi_test);
        }
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
