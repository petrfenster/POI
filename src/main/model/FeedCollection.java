package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class FeedCollection implements Writable {
    private String name;
    private List<POI> poiList;

    // constructor
    public FeedCollection(String name) {
        this.name = name;
        this.poiList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: sets the name for this
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns the name of the FeedCollection
    public String getName() {
        return name;
    }

    public void setPoiList(List<POI> poiList) {
        this.poiList = poiList;
    }

    // EFFECTS: returns the list of POIs inside the FeedCollection
    public List<POI> getPoiList() {
        return poiList;
    }

    // MODIFIES: this
    // EFFECTS: adds POI to the list of POIs
    public void addToList(POI newPOI) {
        poiList.add(newPOI);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pois", poiListToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    public JSONArray poiListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (POI poi : poiList) {
            jsonArray.put(poi.toJson());
        }
        return jsonArray;
    }
}
