package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FeedCollection feedCollection = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFeedCollection() {
        JsonReader reader = new JsonReader("./data/testEmptyFC.json");
        try {
            FeedCollection feedCollection = reader.read();
            assertEquals("Collection", feedCollection.getName());
            assertEquals(0, feedCollection.getPoiList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testGeneralFC.json");
        try {
            FeedCollection feedCollection = reader.read();
            assertEquals("Collection", feedCollection.getName());
            List<POI> poiList = feedCollection.getPoiList();
            assertEquals(1, poiList.size());
            List<Review> reviews = new ArrayList<>();
            Review review = new Review("Petr", "meh");
            reviews.add(review);
            Rating rating = new Rating(reviews, 1, 3.2);
            GeoLocation geoLocation = new GeoLocation("1",
                    "Capliano rd",
                    "vancouver",
                    "BC",
                    "V6T 1K6");
            HoursOfOperation hoursOfOperation = new HoursOfOperation(8,
                    40, 22, 0);


            checkPOI("Capilano", "Beach", rating, geoLocation, hoursOfOperation, 60, poiList.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}