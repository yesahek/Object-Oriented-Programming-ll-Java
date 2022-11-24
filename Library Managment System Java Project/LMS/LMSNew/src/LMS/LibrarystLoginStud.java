/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS;

/**
 *
 * @author ukee
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ukee
 */

class stud {
    private final int userId;
final private String IdNo,userName, userPassword, fullName;
    
    public stud(int userId, String IdNo, String userName,String userPassword,
            String fullName){
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.fullName = fullName;
        this.IdNo = IdNo;
    }
    public int getId(){
        return userId;
    }
    public String getIdNo(){
        return IdNo;
    }
    public String getName(){
        return userName;
    }
    public String getUserPassword (){
        return userPassword;
    }
    public String getFullName (){
        return fullName;
    }
}

public final class LibrarystLoginStud extends JFrame implements ActionListener{
JFrame frame = new JFrame();
JLabel logo,userName,labelIdNo,labelStudName,labelStudPass,labelFullName;
JLabel Profile,labelFooter,tableTitle;
JTextField txtIdNo,txtStudName,txtStudPass,txtFullName,txtSubject,txtBookType,txtGread;
JPanel main,topPanel,sidePanel, studContentGridPanel, studFormPanel,studTablePanel,footerPanel,contentSide;
JTextField txtfFname, txtfMname, txtfLname,txtFileSize,txtFileType;
JButton btnStudClear,btnStudSave,btnStudDelete,btnStudUpdate,butLibrarists,butBooks,butStudent,logOut;


JTable studTable;
JScrollPane sp;
Container mainContainer = this.getContentPane();

Connection Conn = null;
Statement stmt = null;
ResultSet rst = null;
PreparedStatement pst = null;

int Q,i;

//int books = 0;
//boolean sesion = false ;   
private String role;
private String uName;

