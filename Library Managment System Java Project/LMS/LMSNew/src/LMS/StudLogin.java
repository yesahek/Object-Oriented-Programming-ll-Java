/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author ukee
 */
public class StudLogin extends JFrame implements ActionListener {

JFrame frame = new JFrame();
JLabel logo, tit, fileName, size,date,author,fileTypeLabel,booktypeLabel,subjectLabel,userName,password,userType;
JTextArea file , filed, files, filea,txtAreaAbout;
JLabel Profile, label,imgLabel,fileSizeLabel,imgProLabel ;
JPanel leftpan ,gridPanel, contentGridPanel, contentGridPanel2,topPanel ,center,sidePanel,mainPanel;
JPanel main,ContentPanel, contentSide,filePanel,imagePanel,fileDetail;
JButton logOut;
JLabel copyRight;
Container mainCon = this.getContentPane();
ImageIcon img = new ImageIcon(new ImageIcon("src/LibraryManagmentSystem/IMG/book1.jpg").getImage().getScaledInstance(200,250, Image.SCALE_DEFAULT));
ImageIcon imgProfile = new ImageIcon(new ImageIcon("src/LibraryManagmentSystem/IMG/profile.jpg").getImage().getScaledInstance(200,250, Image.SCALE_DEFAULT));
JScrollPane scroll;

Connection conn;
Statement stmt;
ResultSet rst;

int books = 0;
boolean sesion = false ;

StudLogin(String uName, String role) throws SQLException{
 
    frame.setVisible(true);
    frame.setSize(1100,700);
    frame.setLocation(150,25);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(mainCon);
    
        mainCon.setLayout(new BorderLayout(8,6));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GREEN));
        //Top Panel
        topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(Color.BLACK,3));
        topPanel.setBackground(Color.ORANGE);
        topPanel.setLayout(new FlowLayout(20,20,20));            
        topPanel.add(logo = new JLabel("Welcom "+uName +" to MicroLink Library Managment System"));
        logo.setFont(new Font("Arial", Font.BOLD, 18));
        mainCon.add(topPanel, BorderLayout.NORTH);        
        //Middle Panel
        
        main = new JPanel();
        main.setOpaque(true);
        main.setBorder(new LineBorder(Color.BLACK, 1));
        main.setLayout(new BorderLayout());
        
        sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout(30,30,30));
        sidePanel.setBorder(new LineBorder(Color.BLACK,0));
        sidePanel.setBackground(Color.CYAN);        
        //side content Panel
        
        contentSide = new JPanel(new GridLayout(04,0,30,20));
        Profile= new JLabel("Wellcome "+uName);
        Profile.setFont(new Font(null,Font.PLAIN,14)); 
        imgProLabel = new JLabel(imgProfile);
        contentSide.add(Profile); 
        
       
        logOut = new JButton("LogOut");
        logOut.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            logOutActionPerformed(evt);
        }        
      }); 
        contentSide.add(logOut);
        sidePanel.add(contentSide);
        mainCon.add(sidePanel, BorderLayout.WEST); 
        //Center or main 
        
        //Center Content area     
        ContentPanel = new JPanel();
        ContentPanel.setBorder(new LineBorder(Color.BLACK, 1));
        ContentPanel.setLayout(new GridLayout(4,3,20,20));
       // ContentPanel.setBackground(Color.GRAY);
        
        DBopen();
        for (int i=0; i<books; i++){
        String c = "SELECT * FROM `books` LIMIT "+i+",1";   
        rst = stmt.executeQuery(c);
        rst.next();
        filePanel = new JPanel();
        
        filePanel.setBorder(new LineBorder(Color.ORANGE, 2));
        filePanel.setLayout(new BorderLayout());
        filePanel.setBackground(Color.ORANGE);
        imagePanel = new JPanel(new GridLayout());        
        imgLabel = new JLabel(img);
        
        fileDetail = new JPanel();
        fileDetail.setLayout(new GridLayout(0,1));
        fileDetail.setBorder(new LineBorder(Color.BLACK, 2));
        fileName = new JLabel(rst.getString("bookName")+"    ");
        fileName.setFont(new Font("Arial", Font.BOLD, 18));  
        fileSizeLabel = new JLabel("size : " + rst.getString("fileSize"));
        fileTypeLabel = new JLabel("File type :"+ rst.getString("fileType") );
        subjectLabel = new JLabel(rst.getString("subject"));
        booktypeLabel = new JLabel(rst.getString("bookType")+" Book");
        
        fileDetail.add(fileName);
        fileDetail.add(subjectLabel);
        fileDetail.add(booktypeLabel);
        fileDetail.add(fileTypeLabel);
       // fileDetail.add(fileSizeLabel);        
        filePanel.add(imgLabel, BorderLayout.NORTH);
        filePanel.add(fileDetail,BorderLayout.SOUTH);
        
        ContentPanel.add(filePanel);
        scroll = new JScrollPane(ContentPanel);
          
        mainCon.add(scroll, BorderLayout.CENTER);
        
       //booksTable
    }
}
    
void DBopen(){
    try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
            stmt = conn.createStatement();
            rst = stmt.executeQuery("select COUNT(*) AS Count from books");
            while(rst.next())
            books = rst.getShort("Count");             
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection Failed: " + ex.getMessage());
        }
}
    @Override
    public void actionPerformed(ActionEvent e) {
     }
    private void logOutActionPerformed(ActionEvent evt) {
      JOptionPane.showMessageDialog(this,"Bye See you Soon");
       frame.dispose();
    }
}
