package model;

/*
 * Represents each item in the list- it will have a title, due date, and category
 */

public class Item {

    private String name;
    private String daysBeforeDue;
    private Categories category;

    // EFFECTS: creates new item with parameterized values
    public Item(String title, String daysBeforeDue, Categories category) {
        this.name = title;
        this.daysBeforeDue = daysBeforeDue;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public String getDaysBeforeDue() {
        return this.daysBeforeDue;
    }

    public Categories getCategory() {
        return this.category;
    }

}