 public LibrarystLoginStud(String uName,String role) throws SQLException{
        super("Welcome "+uName+" "+ role);
        mainContainer.setLayout(new BorderLayout(8,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1150,740);    
        frame.setVisible(true);
        frame.setLocation(150,0);
        frame.setResizable(false);
        frame.add(mainContainer);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GREEN));
        //Top Panel
        topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(Color.BLACK,3));
        topPanel.setBackground(Color.ORANGE);
        topPanel.setLayout(new FlowLayout(20,20,20));            
        topPanel.add(logo = new JLabel("Library Managment System"));
        logo.setFont(new Font("Arial", Font.BOLD, 18));       
        mainContainer.add(topPanel, BorderLayout.NORTH);       
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
        Profile= new JLabel("Wellcome"+uName);
        Profile.setFont(new Font(null,Font.PLAIN,14));        
        butStudent = new JButton("Students");
        butStudent.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            StudentsActionPerformed(evt);
        }      
    });
        butBooks = new JButton("Books");
        butBooks.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            BooksActionPerformed(evt);
        }             
});
        
        logOut = new JButton("LogOut");
        logOut.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            logOutActionPerformed(evt);
        }        
      }); 
        
        contentSide.add(Profile);        
        contentSide.add(butBooks);
        contentSide.add(butStudent);        
        contentSide.add(logOut);
        sidePanel.add(contentSide);
        main.add(sidePanel, BorderLayout.WEST); 
        //Center or main 
        
        //form Student registration area        
        studFormPanel = new JPanel();
        studFormPanel.setBorder(new LineBorder(Color.BLACK, 1));
        studFormPanel.setLayout(null);
        labelIdNo= new JLabel("Student ID");
        labelIdNo.setBounds(05, 0, 150, 35);
        labelIdNo.setFont(new Font(null,Font.PLAIN,18));

        txtIdNo = new JTextField(50);
        txtIdNo.setBounds(40, 30, 200, 25);
        txtIdNo.setFont(new Font("Tahoma", 1, 18));
        
        labelStudName = new JLabel("User Name");
        labelStudName.setBounds(05, 55, 150, 35);
        labelStudName.setFont(new Font(null,Font.PLAIN,18));

        txtStudName = new JTextField(50);
        txtStudName.setBounds(40, 90, 200, 25);
        txtStudName.setFont(new Font("Tahoma", 1, 18));

        labelStudPass = new JLabel("PassWord");
        labelStudPass.setBounds(05, 115, 150, 35);
        labelStudPass.setFont(new Font(null,Font.PLAIN,18));

        txtStudPass= new JTextField(50);
        txtStudPass.setBounds(40, 150, 200, 25);
        txtStudPass.setFont(new Font("Tahoma", 1, 18));

        labelFullName = new JLabel("Full Name");
        labelFullName.setBounds(05, 175, 150, 35);
        labelFullName.setFont(new Font(null,Font.PLAIN,18));

        txtFullName= new JTextField(50);
        txtFullName.setBounds(40, 210, 200, 25);
        txtFullName.setFont(new Font("Tahoma", 1, 18));
        

        btnStudUpdate = new JButton("Update");
        btnStudUpdate.setBounds(10, 485, 115, 35);
        btnStudUpdate.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnStudUpdate.addActionListener(this);
        btnStudUpdate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdadteActionPerformed(evt);
        }                 
          });
        btnStudSave = new JButton("Add New");
        btnStudSave.setBounds(140, 485, 115, 35);
        btnStudSave.addActionListener(this);
        btnStudSave.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnStudSave.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSaveActionPerformed(evt);
        }
          });
        btnStudClear = new JButton("Clear");
        btnStudClear.setBounds(10, 525, 115, 35);
        btnStudClear.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnStudClear.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnClearActionPerformed(evt);
        }
          });
        btnStudDelete = new JButton("Delete");
        btnStudDelete.setBounds(140, 525, 115, 35);
        btnStudDelete.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnStudDelete.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteActionPerformed(evt);
        }       
          }); 
        studFormPanel.add(btnStudUpdate);
        studFormPanel.add(btnStudDelete);
        studFormPanel.add(btnStudSave);
        studFormPanel.add(btnStudClear);
        studFormPanel.add(txtFullName);
        studFormPanel.add(labelFullName);
        studFormPanel.add(txtStudPass);
        studFormPanel.add(labelStudPass);
        studFormPanel.add(txtStudName);
        studFormPanel.add(labelStudName);
        studFormPanel.add(txtIdNo);
        studFormPanel.add(labelIdNo); 
        main.add(studFormPanel); 
       //studTable
    
       
        studTablePanel = new JPanel();
        studTablePanel.setLayout(new FlowLayout(30,10,10));
        ArrayList<stud> list = studList();
        DefaultTableModel model = new DefaultTableModel();
        Object[] row = new Object[9];
        model.addColumn("Id");
        model.addColumn("Student IdNo");
        model.addColumn("Student UserName");
        model.addColumn("Student Password");
        model.addColumn("Student FullName");
            for (int i = 0;i<list.size();i++){
                row[0]=list.get(i).getId();
                row[1]=list.get(i).getIdNo();
                row[2]=list.get(i).getName();
                row[3]=list.get(i).getUserPassword();
                row[4]=list.get(i).getFullName();
                model.addRow(row);
                studTable = new JTable(model);
            }
        RefrashTable();
        studTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });   
        //studTable.setPreferredScrollableViewportSize(studTable.getPreferredSize());
        //studTable.setFillsViewportHeight(true);
        sp = new JScrollPane(studTable);
        studTablePanel.add(sp);        
        studTablePanel.setLayout(new GridLayout(1,0,100,0));      
        main.add(studTablePanel,BorderLayout.EAST);         
        mainContainer.add(main, BorderLayout.CENTER);
        //foter
        
        footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(1));        
        footerPanel.setBackground(Color.orange);
        footerPanel.setBorder(new LineBorder(Color.BLACK,1));
        labelFooter = new JLabel();
        labelFooter.setText("Library Managment System Group Project Of OOP2");
        footerPanel.add(labelFooter);
        mainContainer.add(footerPanel, BorderLayout.SOUTH);              
    }

    public ArrayList<stud> studList(){
        ArrayList<stud> studList = new ArrayList<>();
            try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
             String query1="SELECT * FROM `students`";
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(query1);
             stud u;
             while(rs.next()){
                 u=new stud(rs.getInt("studId"), rs.getString("IdNo"), rs.getString("studUserName"),rs.getString("studPass"),
                       rs.getString("fullName"));
                 studList.add(u);  
             }
            } catch (ClassNotFoundException | SQLException e) {
               JOptionPane.showMessageDialog(null, e);
            }      
    return studList;
    }    
    public void tableMouseClicked(java.awt.event.MouseEvent e) {
    DefaultTableModel RecordTable = (DefaultTableModel)studTable.getModel();
    int SelectedRows = studTable.getSelectedRow();
    txtIdNo.setText(RecordTable.getValueAt(SelectedRows,1).toString());
    txtStudName.setText(RecordTable.getValueAt(SelectedRows,2).toString());
    txtStudPass.setText(RecordTable.getValueAt(SelectedRows,3).toString());
    txtFullName.setText(RecordTable.getValueAt(SelectedRows,4).toString());
    }
    public String loginfunction(String uName , String role){
    role = this.role;
    uName = this.uName;

   //uName,String role
    return (userName + role);    
    } 
    //RefrashTable
    public void RefrashTable()
    {
         try
        {
          Class.forName("com.mysql.jdbc.Driver");
          Conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
          pst = Conn.prepareStatement("select * from students");
          rst = pst.executeQuery();
          ResultSetMetaData StData = rst.getMetaData();            
          Q = StData.getColumnCount();            
          DefaultTableModel RecordTable = (DefaultTableModel)studTable.getModel();
          RecordTable.setRowCount(0);
            while(rst.next()){                
                Vector columnData = new Vector();                
                for (i = 1; i <= Q; i++)
                {
                    columnData.add(rst.getString("studId"));
                    columnData.add(rst.getString("IdNo"));
                    columnData.add(rst.getString("studUserName"));
                    columnData.add(rst.getString("studPass"));
                    columnData.add(rst.getString("fullName"));                  
                }
                    RecordTable.addRow(columnData);                
            }         
        }
        catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //Actions To Be Performed on Buttons
    private void logOutActionPerformed(ActionEvent evt) {
      JOptionPane.showMessageDialog(this,"Bye See you Soon");
       frame.dispose();
    }
    private void StudentsActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this,"You are Already on Students Tab");
     }
    private void BooksActionPerformed(ActionEvent evt) {
            try {
        frame.dispose();
        LibrarystLoginBook a = new LibrarystLoginBook(uName,role);
        } catch (SQLException ex) {
        Logger.getLogger(LibrarystLoginBook.class.getName()).log(Level.SEVERE, null, ex);
         }
    }  
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {                                          
    try
        {
           Class.forName("com.mysql.jdbc.Driver");
           Conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
            pst = Conn.prepareStatement("insert into students (IdNo, studUserName, studPass, fullName)values(?,?,?,?)");            
            pst.setString(1, txtIdNo.getText());
            pst.setString(2, txtStudName.getText());
            pst.setString(3, txtStudPass.getText());
            pst.setString(4, txtFullName.getText());
                        
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Student Register Successfully");
          RefrashTable();
        } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(LibrarystLoginStud.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    private void btnUpdadteActionPerformed(ActionEvent evt) {
         try
        {
          Class.forName("com.mysql.jdbc.Driver");
          Conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
          int row = studTable.getSelectedRow();
          String vlaue = (studTable.getModel().getValueAt(row,0).toString());
          String query ="UPDATE students SET IdNo=?, studUserName=?, studPass=?, fullName=? where studId="+vlaue;
            stmt = Conn.createStatement();
            pst = Conn.prepareStatement(query);
            pst.setString(1, txtIdNo.getText());
            pst.setString(2, txtStudName.getText());
            pst.setString(3, txtStudPass.getText());
            pst.setString(4, txtFullName.getText());            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Student Record Successfully Updated");
          RefrashTable();
        } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(LibrarystLoginStud.class.getName()).log(Level.SEVERE, null, ex);
         }        
    }
    private void btnClearActionPerformed(ActionEvent evt) {
        txtIdNo.setText("");  
        txtStudName.setText("");
        txtStudPass.setText("");
        txtFullName.setText("");          
            
        DefaultTableModel RecordTable = (DefaultTableModel) studTable.getModel();
        RecordTable.setRowCount(0);
        RefrashTable();
    }
    private void btnDeleteActionPerformed(ActionEvent evt) {
   
        DefaultTableModel RecordTable = (DefaultTableModel)studTable.getModel();
        int SelectedRows = studTable.getSelectedRow();
        try
        {
         int id;
         id = Integer.parseInt(RecordTable.getValueAt(SelectedRows, 0).toString());
           
         int deleteItem;
            deleteItem = JOptionPane.showConfirmDialog(null,"Confirm if you want to delete?",
            "Warning",JOptionPane.YES_NO_OPTION);
             if (deleteItem ==JOptionPane.YES_OPTION ) 
             {                          
           Class.forName("com.mysql.jdbc.Driver"); 
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
            pst = Conn.prepareStatement("delete from students where studId =?");
                
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Selected Row Deleted");
            RefrashTable();
             }
           
        }
        
        catch (ClassNotFoundException ex) {
   java.util.logging.Logger.getLogger(stud.class.getName()).log(java.util.logging.Level.SEVERE, 
           null, ex);
        } catch (SQLException ex) {
             System.err.println(ex);

        } 
                
                
        }   
    
    
    
    
    
    
    
      @Override
    public void actionPerformed(ActionEvent e) {
   //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main (String [] args) throws SQLException{
       LibrarystLoginStud a = new LibrarystLoginStud("",""); 
    }  
}