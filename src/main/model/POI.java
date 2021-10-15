package model;

import model.GeoLocation;
import model.HoursOfOperation;
import model.Rating;

public class POI {
    private String name;
    private String type;
    private Rating rating;
    private GeoLocation geoLocation;
    private HoursOfOperation hoursOfOperation;
    private double price;

    public POI(String name,
               String type,
               Rating rating,
               GeoLocation geoLocation,
               HoursOfOperation hoursOfOperation,
               double price) {
        this.name = name;
        this.type = type;
        this.rating = rating;
        this.geoLocation = geoLocation;
        this.hoursOfOperation = hoursOfOperation;
        this.price = price;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Rating getRating() {
        return rating;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public HoursOfOperation getHoursOfOperation() {
        return hoursOfOperation;
    }

    public double getPrice() {
        return price;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public void setHoursOfOperation(HoursOfOperation hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


