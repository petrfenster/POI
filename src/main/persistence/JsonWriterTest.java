package persistence;

import model.FeedCollection;
import model.POI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.InitialPOI;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    private FeedCollection feedCollection;
    private InitialPOI initialPOI;

    @BeforeEach
    void setUp() {
        initialPOI = new InitialPOI();
        feedCollection = new FeedCollection("Collection");
        feedCollection.addToList(initialPOI.initializeCapilano());
        feedCollection.addToList(initialPOI.initializeKinton());
        feedCollection.addToList(initialPOI.initializeWreck());
        feedCollection.addToList(initialPOI.initializeTelus());
        feedCollection.addToList(initialPOI.initializeGallery());
    }


    @Test
    void testWriterInvalidFile() {
        try {
            FeedCollection feedCollection = new FeedCollection("Collection");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
            System.out.println("shaon is superior");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmpty() {
        try {
            FeedCollection feedCollection = new FeedCollection("Collection");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFC.json");
            writer.open();
            writer.write(feedCollection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFC.json");
            feedCollection = reader.read();
            assertEquals("Collection", feedCollection.getName());
            assertEquals(0, feedCollection.getPoiList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneral() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFC.json");
            writer.open();
            writer.write(feedCollection);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralFC.json");
            feedCollection = reader.read();
            assertEquals("Collection", feedCollection.getName());
            List<POI> pois = feedCollection.getPoiList();
            assertEquals(5, pois.size());
            POI capilano = initialPOI.initializeCapilano();
            POI kinton = initialPOI.initializeKinton();
            checkPOI(capilano.getName(), capilano.getType(), capilano.getRating(), capilano.getGeoLocation(),
                    capilano.getHoursOfOperation(), capilano.getPrice(), pois.get(0));
            checkPOI(kinton.getName(), kinton.getType(), kinton.getRating(), kinton.getGeoLocation(),
                    kinton.getHoursOfOperation(), kinton.getPrice(), pois.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
