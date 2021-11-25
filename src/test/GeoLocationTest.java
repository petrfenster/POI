import model.GeoLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeoLocationTest {
    private GeoLocation geo;

    @BeforeEach
    public void setUp() {
        geo = new GeoLocation("750", "Hornby St", "Vancouver", "BC",
                "V6Z 2H7");
    }

    @Test
    public void testConstructor() {
        assertEquals("750", geo.getStreetNumber());
        assertEquals("Hornby St", geo.getStreetName());
        assertEquals("Vancouver", geo.getCityName());
        assertEquals("BC", geo.getProvince());
        assertEquals("V6Z 2H7", geo.getZipCode());
    }

    @Test
    public void testSetGeoLocation() {
        geo.setStreetNumber("89");
        assertEquals("89", geo.getStreetNumber());
        geo.setStreetName("Lower Mall");
        assertEquals("Lower Mall", geo.getStreetName());
        geo.setCityName("Toronto");
        assertEquals("Toronto", geo.getCityName());
        geo.setProvince("AB");
        assertEquals("AB", geo.getProvince());
        geo.setZipCode("V6T 1Z4");
        assertEquals("V6T 1Z4", geo.getZipCode());
    }

    @Test
    public void testToString() {
        assertEquals("750 Hornby St, Vancouver, BC V6Z 2H7", geo.toString());
    }
}
