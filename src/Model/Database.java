/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gandh
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import View.input_file_2;

public class Database {

    
    private ArrayList<RoomDB> roomsArrayList;
    private ArrayList<CourseDB> courseArrayList;
    private ArrayList<TimingDB> timingArrayList;
    private ArrayList<InputDB> InputCourseArrayList;
    private ArrayList<InputDB> InputCapacityArrayList;
    private ArrayList<InputDB> InputPreferencesArrayList;
    private ArrayList<InputDB> InputArrayList;
    private int inputentry=0;


    public Database() {
        roomsArrayList = new ArrayList<>();
        courseArrayList = new ArrayList<>();
        timingArrayList = new ArrayList<>();
        InputCourseArrayList = new ArrayList<>();
        InputCapacityArrayList = new ArrayList<>();
        InputPreferencesArrayList = new ArrayList<>();
        InputArrayList=new ArrayList<>();
        input_file_2 input_file_2 = new input_file_2();
 
    }

    // adds user to our collection
    public void addRoom(RoomDB room) {
        roomsArrayList.add(room);
    }

    public void addCourse(CourseDB course) {
        courseArrayList.add(course);
    }

    public void addTiming(TimingDB timing) {
        timingArrayList.add(timing);
    }



    public void addInputCourse(InputDB course) {
        InputCourseArrayList.add(course);
        //InputCapacityArrayList.add(capacity);
        //InputPreferencesArrayList.add(preferences);
    }

    public void addInputCapacity(InputDB capacity) {
        InputCapacityArrayList.add(capacity);
    }

    public void addInputPreferences(InputDB preferences) {
        InputPreferencesArrayList.add(preferences);
    }

    // saves user to database file
    // Inside Database.java

        public void saveRoom(File file) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                
                bufferedWriter.write("rooms");
                //bufferedWriter.newLine();
                for (RoomDB room : roomsArrayList) {
                    bufferedWriter.newLine();
                    String save_data_room = room.getRoom() + ":" + room.getCapacity();
                    bufferedWriter.write(save_data_room);
                    
                }
                //bufferedWriter.write(";");
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void saveCourse(File file) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
                bufferedWriter.write("courses");
                bufferedWriter.newLine();
                //bufferedWriter.write("    ");
                for (CourseDB course : courseArrayList) {
                    
                    String save_data_course =course.getCourse() + ",";
                    bufferedWriter.write(save_data_course);
                }
                //bufferedWriter.write(";");
                bufferedWriter.newLine();

                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void saveTiming(File file) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
                bufferedWriter.write("timing");
                bufferedWriter.newLine();
                //bufferedWriter.write("    ");
                for (TimingDB timing : timingArrayList) {
                    
                    String save_data_timing =timing.getTiming() + ",";
                    bufferedWriter.write(save_data_timing);
                }
                //bufferedWriter.write(";");
                bufferedWriter.newLine();

                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void saveInput(File file, InputDB input) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {

                if(inputentry<20){

                String saveData = input.getInputCourse() + "; " + input.getInputCapacity() + "; " + input.getInputPreferences()+"  ";
                bufferedWriter.write(saveData);
                bufferedWriter.newLine();
                inputentry++;
                    JOptionPane.showMessageDialog(null, "Succesfully added", "added", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Maximum Constraint Reached", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        
        

        

        
        
        


    // reads user from database file
    public Object[] loadUsers(File file) {
        Object[] objects;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            // each lines to array
            objects = bufferedReader.lines().toArray();
            bufferedReader.close();
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
