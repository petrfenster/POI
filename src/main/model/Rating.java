package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Rating {
    private List<Review> reviews;
    private int numRatings;
    private double averageRating;

    public Rating(List<Review> reviews, int numRatings, double averageRating) {
        this.reviews = reviews;
        this.numRatings = numRatings;
        this.averageRating = averageRating;
    }

    // getters

    public List<Review> getReviews() {
        return reviews;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public double getAverageRating() {
        return averageRating;
    }

    // setters


    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    // MODIFIES: this
    // EFFECTS: adds one to number of ratings
    public void addOneNumRatings() {
        numRatings++;
    }

    // REQUIRES: 0 <= newRating <= 5
    // MODIFIES: this
    // EFFECTS: adds one to number of ratings
    public void newAverageRating(double newRating) {
        double newAverageRating = (averageRating * numRatings + newRating) / (numRatings + 1);
        averageRating = newAverageRating;
        addOneNumRatings();
    }

    // MODIFIES: this
    // EFFECTS: adds a review to list of reviews
    public void addReview(Review review) {
        reviews.add(review);
    }

    // REQUIRES: length of reviews list >= 1, contains certain review
    // MODIFIES: this
    // EFFECTS: removes a review from the list of reviews
    public void removeReview(Review review) {
        reviews.remove(review);
    }

    // MODIFIES: this
    // EFFECTS: modifies the average rating based on the new received rating
    //          adds a newly received review to the list of reviews
    public void generalRating(double newRating, Review review) {
        newAverageRating(newRating);
        addReview(review);
    }

    //EFFECTS: returns string representation of rating

    public List<List<String>> reviewsToListOfListOfString() {
        List<List<String>> reviewsList = new ArrayList<>();
        for (Review r : reviews) {
            reviewsList.add(r.toList());
        }
        return reviewsList;
    }

    public JSONArray reviewsToListOfJson() {
        JSONArray jsonArray = new JSONArray();
        for (Review r : reviews) {
            jsonArray.put(r.toJsonObject());
        }
        return jsonArray;
    }
}
