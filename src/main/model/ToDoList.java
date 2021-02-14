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
    // EFFECTS: creates item and adds it to list then sorts list
    public void insert(Item item) {
        toDoList.add(item);
        sortByDueDate();
    }

    // REQUIRES: the list has at least removeIndex items
    // MODIFIES: this
    // EFFECTS: removes item at given index in array and sorts list
    public void remove(int removeIndex) {
        sortByDueDate();
        int length = toDoList.size();
        if (length < removeIndex) {
            System.out.println("Invalid input");
        } else {
            /*for (Item i : toDoList) {
                if (count == removeIndex) {
                    toDoList.remove(i);
                    System.out.println("Removed successfully!");
                    break;
                } else {
                    count++;
                }
            }*/
            for (int i = 0; i < length; i++) {
                if ((i + 1) == removeIndex) {
                    toDoList.remove(getItemAtIndex(i + 1));
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

    // EFFECTS: returns size of to-do list
    public int getSize() {
        return toDoList.size();
    }

    // EFFECTS: returns item at given index in to-do list
    public Item getItemAtIndex(int i) {
        return toDoList.get(i - 1);
    }

    // EFFECTS: returns true if to-do list contains given item
    public boolean contains(Item item) {
        return toDoList.contains(item);
    }

    // EFFECTS: returns index of given item in to-do list, -1 if not present
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
