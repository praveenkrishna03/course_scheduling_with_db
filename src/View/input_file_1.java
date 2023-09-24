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

import Controller.UserController;
import Model.RoomDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class input_file_1 extends JPanel {

    private JButton addButton;
    private JButton submitButton;
    private JButton addButton2;
    private JButton submitButton2;
    private JButton addButton3;
    private JButton submitButton3;

    private JPanel panel;
    private int rooms;
    private int courses;
    private int timings;
    private List<JTextField> roomFields;
    private List<JTextField> capacityFields;
    private List<JTextField> courseFields;
    private List<JTextField> timingFields;
    private UserController userController;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JPanel page1 = new JPanel();
    private JPanel page2=new JPanel();
    private JPanel page3=new JPanel();
    public List<JTextField> getRoomFields() {
        return roomFields;
    }

    public List<JTextField> getCapacityFields() {
        return capacityFields;
    }

    public List<JTextField> getCourseFields() {
        return courseFields;
    }

    public List<JTextField> getTimingFields() {
        return timingFields;
    }


    public input_file_1() {
        

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        
        addButton = new JButton("+");
        submitButton = new JButton("Next");
        panel = new JPanel();
        JLabel label1 = new JLabel("Rooms and Capacities");

        roomFields = new ArrayList<>();
        capacityFields = new ArrayList<>();
        courseFields = new ArrayList<>();
        timingFields=new ArrayList<>();
        int buttonSpacing = 20;
        rooms = 1;
        courses=1;
        timings=1;

        

        Insets buttonInset = new Insets(0, 0, 20, 0);

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    addElement(rooms);
                    rooms++;
            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Iterate through roomFields and capacityFields to retrieve the text
                cardLayout.show(contentPanel, "page2");
            }
        });


        page1.setLayout(new BoxLayout(page1, BoxLayout.Y_AXIS));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(page1, gridBagConstraints);
        gridBagConstraints.insets = buttonInset;
        page1.add(label1);
        gridBagConstraints.insets = buttonInset;
        page1.add(addButton);
        contentPanel.add(page1, "page1");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(contentPanel, gridBagConstraints);
        
        

        //JPanel page2 = new JPanel();
        JLabel label2 = new JLabel("Courses");
        addButton2=new JButton("+");
        submitButton2 = new JButton("Next");

        

        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    addElement2(courses);
                    courses++;
            }
        });

        submitButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Iterate through roomFields and capacityFields to retrieve the text
                cardLayout.show(contentPanel, "page3");
            }
        });




        page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));

        gridBagConstraints.insets = buttonInset;
        

        page2.add(label2);
        page2.add(addButton2);
        page2.add(submitButton2);
        contentPanel.add(page2, "page2");


        // Add contentPanel to input_file_1

        JLabel label3 = new JLabel("Timing");
        addButton3=new JButton("+");
        submitButton3 = new JButton("Submit");

        

        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    addElement3(timings);
                    timings++;
            }
        });

        submitButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Iterate through roomFields and capacityFields to retrieve the text
                //cardLayout.show(contentPanel, "page2");
            }
        });




        page3.setLayout(new BoxLayout(page3, BoxLayout.Y_AXIS));

        gridBagConstraints.insets = buttonInset;
        

        page3.add(label3);
        page3.add(addButton3);
        page3.add(submitButton3);
        contentPanel.add(page3, "page3");


    }

    private void addElement(int rooms) {
        JPanel elementPanel = new JPanel();
        JLabel roomLabel = new JLabel("Room " + rooms + ":");
        JTextField roomField = new JTextField(30);

        JLabel capacityLabel = new JLabel("Capacity of Room " + rooms + ":");
        JTextField capacityField = new JTextField(30);

        elementPanel.add(roomLabel);
        elementPanel.add(roomField);
        elementPanel.add(capacityLabel);
        elementPanel.add(capacityField);

        page1.add(elementPanel); // Add to the page1 JPanel
        page1.add(Box.createVerticalStrut(10));
        page1.add(submitButton);

        roomFields.add(roomField);
        capacityFields.add(capacityField);
        revalidate();
        repaint();
    }

    private void addElement2(int courses) {
        JPanel elementPanel2 = new JPanel();
        JLabel courseLabel = new JLabel("Course " + courses + ":");
        JTextField courseField = new JTextField(30);


        elementPanel2.add(courseLabel);
        elementPanel2.add(courseField);
        page2.add(elementPanel2); // Add to the page1 JPanel
        page2.add(Box.createVerticalStrut(10));
        page2.add(submitButton2);

        courseFields.add(courseField);
        revalidate();
        repaint();
    }

    private void addElement3(int timings) {
        JPanel elementPanel3 = new JPanel();
        JLabel timingLabel = new JLabel("Timing " + timings + ":");
        JTextField timingField = new JTextField(30);


        elementPanel3.add(timingLabel);
        elementPanel3.add(timingField);
        page3.add(elementPanel3); // Add to the page1 JPanel
        page3.add(Box.createVerticalStrut(10));
        page3.add(submitButton3);

        timingFields.add(timingField);
        revalidate();
        repaint();
    }

    

    public void setsubmitButtonListener(ActionListener listener) {
        // Assuming you have a JButton named submitButton
        submitButton.addActionListener(listener);
    }

    public void setsubmitButton2Listener(ActionListener listener) {
        // Assuming you have a JButton named submitButton
        submitButton2.addActionListener(listener);
    }

    public void setsubmitButton3Listener(ActionListener listener) {
        // Assuming you have a JButton named submitButton
        submitButton3.addActionListener(listener);
    }
    

    

    

    
}

