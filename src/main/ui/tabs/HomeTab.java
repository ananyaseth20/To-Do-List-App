package ui.tabs;

/*
 * Represents home tab
 */


import ui.GUI;

import javax.swing.*;
import java.awt.*;

// credit: SmartHomeUI
// image: birthdaywishes.expert
public class HomeTab extends Tab {
    private JLabel homeLabel;

    public HomeTab(GUI controller) {
        super(controller);
        homeLabel = new JLabel("Welcome to your to-do list! Good luck!!");

        this.setLayout(null);

        Dimension size = homeLabel.getPreferredSize();
        homeLabel.setBounds(0, 10, size.width, size.height);

        this.add(homeLabel);

        this.setBackground(Color.pink);

        ImageIcon image = new ImageIcon("./data/good luck cat.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);

//        size = label.getPreferredSize();
        label.setBounds(0, 0, 600, 400);

        this.add(label);

    }
}
