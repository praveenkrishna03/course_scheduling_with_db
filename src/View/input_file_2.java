/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author gandh
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class input_file_2 extends JPanel {

    public JTextField courseField;
    public JTextField capacityField;
    public JTextField preferencesField;

    public JButton addButton;
    public JButton goback;

    public input_file_2() {

        JLabel courseLabel = new JLabel("Course: ");
        JLabel capacityLabel = new JLabel("Capacity: ");
        JLabel preferencesLabel = new JLabel("Preferences: ");

        courseField = new JTextField(25);
        capacityField = new JTextField(25);
        preferencesField = new JTextField(25);

        addButton = new JButton("Add Input");
        addButton.setPreferredSize(new Dimension(278, 40));
        goback = new JButton("Go back");
        goback.setPreferredSize(new Dimension(278, 40));

        // space between fields
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        // space between buttons
        Insets buttonInset = new Insets(20,0,0,0);

        // uses Grid Bag Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        add(courseLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        add(courseField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        add(capacityLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;

        add(capacityField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        add(preferencesLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;

        add(preferencesField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = buttonInset;

        add(addButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = buttonInset;

        add(goback, gridBagConstraints);
    }

    // getters
    public String getCourse() {
        return courseField.getText();
    }

    public String getCapacity() {
        return capacityField.getText();
    }

    public String getPreferences() {
        return preferencesField.getText();
    }

    public void submitInput(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void goback(ActionListener actionListener) {
        goback.addActionListener(actionListener);
    }

    // reset fields
    public void reset(boolean bln) {
        if(bln) {
            courseField.setText("");
            capacityField.setText("");
            preferencesField.setText("");
        }
    }

}
