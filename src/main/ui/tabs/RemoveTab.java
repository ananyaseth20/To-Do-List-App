//package ui.tabs;
//
//import ui.GUI;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//
//public class RemoveTab extends Tab implements ActionListener {
//    private JLabel removeLabel;
//    private JTextField field;
//    private JLabel label;
//
//    public RemoveTab(GUI controller) {
//        super(controller);
//        removeLabel = new JLabel("Remove Item from List");
//
//        this.setLayout(null);
//
//        Dimension size = removeLabel.getPreferredSize();
//        removeLabel.setBounds(150, 20, size.width, size.height);
//
//        this.add(removeLabel);
//
////        this.setBackground(Color.pink);
//
//        JButton btn = new JButton("Remove Number");
//        btn.setActionCommand("myButton");
//        label = new JLabel(" ");
//        btn.addActionListener(this);
//        field = new JTextField(30);
//
//        JLabel youRemoved = new JLabel("You removed:");
//
//        btn.setBounds(65, 200, 150, 30);
//        field.setBounds(10, 200, 50, 30);
//        youRemoved.setBounds(220, 200, 80, 30);
//        label.setBounds(305, 195, 125, 40);
//
//        this.add(field);
//        this.add(btn);
//        this.add(youRemoved);
//        this.add(label);
//    }
//
//    public void playSound(String soundName) {
//        try {
//           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//            clip.start();
//        } catch (Exception ex) {
//            System.out.println("Error with playing sound.");
//            ex.printStackTrace();
//        }
//    }
//
//    //This is the method that is called when the the JButton btn is clicked
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("myButton")) {
//            label.setText(field.getText());
//            playSound("./data/Ding Sound Effect.wav");
////            int index = list.getSelectedIndex();
////            listModel.remove(index);
//        }
//    }
//}
