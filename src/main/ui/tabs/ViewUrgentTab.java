package ui.tabs;

/*
 * Represents tab to view urgent items
 */


import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewUrgentTab extends Tab {
    private JLabel viewUrgentLabel;
    ArrayList<String> itemNames;
    ArrayList<JLabel> itemsListOfLabels;

    public ViewUrgentTab(GUI controller) {
        super(controller);

        viewUrgentLabel = new JLabel("View Tasks Due Today");

        this.setLayout(null);

        Dimension size = viewUrgentLabel.getPreferredSize();
        viewUrgentLabel.setBounds(150, 20, size.width, size.height);

        this.add(viewUrgentLabel);

        this.setBackground(Color.pink);

        addFunctionality();
    }

    // MODIFIES: this
    // EFFECTS: displays to-do list
    @Override
    public void addFunctionality() {

        removeAll();

        itemNames = getController().getToDoListApp().displayUrgentItems();
        itemsListOfLabels = new ArrayList<>();

        //itemsListOfLabels.toArray();

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
