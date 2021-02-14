package ui;

import model.Categories;
import model.Item;
import model.ToDoList;

import java.util.*;

public class ToDoListApp {
    private ToDoList highPriority;
    private ToDoList midPriority;
    private ToDoList lowPriority;
    private Scanner scanner;

    // EFFECTS: runs the to-do list application
    ToDoListApp() {
        runToDoList();
    }

    // TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runToDoList() {
        boolean keepGoing = true;
        String command = null;

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

    private void viewList() {
        System.out.println("High priority tasks:");
        display(highPriority);
        System.out.println("Medium priority tasks:");
        display(midPriority);
        System.out.println("Low priority tasks:");
        display(lowPriority);
    }

    // MODIFIES: this
    // EFFECTS: displays to-do list
    public void display(ToDoList toDoList) {
        int length = toDoList.getSize();
        if (length == 0) {
            System.out.println("You have completed all your tasks! Yay!!");
        }
        for (int i = 0; i < length; i++) {
            String name = toDoList.getItemAtIndex(i).getName();
            System.out.println((i + 1) + ". " + name + "- due in " + toDoList.getItemAtIndex(i).getDaysBeforeDue());
        }
    }

    // MODIFIES: this
    // EFFECTS: accepts input from user and removes given item from appropriate list
    private void removeItem() {
        System.out.println("Which category do you want to remove an item from:");
        System.out.println("\tH -> high priority \n\tM -> medium priority \n\tL -> low priority");
        char priorityLevel = scanner.next().toUpperCase().charAt(0);
        System.out.println("Enter the number of the item you want to remove:");
        int removeIndex = scanner.nextInt();
        if (priorityLevel == 'H') {
            highPriority.remove(removeIndex);
        } else if (priorityLevel == 'M') {
            midPriority.remove(removeIndex);
        } else {
            lowPriority.remove(removeIndex);
        }
    }

    // MODIFIES: this
    // EFFECTS: accepts input from user and inserts given item into appropriate list
    private void insertItem() {
        System.out.println("What is the priority level of the task:");
        System.out.println("\tH -> high priority \n\tM -> medium priority \n\tL -> low priority");
        char priorityLevel = scanner.next().toUpperCase().charAt(0);
        System.out.println("Enter the name of the task:");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("In how many days is it due?");
        String daysBeforeDue = scanner.next() + " day(s)";
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
            default:
                System.out.println("Invalid input");
        }
        System.out.println("Added successfully!");
    }

    // EFFECTS: displays menu of options to user to choose from
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> add item to to-do list");
        System.out.println("\t2 -> remove item from to-do list");
        System.out.println("\t3 -> view to-do list");
        System.out.println("\t4 -> quit");
    }
}
