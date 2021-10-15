package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointApp {
    private Scanner input;
    private List<POI> poiList;

    // EFFECTS: run the Point Application
    public PointApp() {
        runPointApp();
    }

    // MODIFIES:  this
    // EFFECTS: processes user input

    private void runPointApp() {
        boolean keepGoing = true;
        String command = null;

        init();

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
        if (command.equals("s")) {
            selectPOI();
        } else if (command.equals("a")) {
            addPOI();
        } else if (command.equals("r")) {
            ratePOI();
        } else {
            System.out.println("Hmm, seems like your selection is invalid, please try again!");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> select POI");
        System.out.println("\ta -> add POI");
        System.out.println("\tr -> rate POI");
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

        selectCycle(selectedType);
    }

    // EFFECTS: runs through the list of POIs and compares POIs to specified type
    private void selectCycle(String selectedType) {
        for (POI i : poiList) {
            if (selectedType.equals(i.getType())) {
                System.out.print("Name: ");
                System.out.println(i.getName());
                System.out.print("Rating: ");
                System.out.println(i.getRating().toString());
                System.out.print("Location: ");
                System.out.println(i.getGeoLocation().toString());
                System.out.print("Hours of operation: ");
                System.out.println(i.getHoursOfOperation().toString());
                System.out.print("Price: ");
                System.out.println(i.getPrice());
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
        String addType = input.next();
        return addType;
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
    private int addStreetNumberInterface() {
        System.out.println("Please provide the street number for the POI: ");
        int addStreetNumber = input.nextInt();
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

        poiList.add(addPOI);
    }

    // MODIFIES: this
    // EFFECTS: assigns rating to a specified POI
    private void ratePOI() {
        System.out.println("Please provide the name of POI you wish to rate: ");
        String rateName = input.next();
        for (POI i : poiList) {
            if (rateName.equals(i.getName())) {
                System.out.println("Please rate this POI on a scale from 0 to 5 inclusively: ");
                double rateNum = input.nextInt();
                System.out.println("Please provide review for POI: ");
                String rateText = input.next();
                System.out.println("Please provide your name or nickname: ");
                String rateReviewer = input.next();
                Review rateReview = new Review(rateReviewer, rateText);
                i.getRating().generalRating(rateNum, rateReview);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: initializes POIs
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        poiList = new ArrayList<>();

        initializeCapilano();
        initializeKinton();
    }

    // MODIFIES: this
    // EFFECTS: creates POI capilano and adds to poiList
    private void initializeCapilano() {
        GeoLocation capilanoGeo = new GeoLocation(3735, "Capilano Rd",
                "North Vancouver", "BC", "V7R 4J1");

        Review capilano1 = new Review("Shaon", "Very nice!");
        Review capilano2 = new Review("Vaibhav", "Meh");
        Review capilano3 = new Review("Petr", "Overpriced LOL");
        Review capilano4 = new Review("Kash", "fine");

        List<Review> capilanoReviews = new ArrayList<>();

        capilanoReviews.add(capilano1);
        capilanoReviews.add(capilano2);
        capilanoReviews.add(capilano3);
        capilanoReviews.add(capilano4);

        Rating capilanoRating = new Rating(capilanoReviews, 4, 3.5);
        HoursOfOperation capilanoHours = new HoursOfOperation(9, 0, 17, 0);

        POI capilano = new POI("Capilano Suspension Bridge", "Park", capilanoRating, capilanoGeo,
                capilanoHours, 50);
        poiList.add(capilano);
    }

    // MODIFIES: this
    // EFFECTS: creates POI Kinton and adds to poiList
    private void initializeKinton() {
        GeoLocation kintonGeo = new GeoLocation(6111, "University Blvd", "Vancouver",
                "BC", "V6T 0C7");

        Review kinton1 = new Review("Shaon", "Very tasty");
        Review kinton2 = new Review("Vaibhav", "Could be better");
        Review kinton3 = new Review("Petr", "I wish there were more vegan options");
        Review kinton4 = new Review("Kash", "They have gluten free options");
        Review kinton5 = new Review("Alec", "Nice");

        List<Review> kintonReviews = new ArrayList<>();

        kintonReviews.add(kinton1);
        kintonReviews.add(kinton2);
        kintonReviews.add(kinton3);
        kintonReviews.add(kinton4);
        kintonReviews.add(kinton5);

        Rating kintonRating = new Rating(kintonReviews, 5, 4.5);
        HoursOfOperation kintonHours = new HoursOfOperation(8, 0, 22, 0);

        POI kinton = new POI("Kinton Ramen", "Restaurant", kintonRating, kintonGeo,
                kintonHours, 20);

        poiList.add(kinton);
    }

}
