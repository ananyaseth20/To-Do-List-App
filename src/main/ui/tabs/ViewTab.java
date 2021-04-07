package ui.tabs;

/*
 * Represents tab to view items
 */


import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewTab extends Tab {
    private JLabel viewLabel;

    ArrayList<String> itemNames;
    ArrayList<JLabel> itemsListOfLabels;

    public ViewTab(GUI controller) throws NullPointerException {
        super(controller);
        viewLabel = new JLabel("View To-Do List");

        this.setLayout(null);

        Dimension size = viewLabel.getPreferredSize();
        viewLabel.setBounds(150, 20, size.width, size.height);

        this.add(viewLabel);

        this.setBackground(Color.pink);

        addFunctionality();
    }

    // MODIFIES: this
    // EFFECTS: displays to-do list
    @Override
    public void addFunctionality() {
        removeAll();
        itemNames = getController().getToDoListApp().displayItems();
        itemsListOfLabels = new ArrayList<>();

        setLayout(null);

        for (int i = 0; i < itemNames.size(); i++) {
            JLabel label = new JLabel(itemNames.get(i));
            itemsListOfLabels.add(label);

            Dimension size = label.getPreferredSize();
            label.setBounds(5, i * 25, size.width, size.height);

            add(label);
        }

        revalidate();
        repaint();
    }
}
