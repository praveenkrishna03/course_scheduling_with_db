/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package courseschedulingooad_2;

import View.MainFrame;


import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gandh
 */
public class CourseSchedulingOoad_2 {
    

    public static void main(String[] args) {
        Connection con;
        PreparedStatement pst;    // runs in AWT thread
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/course_scheduling","root","praveenkrishna2003");
            System.out.println("Success");
            SwingUtilities.invokeLater(MainFrame::new);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(CourseSchedulingOoad_2.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(CourseSchedulingOoad_2.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}

