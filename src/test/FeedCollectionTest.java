import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.InitialPOI;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedCollectionTest {
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
}
