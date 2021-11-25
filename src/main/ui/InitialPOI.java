package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class InitialPOI {

    public InitialPOI() {}

    // EFFECTS: initializes POI capilano and adds to poiList
    public POI initializeCapilano() {
        GeoLocation geo = new GeoLocation("3735", "Capilano Rd",
                "North Vancouver", "BC", "V7R 4J1");

        HoursOfOperation hours = new HoursOfOperation(9, 0, 17, 0);

        Review review1 = new Review("Shaon", "Very nice!");
        Review review2 = new Review("Vaibhav", "Meh");
        Review review3 = new Review("Petr", "Overpriced LOL");
        Review review4 = new Review("Kash", "fine");

        List<Review> reviews = new ArrayList<>();

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);

        Rating rating = new Rating(reviews, 4, 3.5);

        POI poi = new POI("Capilano Suspension Bridge", "Park", rating, geo,
                hours, 50);

        return poi;
    }

    // EFFECTS: initializes POI Kinton and adds to poiList
    public POI initializeKinton() {
        GeoLocation geo = new GeoLocation("6111", "University Blvd", "Vancouver",
                "BC", "V6T 0C7");

        HoursOfOperation hours = new HoursOfOperation(8, 0, 22, 0);

        Review review1 = new Review("Shaon", "Very tasty");
        Review review2 = new Review("Vaibhav", "Could be better");
        Review review3 = new Review("Petr", "I wish there were more vegan options");
        Review review4 = new Review("Kash", "They have gluten free options");
        Review review5 = new Review("Alec", "Nice");

        List<Review> reviews = new ArrayList<>();

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);

        Rating rating = new Rating(reviews, 5, 4.5);

        POI poi = new POI("Kinton Ramen", "Restaurant", rating, geo,
                hours, 20);

        return poi;
    }

    // EFFECTS: initializes POI Wreck
    public POI initializeWreck() {

        GeoLocation geo = new GeoLocation("1", "SW Marine Dr", "Vancouver",
                "BC", "V6T 1Z4");

        HoursOfOperation hours = new HoursOfOperation(8, 0, 21, 0);

        Review review1 = new Review("Shaon", "Nice sunset");
        Review review2 = new Review("Vaibhav", "Cold water");
        Review review3 = new Review("Petr", "Nude people");
        Review review4 = new Review("Kash", "Cool");
        Review review5 = new Review("Alec", "Okay");

        List<Review> reviews = new ArrayList<>();

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);

        Rating rating = new Rating(reviews, 5, 4.1);

        POI poi = new POI("Wreck Beach", "Beach", rating, geo,
                hours, 0);

        return poi;
    }

    public POI initializeTelus() {

        GeoLocation geo = new GeoLocation("1455", "Quebec St", "Vancouver",
                "BC", "V6A 327");

        HoursOfOperation hours = new HoursOfOperation(10, 0, 17, 0);

        Review review1 = new Review("Shaon", "Expensive!");
        Review review2 = new Review("Vaibhav", "Only for kids");
        Review review3 = new Review("Petr", "Meh");
        Review review4 = new Review("Kash", "Nice exhibition");
        Review review5 = new Review("Alec", "Interesting");

        List<Review> reviews = new ArrayList<>();

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);

        Rating rating = new Rating(reviews, 5, 2.7);

        POI poi = new POI("TELUS World of Science", "Museum", rating, geo,
                hours, 70);

        return poi;
    }

    public POI initializeGallery() {
        GeoLocation geo = new GeoLocation("750", "Hornby St", "Vancouver",
                "BC", "V6Z 2H7");

        Review gallery1 = new Review("Jack", "Beautiful paintings");
        Review gallery2 = new Review("Vaibhav", "Could be better");
        Review gallery3 = new Review("Petr", "Overpriced");
        Review gallery4 = new Review("Kash", "Van Gogh");
        Review gallery5 = new Review("Shaon", "Friendly staff");

        List<Review> reviews = new ArrayList<>();

        reviews.add(gallery1);
        reviews.add(gallery2);
        reviews.add(gallery3);
        reviews.add(gallery4);
        reviews.add(gallery5);

        Rating rating = new Rating(reviews, 5, 3.9);
        HoursOfOperation hours = new HoursOfOperation(10, 0, 19, 30);
        double price = 49.99;


        POI poi = new POI("Vancouver Art Gallery", "Museum", rating, geo,
                hours, price);

        return poi;
    }
}
