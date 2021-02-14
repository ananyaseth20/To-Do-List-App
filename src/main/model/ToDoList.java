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
        // remove item from list
        //toDoList.remove(item);
        int count = 1;
        if (toDoList.size() < removeIndex || toDoList.size() == 0) {
            System.out.println("Invalid input");
        } else {
            for (Item i : toDoList) {
                if (count == removeIndex) {
                    toDoList.remove(i);
                    break;
                }
                count++;
            }
            System.out.println("Removed successfully!");
        }
    }

    public void sortByDueDate() {
        // sort by date
        //toDoList.sort();
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
