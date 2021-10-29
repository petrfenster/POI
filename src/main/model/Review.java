package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private String reviewer;
    private String review;

    public Review(String reviewer, String review) {
        this.reviewer = reviewer;
        this.review = review;
    }

    // getters
    public String getReviewer() {
        return reviewer;
    }

    public String getReview() {
        return review;
    }

    //setters
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String toString() {
        return reviewer + " : " + review;
    }

    public List<String> toList() {
        List<String> reviewList = new ArrayList<>();
        reviewList.add(reviewer);
        reviewList.add(review);
        return reviewList;
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("reviewer", reviewer);
        json.put("review", review);
        return json;
    }
}
