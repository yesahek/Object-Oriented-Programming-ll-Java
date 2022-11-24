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
import LMS.LibrarystLoginStud;
import LMS.LibrarystLoginStud;
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

class book {
    private final int id;
final private String name, author,date,fileSize,fileType,subject,bookType,grade;
    
    public book(int id, String name,String author,String date,String fileSize,String fileType,String subject,String bookType,String grade){
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.subject = subject;
        this.bookType = bookType;
        this.grade = grade;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAuthor (){
        return author;
    }
    public String getDate(){
        return date;
    }
    public String getFileSize(){
       return fileSize;
    }
    public String getFileType(){
       return fileType;
    } 
    public String getSubject(){
       return subject;
    }
    public String getBookType(){
        return bookType;
    }
    public String getGrade(){
        return grade;
    }
}

public final class LibrarystLoginBook extends JFrame implements ActionListener{
JFrame frame = new JFrame();
JLabel logo,userName,password,userType,labelbookId,labelbookName,labelAuthor,labelDate,labelFileSize,labelFileType,labelSubject,labelBookType,labelGread;
JLabel Profile,labelFooter,tableTitle;
JTextField txtBookId,txtBookName,txtAuthor,txtDate,txtSubject,txtBookType,txtGread;
JPanel main,topPanel,sidePanel, contentGridPanel, formPanel,tablePanel,footerPanel,contentSide;
JTextField txtfFname, txtfMname, txtfLname,txtFileSize,txtFileType;
JButton btnClear,btnSave,btnDelete,btnUpdate,butLibrarists,butBooks,butStudent,logOut;


JTable table;
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

