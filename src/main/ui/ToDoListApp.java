package ui;

import model.Categories;
import model.Item;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/*
 * To-do list application
 */

public class ToDoListApp {

    private static final String JSON_STORE = "./data/toDoList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private ToDoList toDoList;
    private Scanner scanner;

    private GUI gui;

    // EFFECTS: runs the to-do list application
    public ToDoListApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        toDoList = new ToDoList("To-Do List");
        // runToDoList();
        //gui = new GUI();
    }

    // source: TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runToDoList() {
        boolean keepGoing = true;
        String command;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = scanner.next();
            command = command.toLowerCase();

            if (command.equals("6")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes lists
    private void initialize() {
        toDoList = new ToDoList("To-Do List");
        scanner = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user to choose from
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> add item to to-do list");
        System.out.println("\t2 -> remove item from to-do list");
        System.out.println("\t3 -> view to-do list");
        System.out.println("\t4 -> save work room to file");
        System.out.println("\t5 -> load work room from file");
        System.out.println("\t6 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            insertItem();
        } else if (command.equals("2")) {
            removeItem();
        } else if (command.equals("3")) {
            viewList();
        } else if (command.equals("4")) {
            saveToDoList();
        } else if (command.equals("5")) {
            loadToDoList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // REQUIRES: daysBeforeDue >= 0
    // MODIFIES: this
    // EFFECTS: accepts input from user and inserts given item into appropriate list
    private void insertItem() {
        System.out.println("What is the priority level of the task: \n\tH -> high \n\tM -> medium \n\tL -> low");
        char priorityLevel = scanner.next().toUpperCase().charAt(0);

        priorityLevel = checkPriorityLevel(priorityLevel);
        Categories category;
        if (priorityLevel == 'H') {
            category = Categories.HIGHPRIORITY;
        } else if (priorityLevel == 'M') {
            category = Categories.MIDPRIORITY;
        } else {
            category = Categories.LOWPRIORITY;
        }

        System.out.println("Enter the name of the task:");
        scanner.nextLine();
        String name = scanner.nextLine();

        String daysBeforeDue = acceptDaysBeforeDue();

        Item item = new Item(name, daysBeforeDue, category);
        toDoList.insert(item);
        System.out.println("Added successfully!");

        System.out.println("Updated to-do list:");
        viewList();
    }

    private char checkPriorityLevel(char priorityLevel) {
        while (priorityLevel != 'H' && priorityLevel != 'M' && priorityLevel != 'L') {
            System.out.println("Invalid input, enter appropriate input:");
            priorityLevel = scanner.next().toUpperCase().charAt(0);
        }
        return priorityLevel;
    }

    // MODIFIES: this
    // EFFECTS: accepts input from user and removes given item from appropriate list
    private void removeItem() {
        viewList();
        System.out.println("Enter the priority of the item to remove: \n\tH -> high \n\tM -> medium \n\tL -> low");
        char c = scanner.next().toUpperCase().charAt(0);
        System.out.println("Enter the number of the item you want to remove: ");
        System.out.println("Note: If you enter a higher number, items from the next categories will be deleted");
        int removeIndex = scanner.nextInt();

        int h = toDoList.lastIndexOfCategory(Categories.HIGHPRIORITY);
        int m = toDoList.lastIndexOfCategory(Categories.MIDPRIORITY);

        if (c == 'H' && (toDoList.getItemAtIndex(1).getCategory() != Categories.HIGHPRIORITY || h + 1 > removeIndex)) {
            removeIndex = -1;
        } else if (c == 'M') {
            removeIndex = removeIndex + h + 1;
        } else if (c == 'L') {
            removeIndex = removeIndex + h + m + 1;
        }
        if (c == 'M' && h != 0 && toDoList.lastIndexOfCategory(Categories.MIDPRIORITY) == 0) {
            removeIndex = -1;
        } else if (c == 'L' && (h != 0 || m != 0) && toDoList.lastIndexOfCategory(Categories.LOWPRIORITY) == 0) {
            removeIndex = -1;
        }

        toDoList.remove(removeIndex);
        System.out.println("Updated to-do list:");
        viewList();
    }

    // EFFECTS: displays the to-do list to user
    private void viewList() {
        ToDoList highPriority = new ToDoList("high priority");
        ToDoList midPriority = new ToDoList("mid priority");
        ToDoList lowPriority = new ToDoList("high priority");

        for (int i = 0; i < toDoList.getSize(); i++) {
            if (toDoList.getItemAtIndex(i + 1).getCategory().equals(Categories.HIGHPRIORITY)) {
                highPriority.insert(toDoList.getItemAtIndex(i + 1));
            }

            if (toDoList.getItemAtIndex(i + 1).getCategory().equals(Categories.MIDPRIORITY)) {
                midPriority.insert(toDoList.getItemAtIndex(i + 1));
            }

            if (toDoList.getItemAtIndex(i + 1).getCategory().equals(Categories.LOWPRIORITY)) {
                lowPriority.insert(toDoList.getItemAtIndex(i + 1));
            }
        }

        System.out.println("HIGH PRIORITY: ");
        display(highPriority);
        System.out.println("MID PRIORITY: ");
        display(midPriority);
        System.out.println("LOW PRIORITY: ");
        display(lowPriority);
    }

    private String acceptDaysBeforeDue() {
        System.out.println("In how many days is it due?");
        int days = scanner.nextInt();

        if (days < 0) {
            System.out.println("Invalid input");
            runToDoList();
            System.exit(0);
        }

        String daysBeforeDue = String.valueOf(days) + " days";
        return daysBeforeDue;
    }

    // MODIFIES: this
    // EFFECTS: displays to-do list
    public void display(ToDoList toDoList) {
        int length = toDoList.getSize();

        if (length == 0) {
            System.out.println("You have completed all your tasks! Yay!!");
        }

        for (int i = 0; i < length; i++) {
            String name = toDoList.getItemAtIndex(i + 1).getTitle();
            String days = toDoList.getItemAtIndex(i + 1).getDaysBeforeDue();
            System.out.println((i + 1) + ". " + name + ": due in " + days);
        }
    }

    // EFFECTS: saves the to-do list to file
    public void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();

            System.out.println("Saved " + toDoList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads to-do list from file
    public void loadToDoList() {
        try {
            toDoList = jsonReader.read();
            System.out.println("Loaded " + toDoList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    // MODIFIES: this
    // EFFECTS: displays items in toDoList
    public ArrayList<String> displayItems() {
        ArrayList<String> itemNames = new ArrayList<>();

        if (toDoList.getItems().isEmpty()) {
            itemNames.add("No More Tasks! Yay!!");
        }

        int i = 1;
        for (Item item : toDoList.getItems()) {
            Categories c = item.getCategory();
            itemNames.add(i + ". " + item.getTitle() + " due in " + item.getDaysBeforeDue() + " days, " + c);
            i++;
        }

        return itemNames;
    }

    // MODIFIES: this
    // EFFECTS: displays items in toDoList that are due today
    public ArrayList<String> displayUrgentItems() {
        ArrayList<String> urgentItemNames = new ArrayList<>();

        if (toDoList.getItems().isEmpty()) {
            urgentItemNames.add("No More Tasks! Yay!!");
        }

        int i = 1;
        for (Item item : toDoList.getItems()) {
            if (Integer.parseInt(item.getDaysBeforeDue()) <= 1) {
                Categories c = item.getCategory();
                urgentItemNames.add(i + ". " + item.getTitle() + " due in " + item.getDaysBeforeDue() + " days, " + c);
                i++;
            }
        }

        return urgentItemNames;
    }
}
