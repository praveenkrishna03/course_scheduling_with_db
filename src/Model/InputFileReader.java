/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gandh
 */

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class InputFileReader {
    public List<String> inp_1_rooms = new ArrayList<>();
    private List<Integer> inp_1_cap = new ArrayList<>();
    private List<String> inp_1_courses = new ArrayList<>();
    public List<String> inp_1_timing = new ArrayList<>();
    private List<String> inp_2_courses = new ArrayList<>();
    private List<Integer> inp_2_cap = new ArrayList<>();
    private List<List<String>> inp_2_pref = new ArrayList<>();

    private boolean isValidRoomNumber(String roomNumber) {
        try {
            int room = Integer.parseInt(roomNumber);
            return room >= 100 && room <= 900;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean validateInputCourse(String course) {
        return inp_1_rooms.contains(course);
    }
    
    

    private boolean isValidTiming(String timing) {
        return timing.matches("^(MWF\\d{2}(:\\d{2})?|TT\\d{2}(:\\d{2})?)$|^\\d{1,2}$");
    }

    private boolean isValidCapacity(String capacity) {
        // Check if capacity is a number and within the range 10 to 90
        try {
            int cap = Integer.parseInt(capacity);
            return cap >= 10 ;
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
        

        readInputFile1("src\\main\\java\\com\\mycompany\\courseschedulingooad\\data\\input_file_1.txt");
        readInputFile2("src\\main\\java\\com\\mycompany\\courseschedulingooad\\data\\input_file_2.txt");
    }

    private void readInputFile1(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean readingRooms = false;
            boolean readingCourses = false;
            boolean readingTimes = false;

            while ((line = br.readLine()) != null) {
                if (line.equals("rooms")) {
                    readingRooms = true;
                    continue;
                } else if (line.equals("courses")) {
                    readingRooms = false;
                    readingCourses = true;
                    continue;
                } else if (line.equals("timing")) {
                    readingCourses = false;
                    readingTimes = true;
                    continue;
                }

                if (readingRooms) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        if (isValidRoomNumber(parts[0].trim()) && isValidCapacity(parts[1].trim())) {
                            inp_1_rooms.add(parts[0].trim());
                            inp_1_cap.add(Integer.parseInt(parts[1].trim()));
                        } else {
                            showErrorMessage("Invalid room number or capacity: " + line);
                            return; // Exit the method on validation failure
                        }
                    }
                } else if (readingCourses) {
                    String[] courses = line.split(",");
                    for (String course : courses) {
                        if (isValidCourse(course.trim())) {
                            inp_1_courses.add(course.trim());
                        } else {
                            showErrorMessage("Invalid course: " + course);
                            return; // Exit the method on validation failure
                            }
                        }
                } else if (readingTimes) {
                    String[] times = line.split(",");
                    boolean validTimings = true;
                    for (String time : times) {
                        if (validTimings) {
                            inp_1_timing.add(time.trim());
                    } else {
                        showErrorMessage("Invalid timing(s): " + line);
                    }
                    }

                    
                }
                
            }
            //System.out.println("Completed_1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInputFile2(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("; "); // Split by ": " to match the specified format
                
            
                if (parts.length >= 3) {
                    String course = parts[0].trim();
                    String capacityStr = parts[1].trim();
                    String[] time_parts = parts[2].split(",");
                    String[] preference = new String[time_parts.length];
                    //for(int i=0;i<preference.length;i++){
                    //    System.out.println(preference[i]);
                    //}
                    
                    // Copy elements from time_parts to preference
                    for (int i = 0; i < time_parts.length; i++) {
                        
                        preference[i] = time_parts[i].trim();
                    }
            
                    if (isValidCourse(course) && isValidCapacity(capacityStr)/*&& validateInputCourse(course) && isValidTiming(preference)*/) {
                        inp_2_courses.add(course);
                        inp_2_cap.add(Integer.parseInt(capacityStr));
            
                        // Ensure that inp_2_pref contains all the strings from preference
                        List<String> coursePreferences = new ArrayList<>();
                        for (String pref : preference) {
                            coursePreferences.add(pref.trim());
                        }

                    // Add the list of preferences to inp_2_pref
                        inp_2_pref.add(coursePreferences);
                    } else {
                        showErrorMessage("Invalid input: " + line);
                    }
                }
            }
            
            
            //System.out.println("Completed_2");
        } catch (IOException e) {
            e.printStackTrace();
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
        return null;
    }
}
