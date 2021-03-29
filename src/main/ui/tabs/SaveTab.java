package ui.tabs;

/*
 * Represents tab to save to-do list
 */


import ui.GUI;

import javax.swing.*;
import java.awt.*;

public class SaveTab extends Tab {
    private JLabel saveLabel;

    public SaveTab(GUI controller) {
        super(controller);
        saveLabel = new JLabel("");

        this.setLayout(null);

        Dimension size = saveLabel.getPreferredSize();
        saveLabel.setBounds(120, 150, size.width, size.height);

        saveAndQuitButton();

        this.setBackground(Color.pink);
    }

    // MODIFIES: this
    // EFFECTS: creates and places save and quit button appropriately and implements it
    private void saveAndQuitButton() {
        JButton btn = new JButton("Save and Quit");

        btn.setBounds(130, 150, 150, 30);

        this.add(btn);

        this.add(saveLabel);

        btn.addActionListener(e -> saveToDoList());

        setLayout(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: saves to-do list
    private void saveToDoList() {
        getController().getToDoListApp().saveToDoList();
        JLabel saved = new JLabel("To-Do List saved!");
        saved.setBounds(130, 120, 200, 30);
        add(saved);

        revalidate();
        repaint();
    }
}
