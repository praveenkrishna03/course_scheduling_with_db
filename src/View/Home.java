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

public class Home extends JPanel {

    public JButton input_file_1_Button;
    public JButton input_file_2_Button;
    public JButton validate_Button;
    public JButton generate_Button;
    public JButton output_1_Button;
    public JButton output_2_Button;
    public JButton output_3_Button;

    public Home() {
        
        input_file_1_Button=new JButton("Input_File_1");
        input_file_1_Button.setPreferredSize(new Dimension(278, 40));
        input_file_2_Button=new JButton("Input_File_2");
        input_file_2_Button.setPreferredSize(new Dimension(278, 40));
        generate_Button=new JButton("Generate");
        generate_Button.setPreferredSize(new Dimension(100, 40));
        validate_Button=new JButton("Validate");
        validate_Button.setPreferredSize(new Dimension(100, 40));
        output_1_Button=new JButton("Output_1");
        output_1_Button.setPreferredSize(new Dimension(278, 40));
        output_2_Button=new JButton("Output_2");
        output_2_Button.setPreferredSize(new Dimension(278, 40));
        output_3_Button=new JButton("Output_3");
        output_3_Button.setPreferredSize(new Dimension(278, 40));
        

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
        gridBagConstraints.insets = buttonInset;

        add(input_file_1_Button,gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = buttonInset;

        add(input_file_2_Button,gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = buttonInset;

        add(validate_Button,gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = buttonInset;


        add(generate_Button,gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = buttonInset;

        add(output_1_Button,gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = buttonInset;

        add(output_2_Button,gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = buttonInset;

        add(output_3_Button,gridBagConstraints);
    }


    public void setInputFile1ButtonListener(ActionListener listener) {
        // Assuming you have a JButton named inputFile1Button
        input_file_1_Button.addActionListener(listener);
    }

    public void setInputFile2ButtonListener(ActionListener listener) {
        // Assuming you have a JButton named inputFile1Button
        input_file_2_Button.addActionListener(listener);
    }

    public void setValidateButtonListener(ActionListener listener) {
        // Assuming you have a JButton named inputFile1Button
        validate_Button.addActionListener(listener);
    }

    public void setOutputFile1ButtonListener(ActionListener listener) {
        // Assuming you have a JButton named inputFile1Button
        output_1_Button.addActionListener(listener);
    }

    public void setGenerateFile1ButtonListener(ActionListener listener) {
        // Assuming you have a JButton named inputFile1Button
        generate_Button.addActionListener(listener);
    }
    
    

    /* 
    // getters
    public String getFirstname() {
        return firstnameField.getText();
    }

    public String getLastname() {
        return lastNameField.getText();
    }

    public void submitUsers(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void viewUsers(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
    }

    // reset fields
    public void reset(boolean bln) {
        if(bln) {
            firstnameField.setText("");
            lastNameField.setText("");
        }
    }
    */
}
