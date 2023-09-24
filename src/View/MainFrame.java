/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author gandh
 */

import Controller.UserController;
import Model.CourseDB;
import Model.InputDB;
import Model.InputFileReader;
import Model.RoomDB;
import Model.TimingDB;
import Model.InputFileReader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    // Card layout for switching view
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private UserController userController;
    public String[][] data;

    

    public MainFrame() {
        super("Java Swing MVC");
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        Home home = new Home();
        
        
        
        input_file_1 input_file_1 = new input_file_1();
        input_file_2 input_file_2 = new input_file_2();
        //output_file_1 outputfile1=new output_file_1(null, null, null);
        
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        // Set the content pane of the JFrame to the scroll pane
        setContentPane(scrollPane);

        // Initialize the controller and add views to the content panel
        
        contentPanel.add(home, "home");
        contentPanel.add(input_file_1, "InputFile1");
        contentPanel.add(input_file_2, "InputFile2");
        ;
        userController = new UserController(home,input_file_2); 
        

        // Configure the initial view
        cardLayout.show(contentPanel, "home");

        home.setInputFile1ButtonListener(e -> cardLayout.show(contentPanel, "InputFile1"));
        home.setInputFile2ButtonListener(e->cardLayout.show(contentPanel, "InputFile2"));
        input_file_2.goback(e->cardLayout.show(contentPanel, "home"));
        
        home.setOutputFile1ButtonListener(e->{

            UserController control=new UserController(home, input_file_2);
            cardLayout.show(contentPanel,"OutputFile1");
            //String[][] datas= control.getTimetable();
            //output_file_1 output_file_1=new output_file_1(datas);

            //contentPanel.add(output_file_1,"OutputFile1");

            
            //output_file_1 output_file_1=new output_file_1(datas);
            //contentPanel.add(output_file_1,"OutputFile1");
            
            
            cardLayout.show(contentPanel,"OutputFile1");
        });
        home.setValidateButtonListener(e -> {
            // Call the generateDataFromInputFiles method to generate data
            Object[] data = userController.generateDataFromInputFiles();
            // Display the generated data in a table or perform further actions
            // For now, let's just print it
            JOptionPane.showMessageDialog(null, "Validation Done", "Done", JOptionPane.INFORMATION_MESSAGE);
        });


        home.setGenerateFile1ButtonListener(e->{

            InputFileReader inputFileReader=new InputFileReader();
            List<String> inp_1_roomsList=inputFileReader.getInp_1_rooms();
            List<String> inp_1_timingList=inputFileReader.getInp_1_timing();
            
            UserController control=new UserController(home, input_file_2);
            String[][] datas= control.Schedule();
            //System.out.println(datas);
            //System.out.println(data[0].length);
            output_file_1 output_file_1=new output_file_1(datas,inp_1_roomsList,inp_1_timingList);
            //output_file_1.setbackButtonListener(e -> cardLayout.show(contentPanel, "home"));
            

            contentPanel.add(output_file_1,"OutputFile1");

            JOptionPane.showMessageDialog(null, "Timetable generation Done", "Done", JOptionPane.INFORMATION_MESSAGE);
            
        });

        

        input_file_1.setsubmitButtonListener(e -> {
            // Collect room details from the input_file_1 class and send them to the controller
            List<RoomDB> roomList = new ArrayList<>();
            List<JTextField> roomFields = input_file_1.getRoomFields();
            List<JTextField> capacityFields = input_file_1.getCapacityFields();
            
            for (int i = 0; i < roomFields.size(); i++) {
                String roomText = roomFields.get(i).getText();
                String capacityText = capacityFields.get(i).getText();
                roomList.add(new RoomDB(roomText, capacityText));
            }
            
            // Notify the controller to save the room details
            userController.saveRoomDetails(roomList);
        });

        input_file_1.setsubmitButton2Listener(e->{
            List<CourseDB> courseList = new ArrayList<>();
            List<JTextField> courseFields = input_file_1.getCourseFields();

            for (int i = 0; i < courseFields.size(); i++) {
                String courseText = courseFields.get(i).getText();
                courseList.add(new CourseDB(courseText));
            }

            userController.saveCourseDetails(courseList);

        });
        
        input_file_1.setsubmitButton3Listener(e->{
            List<TimingDB> timingList = new ArrayList<>();
            List<JTextField> timingFields = input_file_1.getTimingFields();

            for (int i = 0; i < timingFields.size(); i++) {
                String timingText = timingFields.get(i).getText();
                timingList.add(new TimingDB(timingText));
            }

            userController.saveTimingDetails(timingList);
            cardLayout.show(contentPanel, "home");

        });
        /* 
        List<InputDB> inputList = new ArrayList<>();
    List<JTextField> courseFields = input_file_2.getCourseFields();
    List<JTextField> capacityFields = input_file_2.getCapacityFields();
    List<JTextField> preferenceFields = input_file_2.getPreferenceFields();
            
    for (int i = 0; i < courseFields.size(); i++) {
        String courseText = courseFields.get(i).getText();
        String capacityText = capacityFields.get(i).getText();
        String preferenceText = preferenceFields.get(i).getText();

        inputList.add(new InputDB(courseText, capacityText, preferenceText));
    }
            
    // Notify the controller to save the input details
    userController.saveInputDetails(inputList);
    */
    /* 
    input_file_2.submitInput(e -> {
        String course = input_file_2.getCourse().trim();
        String capacity = input_file_2.getCapacity().trim();
        String preference = input_file_2.getPreferences().trim();
    
        // Check if any of the fields are empty
        if (course.isEmpty() || capacity.isEmpty() || preference.isEmpty()) {
            JOptionPane.showMessageDialog(input_file_2, "All fields (Course, Capacity, Preference) are required.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            // Create an InputDB object with the collected data
            InputDB input = new InputDB(course, capacity, preference);
    
            // Notify the controller to save the input details
            userController.saveInputDetails(input);
    
            // Clear the input fields after successfully saving
            input_file_2.reset(true);
        }
    });*/
    
    
    
    
    
    
    


        // Set icon and frame properties
        ImageIcon imageIcon = new ImageIcon("src/assets/appicon.png");
        setIconImage(imageIcon.getImage());
        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}


/* 
public class MainFrame extends JFrame {
    
    public MainFrame() {
        

        // Initialize the scroll pane and set its properties
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        // Set the content pane of the JFrame to the scroll pane
        setContentPane(scrollPane);

        // Initialize the controller and add views to the content panel
        new UserController(home, userDetails);
        contentPanel.add(home, "home");
        contentPanel.add(userDetails, "user details");
        contentPanel.add(input_file_1, "InputFile1");

        // Configure the initial view
        cardLayout.show(contentPanel, "home");

        // Set icon and frame properties
        ImageIcon imageIcon = new ImageIcon("src/assets/appicon.png");
        setIconImage(imageIcon.getImage());
        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
} */
