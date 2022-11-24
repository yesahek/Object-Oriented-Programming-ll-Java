package projectS;

import java.awt.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;



public class Home {

	public Home() throws SQLException {
		// TODO Auto-generated constructor stub
	final JFrame fra=new JFrame();
    JPanel p1=new JPanel(new GridLayout(2,1)); 
    String gender[] = { "Male", "Female"};
    String depa[] = { "Computer Science", "CIVIL","Electrical Enginerign","Mechanical Enginering"};
JButton Referesh = new JButton("Refresh");
    //p1.setBackground(Color.darkGray);
    JPanel ro=new JPanel();
   

    JPanel butn=new JPanel(new GridLayout(1,1));
    butn.setBorder(BorderFactory.createEmptyBorder(0, 350, 0, 0));
  
fra.setResizable(false);
butn.add(Referesh);
    ro.add(butn);
    //ro.add(butn);
ro.add(p1);
    JPanel p2=new JPanel(new GridLayout(12,0));
    //Contain.add(new JScrollPane());
JPanel se=new JPanel();
se.setBorder(BorderFactory.createEmptyBorder(90, 0,230, 0));

JLabel userid=new JLabel("Enter Student ID Number : ");
final JTextField stdid=new JTextField(15);
JButton search = new JButton("Search");
se.add(userid);
se.add(stdid);
se.add(search);
  JPanel rowx=new JPanel( new GridLayout(2,1));
   JPanel compo=new JPanel(new GridLayout(6,2));
   JPanel bu=new JPanel();
   JLabel fir=new JLabel("First Name : ");
   final JTextField firn=new JTextField();

   
   JLabel last=new JLabel("Last Name : ");
   final JTextField lasn=new JTextField();
   JLabel gendrlabl=new JLabel("Gender : ");
   final JTextField Genderfild=new JTextField();
   
   JLabel deprtlab=new JLabel("Department : ");
   final JTextField deprtxt=new JTextField();
   JLabel ssid=new JLabel("ID : ");
   final JTextField sid=new JTextField();
   JButton Delete = new JButton("Delete");
   JButton updt = new JButton("Update");
   lasn.setEnabled(false);
   Genderfild.setEnabled(false);
   deprtxt.setEnabled(false);
   firn.setEnabled(false);
   sid.setEnabled(false);
bu.add(Delete);
bu.add(updt);
   compo.add(fir);
   compo.add(firn);
   compo.add(last);
   compo.add(lasn);
   compo.add(ssid);
   compo.add(sid);
   compo.add(gendrlabl);
   compo.add(Genderfild);
   compo.add(deprtlab);
   compo.add(deprtxt);
   compo.setBorder(BorderFactory.createEmptyBorder(40, 120, 70, 80));
compo.add(bu);
   rowx.add(se);
   rowx.add(compo);
   p2.setBackground(Color.LIGHT_GRAY);
    JPanel p3=new JPanel(new GridLayout());  
    p3.setBackground(Color.LIGHT_GRAY);
  
   
    JPanel p4=new JPanel(new GridLayout(8,0));
    JPanel adm1=new JPanel(new GridLayout(1,1));
    JPanel adm2=new JPanel(new GridLayout(1,0));
    JPanel adm3=new JPanel(new GridLayout(1,0));
    JPanel adm4=new JPanel(new GridLayout(1,0));

    JLabel adminfna=new JLabel("First Name");
    adminfna.setFont(new java.awt.Font("Tahoma", 1, 17));

    final JTextField admfn=new JTextField(37);
   adm1.add(admfn);
   adm1.add(adminfna);
    JLabel admlas=new JLabel("Last Name");
    admlas.setFont(new java.awt.Font("Tahoma", 1, 17));
    final JTextField admln=new JTextField(17);
    adm2.add(admln);

    JLabel adminuna=new JLabel("Usser Name");
    adminfna.setFont(new java.awt.Font("Tahoma", 1, 17));
    final JTextField admuna=new JTextField(17);
    adm3.add(admuna);

    final JTextField admpsw=new JTextField(17);
    JButton add=new JButton("Register");
    final JLabel admpas=new JLabel("Password");
    admlas.setFont(new java.awt.Font("Tahoma", 1, 17));
    admpas.setFont(new java.awt.Font("Tahoma", 1, 17));
    adminuna.setFont(new java.awt.Font("Tahoma", 1, 17));

    adm4.add(admpsw);
  adm1.setBorder(BorderFactory.createEmptyBorder(9, 0, 10, 80));
    adm2.setBorder(BorderFactory.createEmptyBorder(9, 0, 10, 80));

    adm3.setBorder(BorderFactory.createEmptyBorder(9, 0, 10, 80));

    adm4.setBorder(BorderFactory.createEmptyBorder(9, 0, 10, 80));

    p4.add(adminfna);
    p4.add(adm1);
    p4.add(admlas);
    p4.add(adm2);
    p4.add(adminuna);
    p4.add(adm3);
    p4.add(admpas);
    p4.add(adm4);
    p4.add(add);
	fra.setSize(600, 600);
	fra.setTitle("Welcome");
	fra.setVisible(true);
	JTabbedPane tp = new JTabbedPane();
	 tp.setBounds(50,50,200,200);
	 tp.add("Students List",ro); 
	 tp.add("Add new Students",p2);  
	    tp.add("Update Student Info",rowx); 
	    tp.add("Add New Admin",p4);
	    tp.add("Exit",p4);
	    fra.add(tp);
	    
	    
	    tp.addChangeListener(new ChangeListener() {

			// This method is called whenever the selected tab changes

			public void stateChanged(ChangeEvent evt) {

			    JTabbedPane tp = (JTabbedPane)evt.getSource();

			    

			    // Get current tab

			    int tab = tp.getSelectedIndex();
if(tab==3){
	 fra.dispose();
     NewMain nmain=new NewMain();

}
			}

			

		    });
	    
	    
	    
	    
	    
	    
	    
	    DefaultTableModel defaultTableModel = new DefaultTableModel();
        JTable table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(450, 200));
        table.setEnabled(false);
        p1.add(new JScrollPane(table));
        defaultTableModel.addColumn("FirstName");
        defaultTableModel.addColumn("LastName");
        defaultTableModel.addColumn("Gender");
        defaultTableModel.addColumn("Department");
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Update");
	    try{
			   Class.forName("com.mysql.jdbc.Driver");
			   String conURL = "jdbc:mysql://localhost/user?user=root&password=";
			    java.sql.Connection con = DriverManager.getConnection(conURL);
			       //MyConnection();
			         // PreparedStatement ps;
			           //ResultSet rs;
			          
			           
			        //   String query = "SELECT * FROM user WHERE test = '1'";
			        java.sql.Statement stmt = con.createStatement();
			   String sql = "SELECT * FROM students_info ";
			   stmt.execute(sql);
			   ResultSet rs = stmt.executeQuery(sql);
			           
			    
			    
			            // java.sql.Statement stmt = con.createStatement();
			               

			               
			              // rs = ps.executeQuery();
			               
			   while(rs.next())
			   {
				   //JButton b=new JButton("Edit");
			       String d = rs.getString("First_Name");
			       String e = rs.getString("Last_Name");
			       String f = rs.getString("Gender");
			       String g = rs.getString("Department");
			       String h = rs.getString("Stud_ID");
			       defaultTableModel.addRow(new Object[]{d, e, f,g,h,new JButton("Button1")});
			   }
			               
			   
			           }catch (ClassNotFoundException ex) {

			           }
	    JPanel heading=new JPanel(new GridLayout(1,0));
	    heading.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
	    JPanel Row2=new JPanel(new GridLayout(1,0));
	    Row2.setBackground(Color.LIGHT_GRAY);
	    JPanel Row3=new JPanel(new GridLayout(1,0));
	    Row3.setBackground(Color.LIGHT_GRAY);
	    JPanel Row4=new JPanel(new GridLayout(1,0));
	    Row4.setBackground(Color.LIGHT_GRAY);
	    
