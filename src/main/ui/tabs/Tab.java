package ui.tabs;

/*
 * Represents abstract class tab
 * Credit: SmartHomeUI
 */

import ui.GUI;
import javax.swing.*;

public abstract class Tab extends JPanel {


    // credit: SmartHome App
    private GUI controller;

    //REQUIRES: controller
    public Tab(GUI controller) {
        this.controller = controller;
    }

    //EFFECTS: returns the gui controller for this tab
    public GUI getController() {
        return controller;
    }

    // EFFECTS: implements the functionality of that particular panel
    public abstract void addFunctionality();

}
