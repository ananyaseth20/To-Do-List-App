package model;

/*
 * Represents each item in the list- it will have a title, due date, and category
 */

import org.json.JSONObject;
import persistence.Writable;

public class Item implements Writable {

    private String title;
    private String daysBeforeDue;
    private Categories category;

    // EFFECTS: creates new item with parameterized values
    public Item(String title, String daysBeforeDue, Categories category) {
        this.title = title;
        this.daysBeforeDue = daysBeforeDue;
        this.category = category;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDaysBeforeDue() {
        return this.daysBeforeDue;
    }

    public Categories getCategory() {
        return this.category;
    }

    // source: JSONSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", title);
        json.put("daysBeforeDue", daysBeforeDue);
        json.put("category", category);
        return json;
    }

}