	    JPanel Row5=new JPanel(new GridLayout(1,0));
	    Row5.setBackground(Color.LIGHT_GRAY);
	    JPanel Row6=new JPanel(new GridLayout(1,0));
	    Row6.setBackground(Color.LIGHT_GRAY);
	    
	    JLabel studreg=new JLabel("Students Registration Form");
	    studreg.setForeground(Color.black);
	    studreg.setFont(new java.awt.Font("Tahoma", 1, 24));
	    	heading.setBackground(Color.LIGHT_GRAY);
	    	heading.add(studreg);
	    JLabel stufna=new JLabel("First Name");
	    stufna.setFont(new java.awt.Font("Tahoma", 1, 17));

	    final JTextField fn=new JTextField();
	    Row2.add(fn);
	    Row2.setBorder(BorderFactory.createEmptyBorder(9, 80, 0, 80));

	    JLabel stulna=new JLabel("Last Name");
	    stulna.setFont(new java.awt.Font("Tahoma", 1, 17));

	    final JTextField lastn=new JTextField();
	    Row3.add(lastn);
	    Row3.setBorder(BorderFactory.createEmptyBorder(9, 80, 0, 80));

	    JLabel stusex=new JLabel("Gender");
	    stusex.setFont(new java.awt.Font("Tahoma", 1, 17));

