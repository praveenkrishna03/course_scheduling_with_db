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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class output_file_1 extends JPanel {
    private DefaultTableModel tableModel;
    JButton backButton;

    public output_file_1(String[][] data,List<String>inp_1_roomList,List<String> inp_1_timing) {
        if (data != null && data.length > 0 && data[0] != null && data[0].length > 0) {
            int numRows = data.length+1;
            int numCols = data[0].length+1;
    
            tableModel = new DefaultTableModel(numRows, numCols);
    
            for (int i = 1; i < numCols; i++) {
                tableModel.setValueAt(inp_1_timing.get(i-1), 0, i);
            }
    
            for (int i = 1; i < numRows; i++) {
                tableModel.setValueAt(inp_1_roomList.get(i-1), i, 0);
            }

            for(int i=1;i<numRows;i++){
                for(int j=1;j<numCols;j++) {
                    
                    tableModel.setValueAt(data[i-1][j-1],i,j);

                }
            }
    
            JTable table = new JTable(tableModel);
    
            // Hide the heading column
            table.getTableHeader().setVisible(false);
    
            JScrollPane scrollPane = new JScrollPane(table);

            backButton = new JButton("Back");
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(backButton);
            setLayout(new BorderLayout());
            add(buttonPanel, BorderLayout.CENTER);
            add(scrollPane, BorderLayout.SOUTH);
            
        } else {
            System.out.println("it is empty");
            // Handle the case when data is null or empty
            // You may want to display an error message or handle it differently
            // For simplicity, this example does nothing in case of empty data.
        }
    }

    public void setbackButtonListener(ActionListener listener) {
        // Assuming you have a JButton named inputFile1Button
        backButton.addActionListener(listener);
    }
    
    
}

