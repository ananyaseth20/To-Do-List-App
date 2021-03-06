package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

/*
 * Represents a to-do list
 */

public class ToDoList implements Writable {

    private String name;
    private ArrayList<Item> toDoList;

    // EFFECTS: initializes new ArrayList
    public ToDoList(String name) {
        this.name = name;
        toDoList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: creates item and adds it to list then sorts list
    public void insert(Item item) {
        toDoList.add(item);
        sortByDueDate();
        sortByCategory();
        System.out.println("Added!");
    }

    // MODIFIES: this
    // EFFECTS: creates item and adds it to list
    public void insertGUI(Item item) {
        toDoList.add(item);
        System.out.println("Added!");
    }

    // REQUIRES: the list has at least removeIndex items, if removeIndex != -1
    // MODIFIES: this
    // EFFECTS: removes item at given index in array and sorts list
    public void remove(int removeIndex) {
        sortByDueDate();
        sortByCategory();
        int length = toDoList.size();

        if (length < removeIndex) {
            System.out.println("Invalid input");
        } else {
            for (int i = 0; i < length; i++) {
                if ((i + 1) == removeIndex) {
                    toDoList.remove(getItemAtIndex(i + 1));
                    System.out.println("Removed successfully!");
                }
            }
        }
    }

    // REQUIRES: the list is not empty
    // MODIFIES: this
    // EFFECTS: sorts the items in the list on the basis of when they are due(ascending order)
    public void sortByDueDate() {
        toDoList.sort(Comparator.comparing(Item::getDaysBeforeDue));
    }

    // EFFECTS: returns true if to-do list contains given item
    public boolean contains(Item item) {
        return toDoList.contains(item);
    }

    // EFFECTS: returns size of to-do list
    public int getSize() {
        return toDoList.size();
    }

    // EFFECTS: returns item at given index in to-do list
    public Item getItemAtIndex(int i) {
        return toDoList.get(i - 1);
    }

    // EFFECTS: returns index of given item in to-do list, -1 if not present
    public int returnIndex(Item item) {
        int count = 1;
        for (Item i : toDoList) {
            if (i.equals(item)) {
                return count;
            } else {
                count++;
            }
        }
        System.out.println("Item not present");
        return -1;
    }

    // write in todolistapp
    // REQUIRES: the list is not empty
    // MODIFIES: this
    // EFFECTS: sorts the items in the list on the basis of category
    public void sortByCategory() {
        ArrayList<Item> newList = new ArrayList<Item>();
        // high priority
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).getCategory().equals(Categories.HIGHPRIORITY)) {
                newList.add(toDoList.get(i));
            }
        }
        // mid priority
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).getCategory().equals(Categories.MIDPRIORITY)) {
                newList.add(toDoList.get(i));
            }
        }
        // low priority
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).getCategory().equals(Categories.LOWPRIORITY)) {
                newList.add(toDoList.get(i));
            }
        }
        toDoList = newList;
    }

    // EFFECTS: returns the last index of a particular category in the list
    public int lastIndexOfCategory(Categories category) {
        int lastIndex = 0;
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).getCategory().equals(category)) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    // source: JSONSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("toDoList", itemsToJson());
        return json;
    }

    // source: JSONSerializationDemo
    // EFFECTS: returns things in this to-do list as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : toDoList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    public ArrayList<Item> getItems() {
        return toDoList;
    }
}
