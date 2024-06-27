/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
import javax.swing.*;

public class DBConnection {
     
    public static Connection connectDB(){
       Connection con = null; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system?useSSL=false", "root", null);
            return con;
        } catch (Exception e) {
            
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
        
    }
}
