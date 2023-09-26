/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author gandh
 */

import Model.CourseDB;
import Model.Database;
import Model.InputDB;
import Model.InputFileReader;
import Model.RoomDB;
import Model.TimingDB;
import View.Home;
import View.input_file_2;
import courseschedulingooad_2.CourseSchedulingOoad_2;

import javax.swing.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class UserController {
    // database file
    private String FilePath_ip_1 = "data\\F1.8.txt";
    private String FilePath_ip_2 = "data\\F2.4.txt";
    private Database database;
    private Home home;
    private input_file_2 input_file_2;
     public Connection con;
    

    
    public Object[] generateDataFromInputFiles() {
        // Create an instance of InputFileReader
        InputFileReader inputFileReader = new InputFileReader();

        // Read and process data from inputfile1.txt and inputfile2.txt
        Object[] data = new Object[2];
        data[0] = inputFileReader.readInputFile(); // Call the method to read inputfile1.txt
        data[1] = inputFileReader.readInputFile(); // Call the method to read inputfile2.txt
        return data;
    }


    public UserController(Home home, input_file_2 input_file_2) {
        
        this.database = new Database();
        this.home = home;
        this.input_file_2 = input_file_2;


        

        // submit user
        this.input_file_2.submitInput(e -> {
            String course = input_file_2.getCourse().trim();
            String capacity = input_file_2.getCapacity().trim();
            String preferences = input_file_2.getPreferences().trim();
        
            // Simple validations
            if (course.isEmpty() || capacity.isEmpty() ) {
                JOptionPane.showMessageDialog(input_file_2, "these fields (Course, Capacity) are required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Create an InputDB object with the collected data
            InputDB input = new InputDB(course, capacity, preferences);
        
            // Add input to the database
            this.database.addInputCourse(input);
        
            // Save the input to the appropriate file
            File inputFile = new File(FilePath_ip_2);
            this.database.saveInput(inputFile, input);

        
            // Reset input fields
            input_file_2.reset(true);
        });
        
        }
        

        
        /* 
        this.home.submitUsers(e -> {
            String firstname = this.home.getFirstname().trim();
            String lastname = this.home.getLastname().trim();

            // simple validations
            if(firstname.isEmpty()) {
                JOptionPane.showMessageDialog(this.home, "First Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if(lastname.isEmpty()) {
                JOptionPane.showMessageDialog(this.home, "Last Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            this.database.addUser(new User(firstname, lastname));
            this.database.saveUser(new File(databaseFile));
            this.home.reset(true);
        });
        */

        // load users
        /* 
        this.home.viewUsers(e -> {
            this.userDetails.getUsers(this.database.loadUsers(new File(databaseFile)));
        });
        */
    
    // Inside UserController.java


    

    public void saveRoomDetails(List<RoomDB> roomDetailsList) {
        // Create room objects and add them to the database
        for (RoomDB roomDetails : roomDetailsList) {
            RoomDB room = new RoomDB(roomDetails.getRoom(), roomDetails.getCapacity());
            database.addRoom(room);
        }
        
        // Save the room details to the database file
        File databaseFile = new File(FilePath_ip_1);
        database.saveRoom(databaseFile);
    }

    public void saveCourseDetails(List<CourseDB> courseDetailsList) {
        // Create room objects and add them to the database
        for (CourseDB courseDetails : courseDetailsList) {
            CourseDB course = new CourseDB(courseDetails.getCourse());
            database.addCourse(course);
        }
        
        // Save the room details to the database file
        File databaseFile = new File(FilePath_ip_1);
        database.saveCourse(databaseFile);
    }

    public void saveTimingDetails(List<TimingDB> timingDetailsList) {
        // Create room objects and add them to the database
        for (TimingDB timingDetails : timingDetailsList) {
            TimingDB timing = new TimingDB(timingDetails.getTiming());
            database.addTiming(timing);
        }
        
        // Save the room details to the database file
        File databaseFile = new File(FilePath_ip_1);
        database.saveTiming(databaseFile);
    }

    
/* 
    public void saveInputDetails(List<InputDB> courseDetailsList,List<InputDB> capacityDetailsList,List<InputDB> preferencesDetailsList) {
        // Create room objects and add them to the database
    
        for (InputDB courseDetails : courseDetailsList) {
            //TimingDB timing = new TimingDB(timingDetails.getTiming());
            InputDB course= new InputDB(courseDetails.getCourse(),null,null);
            
            

            database.addInputCourse(course);
        }

         
        for (InputDB capacityDetails : capacityDetailsList) {
            //TimingDB timing = new TimingDB(timingDetails.getTiming());
            InputDB capacity= new InputDB(null,capacityDetails.getCapacity(),null);
            
            

            database.addInputCourse(capacity);
        }

        

        for (InputDB preferncesDetails : preferencesDetailsList) {
            //TimingDB timing = new TimingDB(timingDetails.getTiming());
            InputDB preferences= new InputDB(null, null,preferncesDetails.getPreferences());
            
            

            database.addInputCourse(preferences);
        }
        

        
        
        // Save the room details to the database file
        File databaseFile = new File(FilePath_ip_2);
        database.saveTiming(databaseFile);
        database.saveTiming(databaseFile);
        database.saveTiming(databaseFile);

    }
    */

    public void saveInputDetails(InputDB input) {
        // Add the input details to the database
        database.addInputCourse(input);
    
        // Save the input details to the database file for Input File 2
        File databaseFile = new File(FilePath_ip_2);
        database.saveInput(databaseFile, input);
    }
    
    public String[][] Schedule() {
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/course_scheduling","root","praveenkrishna2003");
    List<String> inp_1_roomsList=new ArrayList();
    List<Integer> inp_1_capList=new ArrayList();
    List<String> inp_1_coursesList=new ArrayList();
    List<String> inp_1_timingList=new ArrayList();
    List<String> inp_2_coursesList=new ArrayList();
    List<Integer> inp_2_capList=new ArrayList();
    List<List<String>> inp_2_prefList=new ArrayList();
    String delete_time_table="DELETE FROM time_table WHERE course IS NOT NULL;";
    
    PreparedStatement del_time_table = con.prepareStatement(delete_time_table);
    
    del_time_table.executeUpdate();
    
    String courses_sel="SELECT course_id FROM courses";
    String rooms_sel="SELECT room_no,capacity FROM rooms";
    String lecture_timings_sel="SELECT timings FROM lecture_timings";
    String course_details_sel="SELECT courseid, enrollment FROM course_details";
    String course_timepreference_sel="SELECT course_id, timings FROM course_timepreference";
    
    
    PreparedStatement courses_ps=con.prepareStatement(courses_sel);
    PreparedStatement rooms_ps=con.prepareStatement(rooms_sel);
    PreparedStatement lecture_timings_ps=con.prepareStatement(lecture_timings_sel);
    PreparedStatement course_details_ps=con.prepareStatement(course_details_sel);
    PreparedStatement course_timepreference_ps=con.prepareStatement(course_timepreference_sel);
    
    ResultSet courses_rs=courses_ps.executeQuery();
    
    while (courses_rs.next()) {
                String courseId = courses_rs.getString("course_id");
                inp_1_coursesList.add(courseId);
    }
    
    ResultSet rooms_rs=rooms_ps.executeQuery();
    
    while (rooms_rs.next()) {
                String room_no = rooms_rs.getString("room_no");
                Integer room_cap = rooms_rs.getInt("capacity");
                
                inp_1_roomsList.add(room_no);
                inp_1_capList.add(room_cap);
    }
    
    ResultSet lecture_timings_rs=lecture_timings_ps.executeQuery();
    
    while (lecture_timings_rs.next()) {
                String timing = lecture_timings_rs.getString("timings");
                inp_1_timingList.add(timing);
    }
    
    ResultSet course_details_rs=course_details_ps.executeQuery();
    
    while (course_details_rs.next()) {
                String course_id = course_details_rs.getString("courseid");
                Integer course_cap = course_details_rs.getInt("enrollment");
                
                inp_2_coursesList.add(course_id);
                inp_2_capList.add(course_cap);
    }
    
    ResultSet course_timepreference_rs=course_timepreference_ps.executeQuery();
    
    while(course_timepreference_rs.next()){
        String course_id = course_timepreference_rs.getString("course_id");
        String preference = course_timepreference_rs.getString("timings");
        
        int existingIndex = -1;
        
        for (int i = 0; i < inp_2_coursesList.size(); i++) {
        if (inp_2_coursesList.get(i).equals(course_id)) {
            existingIndex = i;
            break;
        }
    }
        if (existingIndex == -1) {
        inp_2_coursesList.add(course_id);

        List<String> preferences = new ArrayList<>();
        if (preference != null) {
            preferences.add(preference);
        }
        inp_2_prefList.add(preferences);
    } else {
        // Ensure inp_2_prefList has enough elements
        while (inp_2_prefList.size() <= existingIndex) {
            inp_2_prefList.add(new ArrayList<>());
        }

        List<String> preferences = inp_2_prefList.get(existingIndex);
        if (preference != null) {
            preferences.add(preference);
        }
    }
        
    }

    
    // Create lists for PG courses with and without preferences
    List<String> PG_with_no_pref_courseList = new ArrayList<>();
    List<Integer> PG_with_no_pref_capList = new ArrayList<>();
    List<String> PG_with_pref_courseList = new ArrayList<>();
    List<Integer> PG_with_pref_capList = new ArrayList<>();
    List<List<String>> PG_with_pref_prefList = new ArrayList<>();

    // Create lists for UG courses with and without preferences
    List<String> UG_with_no_pref_courseList = new ArrayList<>();
    List<Integer> UG_with_no_pref_capList = new ArrayList<>();
    List<String> UG_with_pref_courseList = new ArrayList<>();
    List<Integer> UG_with_pref_capList = new ArrayList<>();
    List<List<String>> UG_with_pref_prefList = new ArrayList<>();
    List<List<Integer>> PG_with_pref_list_num = new ArrayList<>();
    List<List<Integer>> UG_with_pref_list_num = new ArrayList<>();

    

    for (int i = 0; i < inp_2_coursesList.size(); i++) {
        String course = inp_2_coursesList.get(i);
        List<String> pref = inp_2_prefList.get(i);
        int cap = inp_2_capList.get(i);
        String preftest=pref.get(0);

        
        
    
        char thirdChar =  course.charAt(2);
    
        if ((thirdChar == '6'|| thirdChar == '7'||thirdChar == '8'||thirdChar == '9') && preftest.isEmpty()) {
            // PG with no preference
            PG_with_no_pref_courseList.add(course);
            PG_with_no_pref_capList.add(cap);
        } else if (thirdChar == '6'|| thirdChar == '7'||thirdChar == '8'||thirdChar == '9') {
            // PG with preference
            PG_with_pref_courseList.add(course);
            PG_with_pref_capList.add(cap);
            PG_with_pref_prefList.add(pref);
        }
    
        if ((thirdChar == '1'|| thirdChar == '2'||thirdChar == '3'||thirdChar == '4'||thirdChar == '5') && preftest.isEmpty()) {
            // UG with no preference
            UG_with_no_pref_courseList.add(course);
            UG_with_no_pref_capList.add(cap);
        } else if (thirdChar == '1'|| thirdChar == '2'||thirdChar == '3'||thirdChar == '4'||thirdChar == '5') {
            // UG with preference
            UG_with_pref_courseList.add(course);
            UG_with_pref_capList.add(cap);
            UG_with_pref_prefList.add(pref);
        }
    }
    
    // Move these sections outside the loop
    for (List<String> prefer : PG_with_pref_prefList) {
        List<Integer> prefIndices = new ArrayList<>();
        for (String timing : prefer) {
            int index = inp_1_timingList.indexOf(timing);
            if (index != -1) {
                prefIndices.add(index);
            }
        }
        PG_with_pref_list_num.add(prefIndices);
    }
    
    for (List<String> prefer : UG_with_pref_prefList) {
        List<Integer> prefIndices = new ArrayList<>();
        for (String timing : prefer) {
            int index = inp_1_timingList.indexOf(timing);
            if (index != -1) {
                prefIndices.add(index);
            }

            
        }
        UG_with_pref_list_num.add(prefIndices);
    }
    /* 
    for(String data:UG_with_no_pref_courseList){
            System.out.println(data);

        }

    */
    

    

    // Initialize your timetable matrix
        String[][] timetable = new String[inp_1_roomsList.size()][inp_1_timingList.size()];
        boolean[][] isSlotOccupied = new boolean[inp_1_roomsList.size()][inp_1_timingList.size()];
        /*int m=0;
        for(String data:inp_1_roomsList){
            System.out.println(data);
            timetable[m][0]=data;
            m++;

        }*/

        //List<String> datas=UG_with_pref_prefList.get(1);
        
        

        for (int i = 0; i < PG_with_pref_courseList.size(); i++) {
            String course = PG_with_pref_courseList.get(i);
            int cap = PG_with_pref_capList.get(i);
            List<Integer> prefIndices = PG_with_pref_list_num.get(i);
            boolean forpref=false;

            for (int j = 0; j < inp_1_capList.size(); j++) {
                int inpCap = inp_1_capList.get(j);

                if (cap <= inpCap) {
                    for (int k : prefIndices) {
                        if (!isSlotOccupied[j][k]) {
                            timetable[j][k] = course;
                                for(int l=0;l<inp_1_roomsList.size();l++){
                                    isSlotOccupied[l][k] = true;
                                }
                            forpref=true;
                            break;
                        }
                        if(forpref==true){
                            break;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < UG_with_pref_courseList.size(); i++) {
            String course = UG_with_pref_courseList.get(i);
            int cap = UG_with_pref_capList.get(i);
            List<Integer> prefIndices = UG_with_pref_list_num.get(i);
            boolean forpref=false;

            for (int j = 0; j < inp_1_capList.size(); j++) {
                int inpCap = inp_1_capList.get(j);

                if (cap <= inpCap) {
                    for (int k : prefIndices) {
                        if (!isSlotOccupied[j][k]) {
                            timetable[j][k] = course;
                            
                            for(int l=0;l<inp_1_roomsList.size();l++){
                                isSlotOccupied[l][k] = true;
                            }
                            forpref=true;
                            
                            break;
                        }
                        if(forpref==true){
                            break;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < PG_with_no_pref_courseList.size(); i++) {
            String course = PG_with_no_pref_courseList.get(i);
            int cap = PG_with_no_pref_capList.get(i);
            boolean fornopref=false;
        
            for (int j = 0; j < inp_1_capList.size(); j++) {
                int inpCap = inp_1_capList.get(j);
        
                if (cap <= inpCap) {
                    for (int k = 0; k < inp_1_timingList.size(); k++) {
                        if (!isSlotOccupied[j][k]) {
                            timetable[j][k] = course;
                            for(int l=0;l<inp_1_roomsList.size();l++){
                                isSlotOccupied[l][k] = true;
                            }
                            fornopref=true;
                            break;
                        }
                        if(fornopref==true){
                            break;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < UG_with_no_pref_courseList.size(); i++) {
            String course = UG_with_no_pref_courseList.get(i);
            int cap = UG_with_no_pref_capList.get(i);
            boolean fornopref=false;
            for (int j = 0; j < inp_1_capList.size(); j++) {
                int inpCap = inp_1_capList.get(j);
        
                if (cap <= inpCap) {
                    for (int k = 0; k < inp_1_timingList.size(); k++) {
                        if (k >= 0 && k < inp_1_timingList.size() && !isSlotOccupied[j][k]) {
                            timetable[j][k] = course;
                            for(int l=0;l<inp_1_roomsList.size();l++){
                                isSlotOccupied[l][k] = true;
                                fornopref=true;
                            }
                            break; 
                        }
                        if(fornopref==true){
                            break;
                        }
                    }
                }
            }
        }
        /* 
        for(int i=0;i<timetable.length;i++){
            for(int j=0;j<timetable[0].length;j++){
            System.out.println(isSlotOccupied[i][j]);
            }
            System.out.println("  ");

        }
        */


// Similar loops for UG_with_pref_courses, PG_with_no_pref_courses, UG_with_no_pref_courses
        /* 
        for (int i = 0; i < timetable.length; i++) {
            for (int j = 0; j < timetable[i].length; j++) {
                System.out.print(timetable[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
        */
        for(int i=0;i<inp_1_roomsList.size();i++){
            for(int j=i;j<inp_1_timingList.size();j++){
                if(timetable[i][j]!=null){
                    String course=timetable[i][j];
                    String room=inp_1_roomsList.get(i);
                    String timing=inp_1_timingList.get(j);
                    String insert="INSERT INTO time_table (course,room,time) VALUES ("+'"'+course+'"'+','+'"'+room+'"'+','+'"'+timing+'"'+");";
                    PreparedStatement in = con.prepareStatement(insert);
                    in.executeUpdate();
                }
            }
            
        }
        

    
        return timetable;
    }catch(ClassNotFoundException ex){
            Logger.getLogger(CourseSchedulingOoad_2.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(CourseSchedulingOoad_2.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    return null;
    }



}