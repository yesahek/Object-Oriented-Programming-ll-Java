package projectS;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;


public class NewMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//TODO Auto-generated method stub
		JPanel container=new JPanel( new GridLayout(4,0));
		container.setBackground(Color.DARK_GRAY);
		JPanel container2=new JPanel( new GridLayout(2,2,0,5));
		JPanel but=new JPanel(new GridLayout(1,2,20,15));
		container2.setBackground(Color.DARK_GRAY);
		but.setBorder(BorderFactory.createEmptyBorder(20, 180, 60, 190));
but.setBackground(Color.DARK_GRAY);
		container2.setBorder(BorderFactory.createEmptyBorder(50, 85, 0, 105));
		JPanel pan=new JPanel();
		pan.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		pan.setBackground(Color.DARK_GRAY);

		JPanel pan2=new JPanel();
final JFrame fram=new JFrame();
fram.setSize(560, 450);
fram.setVisible(true);
fram.setTitle("Students Managment System");
fram.setResizable(false);
JLabel lab=new JLabel("Students Managment System");
lab.setForeground(Color.GREEN);
JLabel lab2=new JLabel("User Name");
lab2.setForeground(Color.WHITE);
JLabel lab3=new JLabel("Password");
lab3.setForeground(Color.WHITE);
lab.setForeground(Color.white);
final JTextField username = new JTextField();
final JPasswordField password=new JPasswordField();
BufferedImage myPicture = null;
//try {
//	myPicture = ImageIO.read(new File("C:/Users/aman/Pictures/student-management-system-500x500.png"));
//} catch (IOException e1) {
//	// TODO Auto-generated catch block
//	e1.printStackTrace();
//}
JLabel picLabel = new JLabel();
picLabel.setSize(60, 60);
JButton login = new JButton("Login");
JButton rst = new JButton("Reset");
container2.add(lab2);
container2.add(username);

container2.add(lab3);

container2.add(password);

but.add(login);
but.add(rst);

lab2.setFont(new java.awt.Font("Tahoma", 1, 14));
lab.setFont(new java.awt.Font("Tahoma", 1, 24));

lab3.setFont(new java.awt.Font("Tahoma", 1, 14));
pan.add(lab);
pan.add(picLabel);
container.add(pan);
container.add(container2);
container.add(but);
fram.add(container);
login.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
    	String unam=username.getText().toString();
    	String psw=password.getText().toString();
    	try {
			CheckAccount(unam,psw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private void CheckAccount(String unam, String psw) throws SQLException {
		// TODO Auto-generated method stub
		   try{
			   Class.forName("com.mysql.jdbc.Driver");
			   String conURL = "jdbc:mysql://localhost/user?user=root&password=";
			    java.sql.Connection con = DriverManager.getConnection(conURL);
			       //MyConnection();
			         // PreparedStatement ps;
			           //ResultSet rs;
			          
			           
			        //   String query = "SELECT * FROM user WHERE test = '1'";
			        java.sql.Statement stmt = con.createStatement();
			   String sql = "SELECT * FROM useraccount WHERE username = '"+unam+"' and password='"+psw+"'";
			   stmt.execute(sql);
			   ResultSet rs = stmt.executeQuery(sql);
			           
			    
			    
			            // java.sql.Statement stmt = con.createStatement();
			               

			               
			              // rs = ps.executeQuery();
			               
			               if(rs.next())
			               {
			                  JOptionPane.showMessageDialog(null, "Login Successfull", "Login Success", 1);
			                      fram.setVisible(false);
			                     Home hom=new Home(); 
			                  
			               }
			               else{
			                       JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
			                   }
			               
			           }catch (ClassNotFoundException ex) {
                   JOptionPane.showMessageDialog(null, ex.toString(), "Error", 2);

			          }
	}
});
rst.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
    	username.setText("");
    	password.setText("");
   }
});
	}
	  
}
