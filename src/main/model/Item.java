package model;

// represents each item in the list- it will have title, due date, category, isCompleted variables
public class Item {
    // stub
    private String name;
    private String daysBeforeDue;
    private Categories category;

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
