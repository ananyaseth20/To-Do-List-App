package model;

import model.Categories;
import model.Item;

import java.util.*;

public class ToDoList {
    // delete or rename this class!
    public final ArrayList<Item> toDoList;

    public ToDoList() {
        toDoList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: creates item and adds it to list
    public void insert(Item item) {
        toDoList.add(item);
    }

    // REQUIRES: the list has at least removeIndex items
    // MODIFIES: this
    // EFFECTS: removes item at given index in array
    public void remove(int removeIndex) {
        int count = 1;
        if (toDoList.size() < removeIndex) {
            System.out.println("Invalid input");
        } else {
            for (Item i : toDoList) {
                if (count == removeIndex) {
                    toDoList.remove(i);
                    System.out.println("Removed successfully!");
                    break;
                } else {
                    count++;
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

    public int getSize() {
        return toDoList.size();
    }

    public Item getItemAtIndex(int i) {
        return toDoList.get(i - 1);
    }

    public boolean contains(Item item) {
        return toDoList.contains(item);
    }

    public int returnIndex(Item item) {
        int count = 1;
        for (Item i: toDoList) {
            if (i.equals(item)) {
                return count;
            } else {
                count++;
            }
        }
        System.out.println("Item not present");
        return -1;
    }
}
