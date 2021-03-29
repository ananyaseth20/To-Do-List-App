package ui.tabs;

/*
 * Represents abstract class tab
 * Credit: SmartHomeUI
 */


import model.Item;
import model.ToDoList;
import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Tab extends JPanel {


    // credit: SmartHome App
    private GUI controller;

    //REQUIRES: controller
    public Tab(GUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the gui controller for this tab
    public GUI getController() {
        return controller;
    }

}
