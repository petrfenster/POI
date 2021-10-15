package model;

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
}
