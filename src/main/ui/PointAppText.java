package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointAppText {
    private static final String JSON_STORE = "./data/feedCollection.json";
    private Scanner input;
    private FeedCollection feedCollection;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: run the Point Application
    public PointAppText() throws FileNotFoundException {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        feedCollection = new FeedCollection("Collection");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPointApp();
    }

    // MODIFIES:  this
    // EFFECTS: processes user input

    private void runPointApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.print("Thank you! Hope to see you soon!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            selectPOI();
        } else if (command.equals("l")) {
            listAvailablePOI();
        } else if (command.equals("a")) {
            addPOI();
        } else if (command.equals("r")) {
            ratePOI();
        } else if (command.equals("i")) {
            infoPOI();
        } else if (command.equals("load")) {
            loadFeedCollection();
        } else if (command.equals("save")) {
            saveFeedCollection();
        } else {
            System.out.println("Hmm, seems like your selection is invalid, please try again!");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tload -> load collection of POIs");
        System.out.println("\tl -> list available POI");
        System.out.println("\ti -> more info about a particular POI");
        System.out.println("\tc -> search by categories POI");
        System.out.println("\ta -> add POI");
        System.out.println("\tr -> rate POI");
        System.out.println("\tsave -> save collection of POIs");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays menu of options to user in select section
    private void displayMenuSelect() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> Park");
        System.out.println("\tr -> Restaurant");
        System.out.println("\tm -> Museum");
        System.out.println("\tb -> Beach");
    }

    // EFFECTS: lists all available POIs stored in poiList
    private void listAvailablePOI() {
        for (POI i : feedCollection.getPoiList()) {
            System.out.print(i.getName());
            System.out.print(" - ");
            System.out.println(i.getType());
        }
    }



    // EFFECTS: displays info
    private void infoPOI() {
        System.out.println("List of available POIs: ");
        for (int p = 0; p < feedCollection.getPoiList().size(); p++) {
            System.out.print(p + 1);
            System.out.print(" - ");
            System.out.println(feedCollection.getPoiList().get(p).getName());
        }
        int poiIndex = input.nextInt() - 1;
        displayInfo(feedCollection.getPoiList().get(poiIndex));
    }

    // EFFECTS: displays full info about a particular POI
    private void displayInfo(POI poi) {
        System.out.print("Name: ");
        System.out.println(poi.getName());
        System.out.print("Location: ");
        System.out.println(poi.getGeoLocation().toString());
        System.out.print("Hours of operation: ");
        System.out.println(poi.getHoursOfOperation().toString());
        System.out.print("Price: ");
        System.out.println(poi.getPrice());
        System.out.print("Rating: ");
        System.out.println(poi.getRating().getAverageRating());
        System.out.println("Reviews: ");
        List<Review> reviewList = poi.getRating().getReviews();
        for (Review r: reviewList) {
            System.out.println(r.toString());
        }
    }

    // EFFECTS: conducts select process
    private void selectPOI() {
        displayMenuSelect();
        System.out.println("Please specify the type of POI: ");
        String selectedType = input.next();

        if (selectedType.equals("p")) {
            selectedType = "Park";
        } else if (selectedType.equals("r")) {
            selectedType = "Restaurant";
        } else if (selectedType.equals("m")) {
            selectedType = "Museum";
        } else if (selectedType.equals("B")) {
            selectedType = "Beach";
        }

        for (POI i : feedCollection.getPoiList()) {
            if (selectedType.equals(i.getType())) {
                System.out.print(i.getName());
                System.out.print(" - ");
                System.out.println(i.getType());
            }
        }
    }

    // EFFECTS: creates interface for adding POI name, returns name
    private String addNameInterface() {
        System.out.println("Please provide the name of POI: ");
        String addName = input.next();
        return addName;
    }

    // EFFECTS: creates interface for adding POI type, returns type
    private String addTypeInterface() {
        System.out.println("Please specify the type of POI: ");
        System.out.println("\tp -> Park");
        System.out.println("\tr -> Restaurant");
        System.out.println("\tm -> Museum");
        System.out.println("\tb -> Beach");
        String selectedType = input.next();
        if (selectedType.equals("p")) {
            return "Park";
        } else if (selectedType.equals("r")) {
            return "Restaurant";
        } else if (selectedType.equals("m")) {
            return "Museum";
        } else if (selectedType.equals("b")) {
            return "Beach";
        } else {
            return "Not selected";
        }
    }

    // EFFECTS: creates interface for adding POI numeric rating, returns numeric rating
    private double addRatingNumInterface() {
        System.out.println("Please rate this POI on a scale from 0 to 5 inclusively: ");
        double addRatingNum = input.nextInt();
        return addRatingNum;
    }

    // EFFECTS: creates interface for adding POI review, returns review
    private String addReviewTextInterface() {
        System.out.println("Please write a review for this POI: ");
        String addReviewText = input.next();
        return addReviewText;
    }

    // EFFECTS: creates interface for adding POI reviewer name, returns reviewer name
    private String addReviewerInterface() {
        System.out.println("Please provide your first name or nickname: ");
        String addReviewer = input.next();
        return addReviewer;
    }

    // EFFECTS: creates interface for adding POI street number, returns street number
    private String addStreetNumberInterface() {
        System.out.println("Please provide the street number for the POI: ");
        String addStreetNumber = input.next();
        return addStreetNumber;
    }

    // EFFECTS: creates interface for adding POI street name, returns street name
    private String addStreetNameInterface() {
        System.out.println("Please provide the street name for the POI: ");
        String addStreetName = input.next();
        return addStreetName;
    }

    // EFFECTS: creates interface for adding POI city name, returns city name
    private String addCityNameInterface() {
        System.out.println("Please provide the city name for the POI");
        String addCityName = input.next();
        return addCityName;
    }

    // EFFECTS: creates interface for adding POI province abbreviation, returns province abbreviation
    private String addProvinceInterface() {
        System.out.println("Please provide the province abbreviation for POI (i.e. BC, ON, MB)");
        String addProvince = input.next();
        return addProvince;
    }

    // EFFECTS: creates interface for adding POI zip code, returns zip code
    private String addZipCodeInterface() {
        System.out.println("Please provide the zip code for POI");
        String addZipCode = input.next();
        return addZipCode;
    }

    // EFFECTS: creates interface for adding POI start hour, returns start hour
    private int addStartHourInterface() {
        System.out.println("What time does it open? (hours: 0 - 23)");
        int addStartHour = input.nextInt();
        return addStartHour;
    }

    // EFFECTS: creates interface for adding POI start minute, returns start minute
    private int addStartMinuteInterface() {
        System.out.println("What time does it open? (minutes: 0 - 59)");
        int addStartMinute = input.nextInt();
        return addStartMinute;
    }

    // EFFECTS: creates interface for adding POI end hour, returns end hour
    private int addEndHourInterface() {
        System.out.println("What time does it close? (hours: 0 - 23)");
        int addEndHour = input.nextInt();
        return addEndHour;
    }

    // EFFECTS: creates interface for adding POI end minute, returns end minute
    private int addEndMinuteInterface() {
        System.out.println("What time does it close? (minutes: 0 - 59)");
        int addEndMinute = input.nextInt();
        return addEndMinute;
    }

    // EFFECTS: creates interface for adding POI price, returns price
    private double addPriceInterface() {
        System.out.println("Please provide an average price for this POI");
        double addPrice = input.nextDouble();
        return addPrice;
    }

    // MODIFIES: this
    // EFFECTS: adds specified POI to the list

    private void addPOI() {
        String addName = addNameInterface();
        String addType = addTypeInterface();

        GeoLocation addGeo = new GeoLocation(addStreetNumberInterface(),
                addStreetNameInterface(), addCityNameInterface(), addProvinceInterface(), addZipCodeInterface());

        Review addReview = new Review(addReviewerInterface(), addReviewTextInterface());

        List<Review> addReviews = new ArrayList<>();
        addReviews.add(addReview);

        Rating addRating = new Rating(addReviews, 0, addRatingNumInterface());

        HoursOfOperation addHours = new HoursOfOperation(addStartHourInterface(),
                addStartMinuteInterface(), addEndHourInterface(), addEndMinuteInterface());

        POI addPOI = new POI(addName, addType, addRating, addGeo, addHours, addPriceInterface());

        feedCollection.addToList(addPOI);
    }

    // MODIFIES: this
    // EFFECTS: assigns rating to a specified POI
    private void ratePOI() {
        System.out.println("Please select one of the POIs to rate: ");
        for (int p = 0; p < feedCollection.getPoiList().size(); p++) {
            System.out.print(p + 1);
            System.out.print(" - ");
            System.out.println(feedCollection.getPoiList().get(p).getName());
        }
        int poiIndex = input.nextInt() - 1;
        POI ratePOI = feedCollection.getPoiList().get(poiIndex);
        System.out.println("Please rate this POI on a scale from 0 to 5 inclusively: ");
        double rateNum = input.nextInt();
        System.out.println("Please provide review for POI: ");
        String rateText = input.next();
        System.out.println("Please provide your name or nickname: ");
        String rateReviewer = input.next();
        Review rateReview = new Review(rateReviewer, rateText);
        ratePOI.getRating().generalRating(rateNum, rateReview);
    }

    // EFFECTS: saves the workroom to file
    private void saveFeedCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(feedCollection);
            jsonWriter.close();
            System.out.println("Saved " + feedCollection.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFeedCollection() {
        try {
            feedCollection = jsonReader.read();
            System.out.println("Loaded " + feedCollection.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
