/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS;


import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ukee
 */
class Login extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    Container main = this.getContentPane();
    JPanel TopPanel;
    JLabel userIdLabel,userPasswordLabel,messageLabel,Logo,Intro,userType;
    JButton loginButton,resetButton;
    JTextField userIdField ;
    JPasswordField userPasswordField;
    JComboBox chkUserType;
    

    public Login(String title,String role){
        super(title + role);
        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");
        userIdField = new JTextField();
        userPasswordField = new JPasswordField();
        userIdLabel = new JLabel("User Name");
        userPasswordLabel = new JLabel("Password");
        userType = new JLabel("User Type : ");
        userType.setBounds(10, 110, 250, 30);
        String userTypes[]={"Choose......","Libraryst","Student"};        
        chkUserType = new JComboBox(userTypes);
        messageLabel = new JLabel();
        
        userIdLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);
        
        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));
        
        userIdField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);
        
        userType.setBounds(50, 225, 100, 25);
        chkUserType.setBounds(125, 225, 100, 25);
        chkUserType.addActionListener(this);
        
        loginButton.setBounds(225, 300, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        
        resetButton.setBounds(125, 300, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        frame.add(userIdLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIdField);
        frame.add(userPasswordField);
        frame.add(userType);
        frame.add(chkUserType);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLocation(500,180);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()==resetButton){
        userIdField.setText("");
        userPasswordField.setText("");
        chkUserType.setSelectedIndex(0);
    }
    if(e.getSource()==loginButton){
        String userId = userIdField.getText();
        String password = String.valueOf(userPasswordField.getPassword());
    if(chkUserType.getSelectedIndex()==2){
         try{
         String role = "Student";
         Class.forName("com.mysql.jdbc.Driver");
          Connection  con = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
          Statement  stmt = con.createStatement();
          String sql="Select * from students where studUserName='"+userId+"' and StudPass='"+password+"'";
          ResultSet rst = stmt.executeQuery(sql);
          if(rst.next()){
              JOptionPane.showMessageDialog(loginButton, userId+" Login successful");
              frame.dispose();
              StudLogin student = new StudLogin(userId,role);  
          }else{
              JOptionPane.showMessageDialog(loginButton, userId+"User name or Password Not correct");
          }
        }catch
                (ClassNotFoundException | SQLException d){System.out.print(d);
        }
        } if(chkUserType.getSelectedIndex()==1){
             try{
                  String role = "Libraryst";
        Class.forName("com.mysql.jdbc.Driver");
          Connection  con = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
          Statement  stmt = con.createStatement();
          String sql="Select * from libraryst where libName='"+userId+"' and libPassword='"+password+"'";
          ResultSet rst = stmt.executeQuery(sql);
          if(rst.next()){
              JOptionPane.showMessageDialog(loginButton, userId+"Login successful");
               frame.dispose();
              LibrarystLoginBook Lib = new LibrarystLoginBook(userId,role);  
          }else{
              JOptionPane.showMessageDialog(loginButton, userId+"User name or Password Not correct");
          }
        }catch
                (ClassNotFoundException | SQLException d){System.out.print(d);
        }
     }          
 }
}
}
/**
 *
 * @author ukee
 */
public class Main {
    public static void main(String [] args){
        String Title = "Login To MicroLink Digitial Library Managment System";
        String Role ="Guste";
        Login login = new Login(Title,Role);        
    }
}