	    @SuppressWarnings("unchecked")
		final
		JComboBox gend=new JComboBox(gender);
	    Row4.add(gend);
	    Row4.setBorder(BorderFactory.createEmptyBorder(9, 80, 0, 80));

	    JLabel studid=new JLabel("Students ID Number");
	    studid.setFont(new java.awt.Font("Tahoma", 1, 17));

	    final JTextField stunum=new JTextField();
	    Row5.add(stunum);
	    Row5.setBorder(BorderFactory.createEmptyBorder(9, 80, 0, 80));

	    JLabel stugrd=new JLabel("Department");
	    stugrd.setFont(new java.awt.Font("Tahoma", 1, 17));

		final JComboBox dep=new JComboBox(depa);
		Row6.add(dep);
	    Row6.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 80));
		JPanel but=new JPanel(new GridLayout(1,2,15,7));
		but.setBorder(BorderFactory.createEmptyBorder(0, 90, 0, 90));
		but.setBackground(Color.LIGHT_GRAY);
			JButton Register =new JButton("Register");
			JButton Reset =new JButton("Reset");
			but.add(Register);
			but.add(Reset);

			Register.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 80));

	    p2.add(heading);

	    p2.add(stufna);

	    p2.add(Row2);

	    p2.add(stulna);

	    p2.add(Row3);

	    p2.add(stusex);
	    p2.add(Row4);
	    p2.add(studid);

	    p2.add(Row5);
	    p2.add(stugrd);

	    p2.add(Row6);
	    p2.add(but);

	    Referesh.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	
try {
	fra.dispose();
	new Home();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	        	
	        }
	    });
	    Register.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	String first=fn.getText().toString();
	        	String last=lastn.getText().toString();
	        	String id=stunum.getText().toString();
	        	String Gender = gend.getSelectedItem().toString();
	        	String depart = dep.getSelectedItem().toString();

	        	try {
	        		Class.forName("com.mysql.jdbc.Driver");
	 			   String conURL = "jdbc:mysql://localhost/user?user=root&password=";
	 			    java.sql.Connection con = DriverManager.getConnection(conURL);
	 			   java.sql.Statement st = con.createStatement(); 
	 	            st.executeUpdate("INSERT INTO students_info " + 
	 	                "VALUES ('"+first+"', '"+last+"', '"+Gender+"', '"+id+"','"+depart+"')");
	 	           con.close();
	                  JOptionPane.showMessageDialog(null, "Student Information Registerd Successfuly", "Registration Success", 1);
	                  fn.setText("");
	                  lastn.setText("");
	                  stunum.setText("");
	                  
	        	} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        }
	    });
	    
	    
	    search.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	
