/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import model.Student;
/**
 *
 * @author ASUS
 */
public class studentController {
     public boolean isValidData(String index, String name, int age, String dob, String gender, String contactNumber, String address) {
        // Add your validation logic here
        // For example, you can check if index, name, gender, contactNumber, and address are not empty
        return !index.isEmpty() && !name.isEmpty() && age > 0 && !dob.isEmpty() && !gender.isEmpty() && !contactNumber.isEmpty() && !address.isEmpty();
    }

    public void saveStudent(Student person) {
        JOptionPane.showMessageDialog(null, "Hey");
        try {
            if (isValidData(person.getIndex(), person.getName(), person.getAge(), person.getDob().toString(), person.getGender(), person.getContact_number(), person.getAddress())) {
                // Assuming you have a method to save person data in your PersonDAO class
                person.insertStudent(person);
            } else {
                JOptionPane.showMessageDialog(null, "Please double-check the input data!");
            }
        } catch (Exception ex) {
            System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void updateStudent(Student person) {
        try {
            person.updateStudent(person);
        } catch (Exception ex) {
            System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void deleteStudent(Student person) {
        try {
            person.deletePerson(person.getIndex());
        } catch (Exception ex) {
            System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void displayStudentTable(Student personDAO, JTable personTable, String searchCriteria) {
        DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
        tableModel.setRowCount(0);
        ResultSet resultSet = personDAO.searchPerson(searchCriteria);
        try {
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("index"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("dob"),
                        resultSet.getString("gender"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("address")
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
