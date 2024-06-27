/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Student {
    
      private String index;
    private String name;
    private int age;
    private Date dob;
    private String gender;
    private String contact_number;
    private String address;

     public Student() {
        // Initialize variables with default values
        this.index = "";
        this.name = "";
        this.age = 0;
        this.dob = new Date(); // Default date
        this.gender = "";
        this.contact_number = "";
        this.address = "";
    }
    /**
     * @return the index
     */
    public String getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(String index) {
        this.index = index;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the contact_number
     */
    public String getContact_number() {
        return contact_number;
    }

    /**
     * @param contact_number the contact_number to set
     */
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
     // Insert operation
    public void insertStudent(Student person) {
        Connection conn = DBConnection.connectDB();
        String query = "INSERT INTO student_table (`index`, name, age, dob, gender, contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, person.getIndex());
            statement.setString(2, person.getName());
            statement.setInt(3, person.getAge());
            statement.setDate(4, new java.sql.Date(person.getDob().getTime()));
            statement.setString(5, person.getGender());
            statement.setString(6, person.getContact_number());
            statement.setString(7, person.getAddress());
            statement.executeUpdate();
            conn.close();
             JOptionPane.showMessageDialog(null, "Successfully Added...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Update operation
    public void updateStudent(Student person) {
                Connection conn = DBConnection.connectDB();
        String query = "UPDATE student_table SET name=?, age=?, dob=?, gender=?, contact_number=?, address=? WHERE `index`=?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setDate(3, new java.sql.Date(person.getDob().getTime()));
            statement.setString(4, person.getGender());
            statement.setString(5, person.getContact_number());
            statement.setString(6, person.getAddress());
            statement.setString(7, person.getIndex());
            
            int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Student information updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No records updated. Student not found or no changes made.");
        }

        conn.close();
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Delete operation
    public void deletePerson(String index) {
        Connection conn = DBConnection.connectDB();
        String query = "DELETE FROM student_table WHERE `index`=?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, index);
            
            
            int rowsDeleted = statement.executeUpdate();

        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(null, "Student information deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No records deleted. Student not found.");
        }

        conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Select operation
      public ResultSet searchPerson(String searchCriteria) {
          Connection conn = DBConnection.connectDB();
        String query = "SELECT * FROM student_table WHERE `index` LIKE ? OR name LIKE ? OR age = ? OR gender LIKE ? OR contact_number LIKE ? OR address LIKE ?";
        ResultSet resultSet = null;
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            String likePattern = "%" + searchCriteria + "%";
            preparedStatement.setString(1, likePattern);
            preparedStatement.setString(2, likePattern);
            // Assuming the search criteria for age is a String representing an integer
            try {
                int age = Integer.parseInt(searchCriteria);
                preparedStatement.setInt(3, age);
            } catch (NumberFormatException e) {
                // If searchCriteria cannot be parsed as an integer, set age to -1 to skip this condition
                preparedStatement.setInt(3, -1);
            }
            preparedStatement.setString(4, likePattern);
            preparedStatement.setString(5, likePattern);
            preparedStatement.setString(6, likePattern);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return resultSet;
    }


  
}