 public LibrarystLoginBook(String uName,String role) throws SQLException{
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
        Profile= new JLabel("Wellcome "+uName);
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
        
        //form registration area        
        formPanel = new JPanel();
        formPanel.setBorder(new LineBorder(Color.BLACK, 1));
        formPanel.setLayout(null);
        labelbookName= new JLabel("Book Name");
        labelbookName.setBounds(05, 0, 150, 35);
        labelbookName.setFont(new Font(null,Font.PLAIN,18));

        txtBookName = new JTextField(50);
        txtBookName.setBounds(40, 30, 200, 25);
        txtBookName.setFont(new Font("Tahoma", 1, 18));

        labelAuthor = new JLabel("Book Author");
        labelAuthor.setBounds(05, 55, 150, 35);
        labelAuthor.setFont(new Font(null,Font.PLAIN,18));

        txtAuthor = new JTextField(50);
        txtAuthor.setBounds(40, 90, 200, 25);
        txtAuthor.setFont(new Font("Tahoma", 1, 18));

        labelDate = new JLabel("Imp. Data");
        labelDate.setBounds(05, 115, 150, 35);
        labelDate.setFont(new Font(null,Font.PLAIN,18));

        txtDate= new JTextField(50);
        txtDate.setBounds(40, 150, 200, 25);
        txtDate.setFont(new Font("Tahoma", 1, 18));

        labelFileSize = new JLabel("File Size");
        labelFileSize.setBounds(05, 175, 150, 35);
        labelFileSize.setFont(new Font(null,Font.PLAIN,18));

        txtFileSize= new JTextField(50);
        txtFileSize.setBounds(40, 210, 200, 25);
        txtFileSize.setFont(new Font("Tahoma", 1, 18));

        labelFileType = new JLabel("File Type");
        labelFileType.setBounds(05, 235, 150, 35);
        labelFileType.setFont(new Font(null,Font.PLAIN,18));

        txtFileType= new JTextField(50);
        txtFileType.setBounds(40, 270, 200, 25);
        txtFileType.setFont(new Font("Tahoma", 1, 18));

        labelSubject = new JLabel("Subject");
        labelSubject.setBounds(05, 295, 150, 35);
        labelSubject.setFont(new Font(null,Font.PLAIN,18));

        txtSubject = new JTextField(50);
        txtSubject.setBounds(40, 335, 200, 25);
        txtSubject.setFont(new Font("Tahoma", 1, 18));

        labelBookType = new JLabel("Book Type");
        labelBookType.setBounds(05, 355, 150, 35);
        labelBookType.setFont(new Font(null,Font.PLAIN,18));

        txtBookType = new JTextField(50);
        txtBookType.setBounds(40, 390, 200, 25);
        txtBookType.setFont(new Font("Tahoma", 1, 18));

        labelGread = new JLabel("Gread");
        labelGread.setBounds(05, 415, 150, 35);
        labelGread.setFont(new Font(null,Font.PLAIN,18));

        txtGread = new JTextField(50);
        txtGread.setBounds(40, 450, 200, 25);
        txtGread.setFont(new Font("Tahoma", 1, 18));

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(10, 485, 115, 35);
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnUpdate.addActionListener(this);
        btnUpdate.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
             btnUpdadteActionPerformed(evt);
            }                 
        });
        btnSave = new JButton("Add New");
        btnSave.setBounds(140, 485, 115, 35);
        btnSave.addActionListener(this);
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnSave.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            btnSaveActionPerformed(evt);
        }
        });
        btnClear = new JButton("Clear");
        btnClear.setBounds(10, 525, 115, 35);
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnClear.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            btnClearActionPerformed(evt);
        }
          });

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(140, 525, 115, 35);
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnDelete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            btnDeleteActionPerformed(evt);
        }       
          });
 
        formPanel.add(btnUpdate);
        formPanel.add(btnDelete);
        formPanel.add(btnSave);
        formPanel.add(btnClear);
        formPanel.add(txtGread);
        formPanel.add(labelGread);
        formPanel.add(txtBookType);
        formPanel.add(labelBookType);
        formPanel.add(txtSubject);
        formPanel.add(labelSubject);
        formPanel.add(txtFileSize);
        formPanel.add(txtFileType);
        formPanel.add(labelFileType);
        formPanel.add(txtFileSize);
        formPanel.add(labelFileSize);
        formPanel.add(txtDate);
        formPanel.add(labelDate);
        formPanel.add(txtAuthor);
        formPanel.add(labelDate);
        formPanel.add(labelAuthor);
        formPanel.add(txtBookName);
        formPanel.add(labelbookName); 
        main.add(formPanel); 
       //booksTable
    
       
        tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(30,10,10));
        ArrayList<book> list = BookList();
        DefaultTableModel model = new DefaultTableModel();
        Object[] row = new Object[9];
        model.addColumn("Book Id");
        model.addColumn("Book Name");
        model.addColumn("Book Author");
        model.addColumn("Date");
        model.addColumn("File Size");
        model.addColumn("File Type");
        model.addColumn("Subject");
        model.addColumn("Book Type");
        model.addColumn("Grade");
            for (int i = 0;i<list.size();i++){
                row[0]=list.get(i).getId();
                row[1]=list.get(i).getName();
                row[2]=list.get(i).getAuthor();
                row[3]=list.get(i).getDate();
                row[4]=list.get(i).getFileSize();
                row[5]=list.get(i).getFileType();
                row[6]=list.get(i).getSubject();
                row[7]=list.get(i).getBookType();
                row[8]=list.get(i).getGrade();
                model.addRow(row);
                table = new JTable(model);
            }
        RefrashTable();
        table.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableMouseClicked(evt);
            }
        });   
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        sp = new JScrollPane(table);                            
        tablePanel.add(sp);  
        //tablePanel.add(table);  
        tablePanel.setLayout(new GridLayout(1,0,100,0));      
        main.add(tablePanel,BorderLayout.EAST);         
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
    public ArrayList<book> BookList(){
        ArrayList<book> BookList = new ArrayList<>();
            try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
             String query1="SELECT * FROM `books`";
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(query1);
             book u;
             while(rs.next()){
                 u=new book(rs.getInt("bookid"), rs.getString("bookName"),rs.getString("Author"),
                       rs.getString("date"),rs.getString("fileSize"),rs.getString("fileType"),
                       rs.getString("subject"),rs.getString("bookType"),rs.getString("gread"));
                 BookList.add(u);  
             }
            } catch (ClassNotFoundException | SQLException e) {
               JOptionPane.showMessageDialog(null, e);
            }      
    return BookList;
    }    
    public void tableMouseClicked(java.awt.event.MouseEvent e) {
    DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
    int SelectedRows = table.getSelectedRow();
    txtBookName.setText(RecordTable.getValueAt(SelectedRows,1).toString());
    txtAuthor.setText(RecordTable.getValueAt(SelectedRows,2).toString());
    txtDate.setText(RecordTable.getValueAt(SelectedRows,3).toString());
    txtFileSize.setText(RecordTable.getValueAt(SelectedRows,4).toString());
    txtFileType.setText(RecordTable.getValueAt(SelectedRows,5).toString());
    txtSubject.setText(RecordTable.getValueAt(SelectedRows,6).toString());
    txtBookType.setText(RecordTable.getValueAt(SelectedRows,7).toString());
    txtGread.setText(RecordTable.getValueAt(SelectedRows,8).toString());
    }
    public String loginfunction(String uName , String role){
    role = this.role;
    uName = this.uName;

   //uName,String role
    return (userName + role);    
    } 
    public void RefrashTable()
    {
         try
        {
          Class.forName("com.mysql.jdbc.Driver");
          Conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
          pst = Conn.prepareStatement("select * from books");
          rst = pst.executeQuery();
          ResultSetMetaData StData = rst.getMetaData();            
          Q = StData.getColumnCount();            
          DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
          RecordTable.setRowCount(0);
            while(rst.next()){                
                Vector columnData = new Vector();                
                for (i = 1; i <= Q; i++)
                {
                    columnData.add(rst.getString("bookid"));
                    columnData.add(rst.getString("bookName"));
                    columnData.add(rst.getString("Author"));
                    columnData.add(rst.getString("date"));
                    columnData.add(rst.getString("fileSize"));
                    columnData.add(rst.getString("fileType"));
                    columnData.add(rst.getString("subject"));
                    columnData.add(rst.getString("bookType"));
                    columnData.add(rst.getString("gread"));                    
                }
                    RecordTable.addRow(columnData);                
            }         
        }
        catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void logOutActionPerformed(ActionEvent evt) {
      JOptionPane.showMessageDialog(this,"Bye See you Soon");
       frame.dispose();
    }
    private void StudentsActionPerformed(ActionEvent evt) {
    try {
        frame.dispose();
        LibrarystLoginStud a = new LibrarystLoginStud("","");
    } catch (SQLException ex) {
        Logger.getLogger(LibrarystLoginBook.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
     private void BooksActionPerformed(ActionEvent evt) {
         JOptionPane.showMessageDialog(this,"You are Already on Books Tab");
        }
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {                                          
    try
        {
           Class.forName("com.mysql.jdbc.Driver");
           Conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
            pst = Conn.prepareStatement("insert into books (bookName, Author, date, fileSize, fileType, subject, bookType, gread)values(?,?,?,?,?,?,?,?)");            
            pst.setString(1, txtBookName.getText());
            pst.setString(2, txtAuthor.getText());
            pst.setString(3, txtDate.getText());
            pst.setString(4, txtFileSize.getText());
            pst.setString(5, txtFileType.getText());
            pst.setString(6, txtSubject.getText());
            pst.setString(7, txtBookType.getText());
            pst.setString(8, txtGread.getText());            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Book Recorded Successfully");
          RefrashTable();
        } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(LibrarystLoginBook.class.getName()).log(Level.SEVERE, null, ex);
         }        
    }
    private void btnUpdadteActionPerformed(ActionEvent evt) {
         try
        {
          Class.forName("com.mysql.jdbc.Driver");
          int row = table.getSelectedRow();
          String vlaue = (table.getModel().getValueAt(row,0).toString());
          String query ="UPDATE books SET bookName=?, Author=?, date=?, fileSize=?, fileType=?, subject=?, bookType=?, gread=? where bookId="+vlaue;
          stmt = Conn.createStatement();
          pst = Conn.prepareStatement(query);
            pst.setString(1, txtBookName.getText());
            pst.setString(2, txtAuthor.getText());
            pst.setString(3, txtDate.getText());
            pst.setString(4, txtFileSize.getText());
            pst.setString(5, txtFileType.getText());
            pst.setString(6, txtSubject.getText());
            pst.setString(7, txtBookType.getText());
            pst.setString(8, txtGread.getText());            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Book Recorded Successfully");
          RefrashTable();
        } catch (SQLException | ClassNotFoundException ex) {
             Logger.getLogger(LibrarystLoginBook.class.getName()).log(Level.SEVERE, null, ex);
     }        
  }
    private void btnClearActionPerformed(ActionEvent evt) {
          txtBookName.setText("");
          txtAuthor.setText("");
          txtDate.setText("");
          txtFileSize.setText("");
          txtFileType.setText("");
          txtSubject.setText("");
          txtBookType.setText("");
          txtGread.setText("");           
        DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
        RecordTable.setRowCount(0);
        RefrashTable();
    }
    private void btnDeleteActionPerformed(ActionEvent evt) {
   
        DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
        int SelectedRows = table.getSelectedRow();
        try
        {
         int id;
         id = Integer.parseInt(RecordTable.getValueAt(SelectedRows, 0).toString());
           
         int deleteItem;
            deleteItem = JOptionPane.showConfirmDialog(null,"Confirm if you want to delete item",
            "Warning",JOptionPane.YES_NO_OPTION);
             if (deleteItem ==JOptionPane.YES_OPTION ) 
             {                          
           Class.forName("com.mysql.jdbc.Driver"); 
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","");
            pst = Conn.prepareStatement("delete from books where bookId =?");
                
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Selected Row Deleted");
            RefrashTable();
             }           
        }        
        catch (ClassNotFoundException ex) {
   java.util.logging.Logger.getLogger(book.class.getName()).log(java.util.logging.Level.SEVERE, 
           null, ex);
        } catch (SQLException ex) {
             System.err.println(ex);
     }               
   } 
      @Override
    public void actionPerformed(ActionEvent e) {
    }  
}
   
   
    
    
 
   
   
     
   

    
