/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gandh
 */

import courseschedulingooad_2.CourseSchedulingOoad_2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JOptionPane;

public class InputFileReader {
    public boolean haserror=false;
    public List<String> inp_1_rooms = new ArrayList<>();
    public List<Integer> inp_1_cap = new ArrayList<>();
    public List<String> inp_1_courses = new ArrayList<>();
    public List<String> inp_1_timing = new ArrayList<>();
    public List<String> inp_2_courses = new ArrayList<>();
    public List<Integer> inp_2_cap = new ArrayList<>();
    public List<List<String>> inp_2_pref = new ArrayList<>();
    public Connection con;

    private boolean isValidRoomNumber(String roomNumber) {
        try {
            int room = Integer.parseInt(roomNumber);
            return room >= 100 && room <= 900;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean validateInputCourse(String course) {
        return inp_1_rooms.contains(course);
    }
    
    

    public boolean isValidTiming(String timing) {
        return timing.matches("^(MWF\\d{1,2}(:\\d{2})?|TT\\d{1,2}(:\\d{2})?|TT\\d:\\d)$|^\\d{1,2}$");


        
        
    }

    public boolean isValidCapacity(String capacity) {
    try {
        int cap = Integer.parseInt(capacity);
        return cap >= 10 && cap <= 300; // Check if cap is within the range 10 to 300
    } catch (NumberFormatException e) {
        return false;
    }
}


    private boolean isValidCourse(String course) {
        return course.length() == 5 && course.charAt(0) == 'c' && course.charAt(1) == 's' && course.substring(2).matches("\\d{3}");
    }
    

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public InputFileReader() {
        

        readInputFile1("src\\data\\F1.8.txt");
        readInputFile2("src\\data\\F2.4.txt");
    }

    private void readInputFile1(String fileName) {
        
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean fileIsEmpty = true;
            boolean readingRooms = false;
            boolean readingCourses = false;
            boolean readingTimes = false;
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/course_scheduling","root","praveenkrishna2003");
            System.out.println("Connection establishment Success");
            String delete_rooms="DELETE FROM rooms WHERE room_no>0;";
            String delete_timings="DELETE FROM lecture_timings WHERE timings IS NOT NULL;";
            String delete_courses="DELETE FROM courses WHERE course_id IS NOT NULL;";
            String com="COMMIT";
            PreparedStatement del_rooms = con.prepareStatement(delete_rooms);
            PreparedStatement del_courses = con.prepareStatement(delete_courses);
            PreparedStatement del_timings = con.prepareStatement(delete_timings);
            PreparedStatement commit = con.prepareStatement(com);
            del_rooms.executeUpdate();
            del_courses.executeUpdate();
            del_timings.executeUpdate();
            commit.executeUpdate();
            int rooms_count=0;
            while ((line = br.readLine()) != null) {
                switch (line) {
                    case "rooms" -> {
                        readingRooms = true;
                        continue;
                    }
                    case "courses" -> {
                        readingRooms = false;
                        readingCourses = true;
                        continue;
                    }
                    case "timing" -> {
                        readingCourses = false;
                        readingTimes = true;
                        continue;
                    }
                    default -> {
                    }
                }
                
                if (readingRooms) {
    if (rooms_count < 20) {
        String[] parts = line.split(":");
        if (parts != null) {
            if (parts.length == 2) {
                String roomNumber = parts[0].trim();
                int capacity = Integer.valueOf(parts[1].trim());
                
                // Check for duplicates
                boolean isDuplicate = false;
                for (int i = 0; i < inp_1_rooms.size(); i++) {
                    if (inp_1_rooms.get(i).equals(roomNumber) || inp_1_cap.get(i) == capacity) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate && isValidRoomNumber(roomNumber) && isValidCapacity(String.valueOf(capacity))) {
                    rooms_count++;
                    inp_1_rooms.add(roomNumber);
                    inp_1_cap.add(capacity);
                    String insert_room = "INSERT INTO rooms (room_no,capacity) VALUES (" + roomNumber + "," + capacity + ");";
                    PreparedStatement in_rooms = con.prepareStatement(insert_room);
                    in_rooms.executeUpdate();
                    System.out.println(insert_room);
                } else {
                    if (isDuplicate) {
                        showErrorMessage("Duplicate room: " + roomNumber + " with capacity: " + capacity);
                    } else if (!isValidRoomNumber(roomNumber)) {
                        showErrorMessage("Invalid room number: " + roomNumber);
                    } else if (!isValidCapacity(String.valueOf(capacity))) {
                        showErrorMessage("Invalid capacity: " + capacity);
                    }
                    // Exit the method on validation failure
                }
            }
        } else {
            showErrorMessage("Rooms list Empty");
        }
    } else {
        showErrorMessage("Too many rooms. Maximum allowed is 20.");
    }
}
else if (readingCourses) {
    if (rooms_count == 0) {
        showErrorMessage("Rooms list Empty");
    }
    
    String[] courses = line.split(",");
    if (courses.length != 0) {
        if (courses.length <= 30) {
            for (String course : courses) {
                String trimmedCourse = course.trim();
                boolean isDuplicate = false;
                for (String addedCourse : inp_1_courses) {
                    if (trimmedCourse.equals(addedCourse)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate && isValidCourse(trimmedCourse)) {
                    inp_1_courses.add(trimmedCourse);
                    String insert_course = "INSERT INTO courses (course_id) VALUES (" + '"' + trimmedCourse + '"' + ");";
                    PreparedStatement in_course = con.prepareStatement(insert_course);
                    System.out.println(insert_course);
                    in_course.executeUpdate();
                    System.out.println(in_course);
                } else {
                    if (isDuplicate) {
                        showErrorMessage("Duplicate course: " + trimmedCourse);
                    } else {
                        showErrorMessage("Invalid course: " + trimmedCourse);
                    }
                    // Exit the method on validation failure
                }
            }
        } else {
            showErrorMessage("Too many courses. Maximum allowed is 30.");
            // Exit the method on validation failure
        }
    } else {
        showErrorMessage("Courses list Empty");
    }
}
else if (readingTimes) {
    String[] times = line.split(",");
    
    if (times.length != 0) {
        if (times.length <= 15) {
            for (String time : times) {
                String trimmedTime = time.trim();
                
                // Check for duplicates
                boolean isDuplicate = false;
                for (String addedTime : inp_1_timing) {
                    if (addedTime.equals(trimmedTime)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate && isValidTiming(trimmedTime)) {
                    inp_1_timing.add(trimmedTime);
                    String insert_timing = "INSERT INTO lecture_timings (timings) VALUES (" + '"' + trimmedTime + '"' + ");";
                    PreparedStatement in_timing = con.prepareStatement(insert_timing);
                    System.out.println(insert_timing);
                    in_timing.executeUpdate();
                    System.out.println(insert_timing);
                } else {
                    if (isDuplicate) {
                        showErrorMessage("Duplicate timing: " + trimmedTime);
                    } else if(!isValidTiming(trimmedTime)){
                        showErrorMessage("Invalid timing: " + trimmedTime);
                    }
                    // Exit the method on validation failure
                }
            }
        } else {
            showErrorMessage("Too many timings. Maximum allowed is 15.");
        }
    } else {
        showErrorMessage("Timings list Empty");
    }
}

                fileIsEmpty=false;
                
            }
            //in_rooms.close();
            con.close();
            if (fileIsEmpty) {
            // Handle the case where the file is empty
            showErrorMessage("Input file 1 is empty.");
            }
            //System.out.println("Completed_1");
        } catch (IOException e) {
            showErrorMessage("Input file 1 not found");
            e.printStackTrace();
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(CourseSchedulingOoad_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void readInputFile2(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean fileIsEmpty = true;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/course_scheduling","root","praveenkrishna2003");
            String delete_pref="DELETE FROM course_timepreference WHERE course_id IS NOT NULL;";
            String delete_course="DELETE FROM course_details WHERE courseid IS NOT NULL;";
            String com="COMMIT";
            PreparedStatement del_courses = con.prepareStatement(delete_course);
            PreparedStatement del_prefs = con.prepareStatement(delete_pref);
            PreparedStatement commit = con.prepareStatement(com);
            del_courses.executeUpdate();
            del_prefs.executeUpdate();
            commit.executeUpdate();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("; "); // Split by ": " to match the specified format
                
            
if (parts.length >= 3) {
    String course = parts[0].trim();
    String capacityStr = parts[1].trim();
    String[] time_parts = parts[2].split(",");
    String[] preference = new String[time_parts.length];

    // Copy elements from time_parts to preference
    for (int i = 0; i < time_parts.length; i++) {
        preference[i] = time_parts[i].trim();
    }

    try {
        int capacity = Integer.parseInt(capacityStr);
        if (capacity >= 2 && capacity <= 250) {
            if (preference.length <= 5) { // Check if preferences are less than or equal to 5
                // Check for duplicates
                boolean isDuplicate = false;
                for (String addedCourse : inp_2_courses) {
                    if (addedCourse.equals(course)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate && isValidCourse(course)) {
                    inp_2_courses.add(course);
                    inp_2_cap.add(capacity);
                    String insert_course = "INSERT INTO course_details (courseid,enrollment) VALUES (" + '"' + course + '"' + ',' + capacity + ");";
                    PreparedStatement in_course = con.prepareStatement(insert_course);
                    in_course.executeUpdate();

                    List<String> coursePreferences = new ArrayList<>();
                    for (String pref : preference) {
                        coursePreferences.add(pref.trim());
                        String insert_pref = "INSERT INTO course_timepreference (course_id,timings) VALUES (" + '"' + course + '"' + ',' + '"' + pref + '"' + ");";
                        PreparedStatement in_pref = con.prepareStatement(insert_pref);
                        System.out.println(insert_pref);
                        in_pref.executeUpdate();
                    }

                    // Add the list of preferences to inp_2_pref
                    inp_2_pref.add(coursePreferences);
                } else {
                    if (isDuplicate) {
                        showErrorMessage("Duplicate course: " + course);
                    } else {
                        showErrorMessage("Invalid input: " + line);
                    }
                }
            } else {
                showErrorMessage("Too many preferences. Maximum allowed is 5.");
            }
        } else {
            showErrorMessage("Enrollment is not within the valid range (2-250): " + line);
        }
    } catch (NumberFormatException e) {
        showErrorMessage("Invalid capacity: " + capacityStr);
    }
}



                fileIsEmpty=false;
            }
            
            con.close();
            if (fileIsEmpty) {
            // Handle the case where the file is empty
            showErrorMessage("Input file 2 is empty.");
            }
            //System.out.println("Completed_2");
        } catch (IOException e) {
            showErrorMessage("Input file 2 not found");
            e.printStackTrace();
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(CourseSchedulingOoad_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    // Getters for the arrays
    public List<String> getInp_1_rooms() {
        return inp_1_rooms;
    }

    public List<Integer> getInp_1_cap() {
        return inp_1_cap;
    }

    public List<String> getInp_1_courses() {
        return inp_1_courses;
    }

    public List<String> getInp_1_timing() {
        return inp_1_timing;
    }

    public List<String> getInp_2_courses() {
        return inp_2_courses;
    }

    public List<Integer> getInp_2_cap() {
        return inp_2_cap;
    }

    public List<List<String>> getInp_2_pref() {
        return inp_2_pref;
    }

    public Object readInputFile() {
        
        
        return haserror;
    }
}
