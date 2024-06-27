/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import view.frmRegister;
import java.sql.*;
import javax.swing.*;

public class User {
    private String userName;
    private String userPhoneNumber;
    private String password;
    private String confirmPassword;
    
        public User() {
        userName = "";
        userPhoneNumber = "";
        password = "";
        confirmPassword = "";
    }
        
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public void userInsertQuery(String username, String phone_number, String password) {
    Connection conr = DBConnection.connectDB();
    try{
        
        
        PreparedStatement ps = conr.prepareStatement("INSERT INTO admin_table (username, phone_number, password) values(?,?,?)");
        ps.setString(1,username);
        ps.setString(2,phone_number);
        ps.setString(3,password);
        int status=ps.executeUpdate();
        if(status==1){
                JOptionPane.showMessageDialog(null,"Data sent successfully");}
        conr.close();
        frmRegister fr = new frmRegister();
        fr.clearTextFields();
        
    }catch(Exception ex){
            System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, ex.toString());  
    }
    }
        
}
