package ui.tabs;

/*
 * Represents tab to load previous input
 */


import ui.GUI;

import javax.swing.*;
import java.awt.*;

public class LoadTab extends Tab {
    private JLabel loadLabel;
    private JButton loadButton;
    private ViewTab viewTab;
    private ViewUrgentTab viewUrgentTab;

    public LoadTab(GUI controller) {
        super(controller);
        displayLoadButton();

        loadLabel = new JLabel("Click on 'Load' to load the previously saved to-do list");

        setLayout(null);

        Dimension size = loadLabel.getPreferredSize();
        loadLabel.setBounds(0, 10, size.width, size.height);
        add(loadLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds the load button to the panel and implements it
    public void displayLoadButton() {
        loadButton = new JButton("Load");
        loadButton.setBounds(200, 150, 100, 30);
        add(loadButton);

        this.setBackground(Color.pink);

        loadButton.addActionListener(e -> addFunctionality());

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: makes load button load previous data
    @Override
    public void addFunctionality() {
        getController().getToDoListApp().loadToDoList();
        update();

        JLabel loaded = new JLabel("Items loaded!");
        loaded.setBounds(130, 120, 200, 30);
        add(loaded);

        revalidate();
        repaint();
    }

    // MODIFIES: viewTab and viewUrgentTab
    // EFFECTS: updates view and view urgent panels
    private void update() {
        viewTab = (ViewTab) getController().getView();
        viewTab.addFunctionality();
        viewUrgentTab = (ViewUrgentTab) getController().getViewUrgent();
        viewUrgentTab.addFunctionality();
    }

}
