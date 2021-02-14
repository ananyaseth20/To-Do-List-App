package ui;

import model.Categories;
import model.Item;
import model.ToDoList;

import java.util.*;

/*
 * To-do list application
 */

public class ToDoListApp {
    private ToDoList highPriority;
    private ToDoList midPriority;
    private ToDoList lowPriority;
    private Scanner scanner;

    // EFFECTS: runs the to-do list application
    ToDoListApp() {
        runToDoList();
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

            if (command.equals("4")) {
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
        highPriority = new ToDoList();
        midPriority = new ToDoList();
        lowPriority = new ToDoList();
        scanner = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user to choose from
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> add item to to-do list");
        System.out.println("\t2 -> remove item from to-do list");
        System.out.println("\t3 -> view to-do list");
        System.out.println("\t4 -> quit");
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

        while (priorityLevel != 'H' && priorityLevel != 'M' && priorityLevel != 'L') {
            System.out.println("Invalid input, enter appropriate input:");
            priorityLevel = scanner.next().toUpperCase().charAt(0);
        }

        System.out.println("Enter the name of the task:");
        scanner.nextLine();
        String name = scanner.nextLine();

        String daysBeforeDue = acceptDaysBeforeDue();

        Item item;

        switch (priorityLevel) {
            case 'H': item = new Item(name, daysBeforeDue, Categories.HighPriority);
                highPriority.insert(item);
                break;
            case 'M': item = new Item(name, daysBeforeDue, Categories.MidPriority);
                midPriority.insert(item);
                break;
            case 'L': item = new Item(name, daysBeforeDue, Categories.LowPriority);
                lowPriority.insert(item);
                break;
            default: System.out.println("Invalid input");
        }
    }

    // MODIFIES: this
    // EFFECTS: accepts input from user and removes given item from appropriate list
    private void removeItem() {
        System.out.println("Which category do you want to remove an item from:");
        System.out.println("\tH -> high priority \n\tM -> medium priority \n\tL -> low priority");
        char priorityLevel = scanner.next().toUpperCase().charAt(0);

        while (priorityLevel != 'H' && priorityLevel != 'M' && priorityLevel != 'L') {
            System.out.println("Invalid input, enter appropriate input:");
            priorityLevel = scanner.next().toUpperCase().charAt(0);
        }

        System.out.println("Enter the number of the item you want to remove:");
        int removeIndex = scanner.nextInt();

        if (priorityLevel == 'H') {
            highPriority.remove(removeIndex);
        } else if (priorityLevel == 'M') {
            midPriority.remove(removeIndex);
        } else if (priorityLevel == 'L') {
            lowPriority.remove(removeIndex);
        } else {
            System.out.println("Invalid input");
        }
    }

    // EFFECTS: displays the 3 to-do lists to user
    private void viewList() {
        System.out.println("High priority tasks:");
        display(highPriority);

        System.out.println("Medium priority tasks:");
        display(midPriority);

        System.out.println("Low priority tasks:");
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

        String daysBeforeDue = String.valueOf(days) + " day(s)";
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
            String name = toDoList.getItemAtIndex(i + 1).getName();
            System.out.println((i + 1) + ". " + name + "- due in " + toDoList.getItemAtIndex(i + 1).getDaysBeforeDue());
        }
    }
}
