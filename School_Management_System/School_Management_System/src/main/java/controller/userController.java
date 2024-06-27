/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.*;
import javax.swing.*;
import model.DBConnection;
import model.User;
import view.frmLogin;
import view.frmWelcome;

public class userController {
    
public boolean isValidUsername(String username) {
    return username != null && !username.isEmpty() ;
}

public boolean isValidPassword(String password) {
    return password != null && !password.isEmpty() &&
           password.length() >= 8 &&
           password.chars().anyMatch(Character::isUpperCase) &&
           password.chars().anyMatch(Character::isLowerCase) && //String covert into the IntStream , check if any of these code point is lower case
           password.chars().anyMatch(Character::isDigit);
}

public boolean isPasswordConfirmed(String password, String confirmPassword) {
    return password.equals(confirmPassword);
}

public void Register(User user){
        try {           
           
            if (isValidUsername(user.getUserName()) && isValidPassword(user.getPassword()) && isPasswordConfirmed(user.getPassword(), user.getConfirmPassword()))
            {
                user.userInsertQuery(user.getUserName(), user.getUserPhoneNumber(), user.getPassword());
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please Check Again !!!");
            }
            
        } catch (Exception ex) {
            System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, ex.toString());  
        }
    }

public boolean isValidUsername2(String username) {
        boolean isValid = false;
Connection conr = DBConnection.connectDB(); 
        try {
                          
            PreparedStatement ps = conr.prepareStatement("SELECT COUNT(*) FROM admin_table WHERE Username = ?");
            ps.setString(1, username);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    isValid = true;
                }
            }
            conr.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while checking the username: " + ex.getMessage());
        }

        return isValid;
    }

public void Login(User user,frmLogin login){
    String enteredUsername = user.getUserName();
    String enteredPassword = user.getPassword();
    
    if (isValidUsername2 (enteredUsername)) {
            Connection conr = DBConnection.connectDB();
            try {

               PreparedStatement ps = conr.prepareStatement("SELECT Password FROM admin_table WHERE Username = ?");
                ps.setString(1, enteredUsername);

                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    String storedPasswordHash = resultSet.getString("Password");

                    boolean isPasswordValid = enteredPassword.equals(storedPasswordHash);

                    if (isPasswordValid) {
                       conr.close();

                        JOptionPane.showMessageDialog(null, "Successfully Login");

                        frmWelcome main = new frmWelcome();
                        main.setLocationRelativeTo(null);
                        main.setVisible(true);                     
                        login.setVisible(false);

                    } else {
                        conr.close();
                        JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username...");
        }
    
}    
}
