package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PointAppController {
    private static final String JSON_STORE = "./data/feedCollection.json";
    private FeedCollection feedCollection;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PointAppController() {
        feedCollection = new FeedCollection("Collection");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public FeedCollection getFeedCollection() {
        return feedCollection;
    }

    public void addPOI(String addName, String addType, String addStreetNum, String addStreetName, String addCityName,
                        String addProvince, String addZipCode, String addReviewer, String addReviewText,
                        int addRatingNum, int addStartHour, int addStartMinute, int addEndHour, int addEndMinute,
                        double addPrice) {

        GeoLocation addGeo = new GeoLocation(addStreetNum,
                addStreetName, addCityName, addProvince, addZipCode);

        Review addReview = new Review(addReviewer, addReviewText);

        List<Review> addReviews = new ArrayList<>();
        addReviews.add(addReview);

        Rating addRating = new Rating(addReviews, 0, addRatingNum);

        HoursOfOperation addHours = new HoursOfOperation(addStartHour,
                addStartMinute, addEndHour, addEndMinute);

        POI addPOI = new POI(addName, addType, addRating, addGeo, addHours, addPrice);

        feedCollection.addToList(addPOI);
        EventLog.getInstance().logEvent(new Event("Added POI: " + addPOI.getName() + " - "
                + addPOI.getType()));

    }

    // MODIFIES: this
    // EFFECTS: assigns rating to a specified POI
    public void ratePOI(int poiIndex, double rateNum, String rateText, String rateReviewer) {
        POI ratePOI = feedCollection.getPoiList().get(poiIndex);
        Review rateReview = new Review(rateReviewer, rateText);
        feedCollection.getPoiList().get(poiIndex).getRating().generalRating(rateNum, rateReview);
        EventLog.getInstance().logEvent(new Event(rateReviewer + " rated " + ratePOI.getName()));


    }

    // EFFECTS: saves the workroom to file
    public void saveFeedCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(feedCollection);
            jsonWriter.close();
            EventLog.getInstance().logEvent(new Event("Collection has been saved"));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadFeedCollection() {
        try {
            feedCollection = jsonReader.read();
            EventLog.getInstance().logEvent(new Event("Collection has been loaded"));
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void infoPOI(POI poi) {
        EventLog.getInstance().logEvent(new Event("Additional info about " + poi.getName()
                + " has been requested"));
    }

    public void listPOI() {
        EventLog.getInstance().logEvent(new Event("All available POIs have been listed"));
    }

    public void categoryPOI(String typePOI) {
        EventLog.getInstance().logEvent(new Event("All the POIs of the category " + typePOI
                + " have been listed"));
    }




}
