package ui;

/*
 * Code for Graphical Interface\
 * credit: SmartHomeUI
 */

import ui.tabs.*;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private ToDoListApp toDoListApp;

    public static final int WIDTH = 650;
    public static final int HEIGHT = 400;

    private JTabbedPane sidebar;
    private JPanel home;
    private JPanel addItem;
    private JPanel viewUrgent;
    private JPanel view;
    private JPanel save;
    private JPanel load;

    public GUI() {
        super("To-Do List");

        toDoListApp = new ToDoListApp();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        addTabs();
        add(sidebar);

        setSize(WIDTH, HEIGHT);
        add(sidebar, BorderLayout.WEST);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds tabs to sidebar
    public void addTabs() {
        home = new HomeTab(this);
        addItem = new AddTab(this);
        viewUrgent = new ViewUrgentTab(this);
        view = new ViewTab(this);//new ViewAllTab(this);
        save = new SaveTab(this);
        load = new LoadTab(this);

        sidebar.add(home, 0);
        sidebar.setTitleAt(0, "Home");
        sidebar.add(load, 1);
        sidebar.setTitleAt(1, "Load Items");
        sidebar.add(view, 2);
        sidebar.setTitleAt(2, "View To-Do List");
        sidebar.add(viewUrgent, 3);
        sidebar.setTitleAt(3, "View Urgent Tasks");
        sidebar.add(addItem, 4);
        sidebar.setTitleAt(4, "Add Item");
        sidebar.add(save, 5);
        sidebar.setTitleAt(5, "Save and Quit");
    }

    // getters:
    public ToDoListApp getToDoListApp() {
        return toDoListApp;
    }

    public JTabbedPane getSidebar() {
        return sidebar;
    }

    public JPanel getHome() {
        return home;
    }

    public JPanel getAddItem() {
        return addItem;
    }

    public JPanel getViewUrgent() {
        return viewUrgent;
    }

    public JPanel getView() {
        return view;
    }

    public JPanel getSave() {
        return save;
    }

    public JPanel getLoad() {
        return load;
    }
}