try {
	
	String studid=stdid.getText().toString();
	Class.forName("com.mysql.jdbc.Driver");
	   String conURL = "jdbc:mysql://localhost/user?user=root&password=";
	    java.sql.Connection con = DriverManager.getConnection(conURL);
	       //MyConnection();
	         // PreparedStatement ps;
	           //ResultSet rs;
	          
	           
	        //   String query = "SELECT * FROM user WHERE test = '1'";
	        java.sql.Statement stmt = con.createStatement();
	   String sql = "SELECT * FROM students_info where Stud_ID='"+studid+"'";
	   stmt.execute(sql);
	   ResultSet rs = stmt.executeQuery(sql);
	           
	    
	    
	            // java.sql.Statement stmt = con.createStatement();
	               

	               
	              // rs = ps.executeQuery();
	               
	   while(rs.next())
	   {
		   //JButton b=new JButton("Edit");
	       String d = rs.getString("First_Name");
	       String e = rs.getString("Last_Name");
	       String f = rs.getString("Gender");
	       String g = rs.getString("Department");
	       String h = rs.getString("Stud_ID");

	       lasn.setEnabled(true);
	       Genderfild.setEnabled(true);
	       deprtxt.setEnabled(true);
	       firn.setEnabled(true);
	       sid.setEnabled(true);

	       lasn.setText(e);
	       firn.setText(d);
	       Genderfild.setText(f);
	       deprtxt.setText(g);
	       sid.setText(h);
	   }
} catch (Exception e) {
	// TODO Auto-generated catch block
	
	e.printStackTrace();
}
	        	
	        }
	    });
	    
	    
	    updt.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	
try {
	String nlas= lasn.getText();
     String nfnam=firn.getText();
     String gnd=Genderfild.getText();
     String depar=deprtxt.getText();
     String studid=sid.getText().toString();
	Class.forName("com.mysql.jdbc.Driver");
	   String conURL = "jdbc:mysql://localhost/user?user=root&password=";
	    java.sql.Connection con = DriverManager.getConnection(conURL);
	   java.sql.Statement st = con.createStatement(); 
      st.executeUpdate("update students_info set First_Name='"+nfnam+"' , Last_Name='"+nlas+"' , Gender='"+gnd+"' , Department='"+depar+"' ,Stud_ID='"+studid+"' where Stud_ID='"+studid+"'");
     con.close();
       JOptionPane.showMessageDialog(null, "Student Information Updated Successfuly", "Updated Success", 1);
       
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	        	
	        }
	    });
	  
	  Delete.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	
try {

     String studid=sid.getText().toString();
	Class.forName("com.mysql.jdbc.Driver");
	   String conURL = "jdbc:mysql://localhost/user?user=root&password=";
	    java.sql.Connection con = DriverManager.getConnection(conURL);
	   java.sql.Statement st = con.createStatement(); 
      st.executeUpdate("Delete from students_info  where Stud_ID='"+studid+"'");
     con.close();
       JOptionPane.showMessageDialog(null, "Student Information Deleted Successfuly", "Updated Success", 1);
       
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	        	
	        }
	    });
	  
	  
	  
	  add.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	String first=admfn.getText().toString();
	        	String last=admln.getText().toString();
	        	String id=admuna.getText().toString();
	        	String Gender = admpsw.getText().toString();
	        	
try {

   String studid=sid.getText().toString();
	Class.forName("com.mysql.jdbc.Driver");
	   String conURL = "jdbc:mysql://localhost/user?user=root&password=";
	    java.sql.Connection con = DriverManager.getConnection(conURL);
	   java.sql.Statement st = con.createStatement(); 
	   st.executeUpdate("INSERT INTO useraccount " + 
                "VALUES ('"+id+"', '"+Gender+"', '"+first+"', '"+last+"')");
            con.close();
     JOptionPane.showMessageDialog(null, "Admin Successfuly Creeated", "Created Success", 1);
     
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	        	
	        }
	    });
	}
}
	


