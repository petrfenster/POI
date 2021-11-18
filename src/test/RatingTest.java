import model.Rating;
import model.Review;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RatingTest {
    private Rating rating;
    private List<Review> reviews;
    private Review review1;
    private Review review2;
    private Review review3;
    private Review review4;
    private Review review5;
    private Review review6;


    
    @BeforeEach
    public void setUp() {
        review1 = new Review("Jack", "Beautiful paintings");
        review2 = new Review("Vaibhav", "Could be better");
        review3 = new Review("Petr", "Overpriced");
        review4 = new Review("Kash", "Van Gogh");
        review5 = new Review("Shaon", "Friendly staff");
        review6 = new Review("Barbara", "Terrible");

        reviews = new ArrayList<>();

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);

        rating = new Rating(reviews, 5, 3.9);
    }

    @Test
    public void testConstructor() {
        assertEquals(reviews, rating.getReviews());
        assertEquals(5, rating.getNumRatings());
        assertEquals(3.9, rating.getAverageRating());
    }

    @Test
    public void testSetRating() {
        List<Review> testReviews = new ArrayList<>();
        testReviews.add(review1);
        testReviews.add(review4);
        rating.setReviews(testReviews);
        assertEquals(testReviews, rating.getReviews());
        rating.setNumRatings(7);
        assertEquals(7, rating.getNumRatings());
        rating.setAverageRating(3.7);
        assertEquals(3.7, rating.getAverageRating());
    }

    @Test
    public void testAddOneNumRatings() {
        rating.addOneNumRatings();
        assertEquals(6, rating.getNumRatings());
    }

    @Test
    public void testNewAverageRating() {
        rating.newAverageRating(4.5);
        assertEquals(4, rating.getAverageRating());
    }

    @Test
    public void testAddReview() {
        rating.addReview(review6);
        reviews.add(review6);
        assertEquals(reviews, rating.getReviews());
    }

    @Test
    public void testRemoveReview() {
        rating.removeReview(review4);
        reviews.remove(review4);
        assertEquals(reviews, rating.getReviews());
    }

    @Test
    public void testGeneralRating() {
        rating.generalRating(4.5, review6);
        assertEquals(4, rating.getAverageRating());
        assertEquals(6, rating.getNumRatings());
        reviews.add(review6);
        assertEquals(reviews, rating.getReviews());
    }

    @Test
    public void testReviewsToListOfJson() {
        JSONArray jsonArray = rating.reviewsToListOfJson();
        assertEquals(reviews.size(), jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            Object object = jsonArray.get(i);
            JSONObject json = (JSONObject) object;
            assertEquals(reviews.get(i).getReviewer(), json.getString("reviewer"));
            assertEquals(reviews.get(i).getReview(), json.getString("review"));
        }
    }

}
