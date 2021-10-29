package persistence;

import model.FeedCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            FeedCollection feedCollection = new FeedCollection("Collection");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            FeedCollection feedCollection = new FeedCollection("Collection");
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(feedCollection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            feedCollection = reader.read();
            assertEquals("Collection", feedCollection.getName());
            assertEquals(0, feedCollection.getPoiList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
