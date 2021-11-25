package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads feed collection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FeedCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFeedCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses feed collection from JSON object and returns it
    private FeedCollection parseFeedCollection(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        FeedCollection feedCollection = new FeedCollection(name);
        addPOIs(feedCollection, jsonObject);
        return feedCollection;
    }

    // MODIFIES: wr
    // EFFECTS: parses pois from JSON object and adds them to workroom
    private void addPOIs(FeedCollection feedCollection, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pois");
        for (Object json : jsonArray) {
            JSONObject nextPOI = (JSONObject) json;
            addPOI(feedCollection, nextPOI);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addPOI(FeedCollection feedCollection, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        double averageRating = jsonObject.getDouble("ratingAverageRating");
        String streetName = jsonObject.getString("geoStreetName");
        String streetNumber = jsonObject.getString("geoStreetNumber");
        String cityName = jsonObject.getString("geoCityName");
        String province = jsonObject.getString("geoProvince");
        String zipCode = jsonObject.getString("geoZipCode");
        int startHour = jsonObject.getInt("startHour");
        int endHour = jsonObject.getInt("endHour");
        int startMinute = jsonObject.getInt("startMinute");
        int endMinute = jsonObject.getInt("endMinute");
        double price = jsonObject.getDouble("price");
        JSONArray ratingReviewsJson = jsonObject.getJSONArray("ratingReviews");
        List<Review> reviews = reviewsFromJson(ratingReviewsJson);
        Rating rating = new Rating(reviews, reviews.size(), averageRating);
        GeoLocation geoLocation = new GeoLocation(streetNumber, streetName, cityName, province, zipCode);
        HoursOfOperation hoursOfOperation = new HoursOfOperation(startHour, startMinute, endHour, endMinute);
        POI poi = new POI(name, type, rating, geoLocation, hoursOfOperation, price);
        feedCollection.addToList(poi);
    }

    public List<Review> reviewsFromJson(JSONArray jsonArray) {
        List<Review> ratingReviws  = new ArrayList<>();
        for (Object json: jsonArray) {
            JSONObject nextReview = (JSONObject) json;
            ratingReviws.add(reviewFromJsonObject(nextReview));
        }
        return ratingReviws;
    }

    public Review reviewFromJsonObject(JSONObject jsonObject) {
        String reviewer = jsonObject.getString("reviewer");
        String review = jsonObject.getString("review");
        Review jsonReview = new Review(reviewer, review);
        return jsonReview;
    }
}
