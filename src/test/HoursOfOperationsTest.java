import model.HoursOfOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HoursOfOperationsTest {
    private HoursOfOperation hoursOfOperation;

    @BeforeEach
    public void setUp() {
        hoursOfOperation = new HoursOfOperation(11, 30, 23, 30);
    }

    @Test
    public void testToString() {
        assertEquals("from 11:30 to 23:30", hoursOfOperation.toString());
    }
}
