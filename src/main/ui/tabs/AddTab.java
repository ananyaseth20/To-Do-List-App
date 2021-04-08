package ui.tabs;

/*
 * Represents tab to add item
 */


import model.Categories;
import model.Item;
import ui.GUI;
import ui.ToDoListApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class AddTab extends Tab implements ActionListener {
    private JLabel addLabel;
    private JTextField field;
    private JLabel label;

    private ToDoListApp toDoListApp;

    private ViewTab viewTab;
    private ViewUrgentTab viewUrgentTab;

    // credit: LabelChanger
    public AddTab(GUI controller) {
        super(controller);
        toDoListApp = getController().getToDoListApp();

        setUpPanel();
        setUpTextFieldAndButton();


    }

    // MODIFIES: this
    // EFFECTS: sets up text field and button in add panel
    private void setUpTextFieldAndButton() {
        JButton btn = new JButton("Add Item");
        btn.setActionCommand("myButton");
        label = new JLabel(" ");
        btn.addActionListener(this);
        field = new JTextField(30);

        JLabel youAdded = new JLabel("You added:");

        btn.setBounds(140, 200, 90, 30);
        field.setBounds(10, 200, 125, 30);
        youAdded.setBounds(235, 200, 80, 30);
        label.setBounds(315, 195, 125, 40);

        this.add(field);
        this.add(btn);
        this.add(youAdded);
        this.add(label);
    }

    // MODIFIES: this
    // EFFECTS: sets up panel with appropriate labels
    private void setUpPanel() {
        addLabel = new JLabel("Add Item to List");
        JLabel jlabel = new JLabel("Enter in format: Name of task, Days before it is due, Priority (H/M/L)");

        this.setLayout(null);

        Dimension size = addLabel.getPreferredSize();
        addLabel.setBounds(150, 20, size.width, size.height);
        Dimension size1 = jlabel.getPreferredSize();
        jlabel.setBounds(7, 50, size1.width, size1.height);

        this.add(addLabel);
        this.add(jlabel);

        this.setBackground(Color.pink);
    }

    // credit: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // credit: https://www.youtube.com/watch?v=qZC5gtOw3DU
    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            label.setText(field.getText());
            playSound("./data/Ding Sound Effect.wav");

            addFunctionality();
        }
    }

    // MODIFIES: this
    // EFFECTS: makes a new item with the information entered and inserts it to the to-do list
    @Override
    public void addFunctionality() {
        String entered = field.getText();
        int name = entered.indexOf(',');
        int days = entered.lastIndexOf(',');
        Categories category;
        char categoryType = Character.toUpperCase(entered.charAt(days + 2));
        if (categoryType == 'H') {
            category = Categories.HIGHPRIORITY;
        } else if (categoryType == 'M') {

            category = Categories.MIDPRIORITY;
        } else {
            category = Categories.LOWPRIORITY;
        }

        Item item = new Item(entered.substring(0, name), entered.substring(name + 2, days), category);
        toDoListApp.getToDoList().insertGUI(item);

        revalidate();
        repaint();

        viewTab = (ViewTab) getController().getView();
        viewTab.addFunctionality();
        viewUrgentTab = (ViewUrgentTab) getController().getViewUrgent();
        viewUrgentTab.addFunctionality();
    }
}
