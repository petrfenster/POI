import model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testReview {
    private Review review;

    @BeforeEach
    public void setReview() {
        review = new Review("Jack", "Beautiful paintings");
    }

    @Test
    public void testConstructor() {
        assertEquals("Jack", review.getReviewer());
        assertEquals("Beautiful paintings", review.getReview());
    }

    @Test
    public void testSetReview() {
        review.setReviewer("Maria");
        review.setReview("Wonderful");
        assertEquals("Maria", review.getReviewer());
        assertEquals("Wonderful", review.getReview());
    }

    @Test
    public void testToString() {
        assertEquals("Jack : Beautiful paintings", review.toString());
    }

}
