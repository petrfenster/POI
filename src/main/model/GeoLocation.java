package model;

public class GeoLocation {
    private int streetNumber;
    private String streetName;
    private String cityName;
    private String province;
    private String zipCode;

    /*
     REQUIRES: cityName has a non-zero length
               province has a non-zero length
               zipCode has a non-zero length
     EFFECTS: geographical location is set using street name (optional), street number (optional), city name,
              province abbreviation (BC, ON, etc.), and zip code.
    */
    public GeoLocation(int streetNumber, String streetName, String cityName, String province, String zipCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.cityName = cityName;
        this.province = province;
        this.zipCode = zipCode;
    }

    // getters

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getProvince() {
        return province;
    }

    public String getZipCode() {
        return zipCode;
    }

    //setters

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    //EFFECTS: returns string representation of geolocation

    public String toString() {
        return streetNumber + " " + streetName + ", " + cityName + ", " + province + " " + zipCode;
    }





}
