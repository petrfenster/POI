package ui;

import model.Event;
import model.POI;
import model.Review;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.List;


public class PointAppUI extends JFrame {
    private PointAppController pointAppController;
    private JDesktopPane desktop;
    private JInternalFrame starterFrame;
    private JInternalFrame listFrame;
    private JInternalFrame infoFrame;
    private JInternalFrame listSelectFrame;
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 900;
    public static final String IMAGE_FILE = "./data/imagePOI.jpeg";
    // Image Attribute: Point of Interest by Francisca Pimenta from the Noun Project

    public PointAppUI() throws FileNotFoundException {
        pointAppController = new PointAppController();
        desktop = new JDesktopPane();
        starterFrame = new JInternalFrame("Starter Window", false, false, false,
                false);
        starterFrame.setLayout(new BorderLayout());
        starterFrame.setSize(200, 300);
        setContentPane(desktop);
        setTitle("POI Application Starter Window");
        setSize(WIDTH, HEIGHT);
        addButtonPanel();
        starterFrame.pack();
        starterFrame.setVisible(true);
        desktop.add(starterFrame);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString());
                }
            }
        });
        centreOnScreen();
        setVisible(true);
    }



    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7,1));
        buttonPanel.add(new JButton(new LoadCollectionAction()));
        buttonPanel.add(new JButton(new SaveCollectionAction()));
        buttonPanel.add(new JButton(new ListAvailablePointsAction()));
        buttonPanel.add(new JButton(new SearchByCategoryAction()));
        buttonPanel.add(new JButton(new AddPointAction()));
        buttonPanel.add(new JButton(new InfoPOI()));
        buttonPanel.add(new JButton(new RatePOI()));


        starterFrame.add(buttonPanel, BorderLayout.CENTER);
    }

    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


    private class RatePOI extends AbstractAction {
        private JComboBox<Integer> selectPOI;
        private List<POI> poiList;
        private JTextField reviewer;
        private JTextField review;
        private JComboBox<Integer> rating;
        private JInternalFrame rateFrame;
        private JInternalFrame listFrame;

        RatePOI() {
            super("Rate a POI");
        }



        @Override
        public void actionPerformed(ActionEvent evt) {
            poiList = pointAppController.getFeedCollection().getPoiList();
            listFrame = new JInternalFrame("List of Available POIs", false, true,
                    false, false);
            JPanel listPanel = new JPanel();
            selectPOI = new JComboBox<Integer>();
            listPanel.setLayout(new GridLayout(poiList.size() + 2,1));
            for (int i = 0; i < poiList.size(); i++) {
                POI poi = poiList.get(i);
                listPanel.add(new JLabel((i + 1) + " - " + poi.getName()));
                selectPOI.addItem(i + 1);
            }
            listPanel.add(selectPOI);
            listPanel.add(new JButton(new SubmitRateButton()));
            listFrame.add(listPanel);
            listFrame.setVisible(true);
            listFrame.pack();
            listFrame.setSize(300, listFrame.getHeight());
            listFrame.setLocation(WIDTH - listFrame.getWidth(), 0);
            desktop.add(listFrame);
        }

        private class SubmitRateButton extends AbstractAction {
            SubmitRateButton() {
                super("Select");
            }

            @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
            @Override
            public void actionPerformed(ActionEvent evt) {
                listFrame.setVisible(false);
                int selectedPOI = (Integer) selectPOI.getSelectedItem();
                POI poi = poiList.get(selectedPOI - 1);
                rateFrame = new JInternalFrame("Rate a POI", false, true,
                        false, false);
                JPanel ratePanel = new JPanel();
                ratePanel.setLayout(new GridLayout(5,2));
                ratePanel.add(new JLabel(poi.getName()));
                ratePanel.add(new JLabel("  -" + poi.getType()));
                ratePanel.add(new JLabel("Your name: "));
                reviewer = new JTextField();
                ratePanel.add(reviewer);
                ratePanel.add(new JLabel("Your review: "));
                review = new JTextField();
                ratePanel.add(review);
                ratePanel.add(new JLabel("Your rating (1-5): "));
                rating = new JComboBox<Integer>();
                for (int i = 1; i < 6; i++) {
                    rating.addItem(i);
                }
                ratePanel.add(rating);
                ratePanel.add(new JButton(new SubmitRating()));
                rateFrame.add(ratePanel);
                rateFrame.setVisible(true);
                rateFrame.pack();
                rateFrame.setLocation(WIDTH - rateFrame.getWidth() - 50, 0);
                desktop.add(rateFrame);
            }

            private class SubmitRating extends AbstractAction {
                SubmitRating() {
                    super("Submit");
                }

                @Override
                public void actionPerformed(ActionEvent evt) {
                    String reviewerName = reviewer.getText();
                    String reviewText = review.getText();
                    int ratingNum = (Integer) rating.getSelectedItem();
                    int poiIndex = (Integer) selectPOI.getSelectedItem() - 1;
                    pointAppController.ratePOI(poiIndex, ratingNum, reviewText, reviewerName);
                    JOptionPane.showMessageDialog(null,
                            "Your rating has been successfully saved",
                            "System message", JOptionPane.INFORMATION_MESSAGE);
                    rateFrame.setVisible(false);
                }
            }
        }
    }

    // Represents the action when a user wants to load the stored collection of POIs
    private class LoadCollectionAction extends AbstractAction {
        LoadCollectionAction() {
            super("Load Collection");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            pointAppController.loadFeedCollection();
            JOptionPane.showMessageDialog(null,
                    "The POI collection has been successfully loaded",
                    "System message", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    // Represents the action when a user wants to save collection of POIs
    private class SaveCollectionAction extends AbstractAction {
        SaveCollectionAction() {
            super("Save Collection");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            pointAppController.saveFeedCollection();
            JOptionPane.showMessageDialog(null,
                    "The POI collection has been successfully saved",
                    "System message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class ListAvailablePointsAction extends AbstractAction {
        ListAvailablePointsAction() {
            super("List available POIs");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            List<POI> poiList = pointAppController.getFeedCollection().getPoiList();
            if (listFrame != null) {
                listFrame.setVisible(false);
            }
            listFrame = new JInternalFrame("List of Available POIs", false, true,
                    false, false);
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new GridLayout(poiList.size(),1));
            for (POI poi: poiList) {
                listPanel.add(new JLabel(poi.getName() + " - " + poi.getType()));
            }
            pointAppController.listPOI();
            listFrame.add(listPanel, BorderLayout.CENTER);
            listFrame.setVisible(true);
            listFrame.pack();
            listFrame.setSize(300, listFrame.getHeight());
            listFrame.setLocation(WIDTH - listFrame.getWidth(), 0);
            desktop.add(listFrame);
        }
    }

    private class SearchByCategoryAction extends AbstractAction {
        private JInternalFrame categoryFrame;
        private JComboBox<String> categorySelect;

        SearchByCategoryAction() {
            super("Filter by category");
        }



        @Override
        public void actionPerformed(ActionEvent evt) {
            categoryFrame = new JInternalFrame("Filter by category", false, true,
                    false, false);
            JPanel selectPanel = new JPanel();
            categorySelect = new JComboBox<String>();
            categorySelect.addItem("Park");
            categorySelect.addItem("Museum");
            categorySelect.addItem("Restaurant");
            categorySelect.addItem("Beach");
            selectPanel.add(categorySelect, BorderLayout.CENTER);
            selectPanel.add(new JButton(new ListByCategoryAction()));
            categoryFrame.add(selectPanel, BorderLayout.CENTER);
            categoryFrame.setVisible(true);
            categoryFrame.pack();
            categoryFrame.setSize(250, 200);
            categoryFrame.setLocation(0, starterFrame.getHeight());
            desktop.add(categoryFrame);
        }

        private class ListByCategoryAction extends AbstractAction {
            ListByCategoryAction() {
                super("Filter");
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (listSelectFrame != null) {
                    listSelectFrame.setVisible(false);
                }
                categoryFrame.setVisible(false);
                String selectedCategory = (String) categorySelect.getSelectedItem();
                pointAppController.categoryPOI(selectedCategory);
                listSelectFrame = new JInternalFrame("List of " + selectedCategory, false,
                        true, false, false);
                int count = 0;
                JPanel listSelectPanel = new JPanel();
                for (POI i : pointAppController.getFeedCollection().getPoiList()) {
                    if (selectedCategory.equals(i.getType())) {
                        listSelectPanel.add(new JLabel(i.getName()));
                        count = count + 1;
                    }
                }
                listSelectPanel.setLayout(new GridLayout(count,1));
                listSelectFrame.add(listSelectPanel, BorderLayout.CENTER);
                listSelectFrame.setVisible(true);
                listSelectFrame.pack();
                listSelectFrame.setSize(300, listSelectFrame.getHeight());
                listSelectFrame.setLocation(0, starterFrame.getHeight() + categoryFrame.getHeight());
                desktop.add(listSelectFrame);
            }
        }

    }

    private class AddPointAction extends AbstractAction {
        private JTextField nameText;
        private JComboBox<String> typeSelect;
        private JTextField addressStreetNum;
        private JTextField addressStreetName;
        private JTextField addressCityName;
        private JComboBox<String> addressProvince;
        private JTextField addressZipCode;
        private JComboBox<Integer> startHour;
        private JComboBox<Integer> startMinute;
        private JComboBox<Integer> endHour;
        private JComboBox<Integer> endMinute;
        private JTextField reviewer;
        private JTextField review;
        private JComboBox<Integer> ratingNum;
        private JTextField price;
        private JInternalFrame addFrame;


        AddPointAction() {
            super("Add POI");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            addFrame = new JInternalFrame("Add POI", false, true,
                    false, false);
            addFrame.setLayout(new GridLayout(3, 4));

            addFrame.add(namePanel());
            addFrame.add(typePanel());
            addFrame.add(new JLabel("Address:"));
            addFrame.add(addressPanel());
            addFrame.add(new JLabel("Hours of operation:"));
            addFrame.add(hoursPanel());
            addFrame.add(new JLabel("Review:"));
            addFrame.add(reviewPanel());
            addFrame.add(new JLabel("Price:"));
            price = new JTextField();
            addFrame.add(price);
            addFrame.add(new JButton(new SubmitPointAction()));




            addFrame.pack();

            addFrame.setVisible(true);
            addFrame.pack();
            addFrame.setLocation(400 / 2, 200);
            desktop.add(addFrame);
        }

        private class SubmitPointAction extends AbstractAction {
            SubmitPointAction() {
                super("Add POI");
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                String name = nameText.getText();
                String type = (String) typeSelect.getSelectedItem();
                String streetNum = addressStreetNum.getText();
                String streetName = addressStreetName.getText();
                String cityName = addressCityName.getText();
                String province = (String) addressProvince.getSelectedItem();
                String zipCode = addressZipCode.getText();
                int startH = (Integer) startHour.getSelectedItem();
                int startM = (Integer) startMinute.getSelectedItem();
                int endH = (Integer) endHour.getSelectedItem();
                int endM = (Integer) endMinute.getSelectedItem();
                String addReviewer = reviewer.getText();
                String addReview = review.getText();
                int addRating = (Integer) ratingNum.getSelectedItem();
                double addPrice = Double.parseDouble(price.getText());
                pointAppController.addPOI(name, type, streetNum, streetName, cityName, province, zipCode, addReviewer,
                        addReview, addRating, startH, startM, endH, endM, addPrice);
                ImageIcon icon = new ImageIcon(IMAGE_FILE);
                JOptionPane.showMessageDialog(null,
                        "The POI has been successfully added to the collection",
                        "System message", JOptionPane.INFORMATION_MESSAGE, icon);
                addFrame.setVisible(false);



            }
        }

        public JPanel namePanel() {
            JPanel namePanel = new JPanel();
            namePanel.setLayout(new GridLayout(1,2));
            namePanel.add(new JLabel("Name"));
            nameText = new JTextField();
            namePanel.add(nameText);
            return namePanel;
        }

        public JPanel typePanel() {
            JPanel typePanel = new JPanel();
            typePanel.setLayout(new GridLayout(1,2));
            typePanel.add(new JLabel("Type"));
            typeSelect = new JComboBox<String>();
            typeSelect.addItem("Park");
            typeSelect.addItem("Museum");
            typeSelect.addItem("Restaurant");
            typeSelect.addItem("Beach");
            typePanel.add(typeSelect);
            return typePanel;
        }

        public JPanel addressPanel() {
            JPanel addressPanel = new JPanel();
            addressPanel.setLayout(new GridLayout(5, 2));
            addressPanel.add(new JLabel("Street Number: "));
            addressStreetNum = new JTextField();
            addressPanel.add(addressStreetNum);
            addressPanel.add(new JLabel("Street Name: "));
            addressStreetName = new JTextField();
            addressPanel.add(addressStreetName);
            addressPanel.add(new JLabel("City: "));
            addressCityName = new JTextField();
            addressPanel.add(addressCityName);
            addressPanel.add(new JLabel("Province: "));
            addressPanel.add(addAddressProvince());
            addressPanel.add(new JLabel("Zip Code: "));
            addressZipCode = new JTextField();
            addressPanel.add(addressZipCode);
            return addressPanel;
        }

        public JComboBox<String> addAddressProvince() {
            addressProvince = new JComboBox<String>();
            addressProvince.addItem("AB");
            addressProvince.addItem("BC");
            addressProvince.addItem("MB");
            addressProvince.addItem("NB");
            addressProvince.addItem("NL");
            addressProvince.addItem("NT");
            addressProvince.addItem("NS");
            addressProvince.addItem("NU");
            addressProvince.addItem("ON");
            addressProvince.addItem("PE");
            addressProvince.addItem("QC");
            addressProvince.addItem("SK");
            addressProvince.addItem("YT");
            return addressProvince;
        }

        public JPanel hoursPanel() {
            JPanel hoursPanel = new JPanel();
            hoursPanel.setLayout(new GridLayout(4, 2));
            hoursPanel.add(new JLabel("Open hour:"));
            startHour = new JComboBox<Integer>();
            addHour(startHour);
            hoursPanel.add(startHour);
            hoursPanel.add(new JLabel("Open minute:"));
            startMinute = new JComboBox<Integer>();
            addMinute(startMinute);
            hoursPanel.add(startMinute);
            hoursPanel.add(new JLabel("Close hour:"));
            endHour = new JComboBox<Integer>();
            addHour(endHour);
            hoursPanel.add(endHour);
            hoursPanel.add(new JLabel("Close minute:"));
            endMinute = new JComboBox<Integer>();
            addMinute(endMinute);
            hoursPanel.add(endMinute);
            return hoursPanel;
        }

        public void addHour(JComboBox<Integer> comboBox) {
            for (int hour = 0; hour < 24; hour++) {
                comboBox.addItem(hour);
            }
        }

        public void addMinute(JComboBox<Integer> comboBox) {
            comboBox.addItem(0);
            comboBox.addItem(15);
            comboBox.addItem(30);
            comboBox.addItem(45);
        }

        public JPanel reviewPanel() {
            JPanel reviewPanel = new JPanel();
            reviewPanel.setLayout(new GridLayout(3, 2));
            reviewPanel.add(new JLabel("Your name: "));
            reviewer = new JTextField();
            reviewPanel.add(reviewer);
            reviewPanel.add(new JLabel("Your review: "));
            review = new JTextField();
            reviewPanel.add(review);
            reviewPanel.add(new JLabel("Your rating: "));
            ratingNum = new JComboBox<Integer>();
            ratingNum.addItem(1);
            ratingNum.addItem(2);
            ratingNum.addItem(3);
            ratingNum.addItem(4);
            ratingNum.addItem(5);
            reviewPanel.add(ratingNum);
            return reviewPanel;
        }
    }

    private class InfoPOI extends AbstractAction {
        private JComboBox<Integer> selectPOI;
        private List<POI> poiList;
        private JInternalFrame listInfoFrame;

        InfoPOI() {
            super("More Info about a POI");
        }



        @Override
        public void actionPerformed(ActionEvent evt) {
            poiList = pointAppController.getFeedCollection().getPoiList();
            if (infoFrame != null) {
                infoFrame.setVisible(false);
            }
            listInfoFrame = new JInternalFrame("List of Available POIs", false, true,
                    false, false);
            JPanel listPanel = new JPanel();
            selectPOI = new JComboBox<Integer>();
            listPanel.setLayout(new GridLayout(poiList.size() + 2,1));
            for (int i = 0; i < poiList.size(); i++) {
                POI poi = poiList.get(i);
                listPanel.add(new JLabel((i + 1) + " - " + poi.getName()));
                selectPOI.addItem(i + 1);
            }
            listPanel.add(selectPOI);
            listPanel.add(new JButton(new SubmitInfoButton()));
            listInfoFrame.add(listPanel);
            listInfoFrame.setVisible(true);
            listInfoFrame.pack();
            listInfoFrame.setSize(300, listInfoFrame.getHeight());
            listInfoFrame.setLocation(WIDTH - listInfoFrame.getWidth(), 0);
            desktop.add(listInfoFrame);
        }

        private class SubmitInfoButton extends AbstractAction {
            SubmitInfoButton() {
                super("Submit");
            }

            @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
            @Override
            public void actionPerformed(ActionEvent evt) {
                listInfoFrame.setVisible(false);
                int selectedPOI = (Integer) selectPOI.getSelectedItem();
                POI poi = poiList.get(selectedPOI - 1);
                infoFrame = new JInternalFrame("Info about a POI", false, true,
                        false, false);
                JPanel infoPanel = new JPanel();
                List<Review> reviewList = poi.getRating().getReviews();
                infoPanel.setLayout(new GridLayout(reviewList.size() + 7,1));
                infoPanel.add(new JLabel("Name: "));
                infoPanel.add(new JLabel(poi.getName()));
                infoPanel.add(new JLabel("Type: "));
                infoPanel.add(new JLabel(poi.getType()));
                infoPanel.add(new JLabel("Location: "));
                infoPanel.add(new JLabel(poi.getGeoLocation().toString()));
                infoPanel.add(new JLabel("Hours of operation: "));
                infoPanel.add(new JLabel(poi.getHoursOfOperation().toString()));
                infoPanel.add(new JLabel("Price: "));
                infoPanel.add(new JLabel(String.valueOf(poi.getPrice())));
                infoPanel.add(new JLabel("Rating: "));
                infoPanel.add(new JLabel(String.valueOf(poi.getRating().getAverageRating())));
                infoPanel.add(new JLabel("Reviews: "));
                for (Review r: reviewList) {
                    infoPanel.add(new JLabel(r.toString()));
                }
                pointAppController.infoPOI(poi);
                infoFrame.add(infoPanel);
                infoFrame.setVisible(true);
                infoFrame.pack();
                infoFrame.setLocation(WIDTH - infoFrame.getWidth() - 50, 0);
                desktop.add(infoFrame);
            }
        }
    }
}
