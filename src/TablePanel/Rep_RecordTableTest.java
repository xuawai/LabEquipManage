package TablePanel;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.Rep_DAO;
import Entity.PersonnelManage;
import Entity.Rep_App;
import Entity.Rep_Record;


@SuppressWarnings("serial")
public class Rep_RecordTableTest extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous,add,delete;
 private JLabel label1;
 private Rep_RecordTable table;
 private JTextField t1,t2,t3,t4;
 private JLabel l1,l2,l3,l4;
 private JLabel tip;
 private int LABID;

 public Rep_RecordTableTest(int LABID) throws ClassNotFoundException, SQLException{
  this.LABID = LABID;
  initTableData();
  initComponent();

 }
 private void initTableData() {
  // TODO Auto-generated method stub
	 
//     String[] number = null;
//     int[] Rep_Record_Id = null;
//     int[] Lab_Id = null;
//     int[] Equipment_Id = null;
//     Timestamp[] Rep_Start_Date = null;
//     Timestamp[] Rep_End_Date = null;
//     String[] Rep_Price = null;
//     int result = 0;
//     try{
//      Class.forName("com.mysql.jdbc.Driver");
//      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
//      
//     
//      String sql = "select * from rep_record";
//     
//      ResultSet rs = conn.createStatement().executeQuery(sql);
//      
//      // 取得行数
//      if (rs.last()){
//       result = rs.getRow();
//       rs.beforeFirst(); // 指针回滚
//      }
//     
//      Rep_Record_Id = new int[result];
//      Lab_Id = new int[result];
//      Equipment_Id = new int[result];
//      Rep_Start_Date = new Timestamp[result];
//      Rep_End_Date = new Timestamp[result];
//      Rep_Price = new String[result];
//      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
//      rs.next();
//      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
//       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
//      }
//      int i = 0;
//      rs.beforeFirst();
//      while (rs.next()) {
//    	  Rep_Record_Id[i]=rs.getInt(1);
//    	  Lab_Id[i]=rs.getInt(2);
//    	  Equipment_Id[i]=rs.getInt(3);
//    	  Rep_Start_Date[i]=rs.getTimestamp(4);
//    	  Rep_End_Date[i]=rs.getTimestamp(5);
//    	  Rep_Price[i]=rs.getString(6);
//     
//       i++;
//      }
//      conn.close(); // 关闭连接
//      rs.close(); // 关闭查询
//     } catch (Exception ex) {
//      System.err.println("Exception:" + ex.getMessage());
//     }
//     
//     if( Rep_Record.Rep_Records.size()!=0){
//    	 Rep_Record.Rep_Records.clear();
//     }
//    	 
//     for(int i=0;i<result;i++){
//    	 Rep_Record s = new Rep_Record(Rep_Record_Id[i],Lab_Id[i],Equipment_Id[i],Rep_Start_Date[i],Rep_End_Date[i],Rep_Price[i]);
//    	 Rep_Record.Rep_Records.add(s);
//     }
	 Rep_DAO RD=new Rep_DAO();
	 RD.select(LABID);
 }
 private void initComponent() {
  // TODO Auto-generated method stub

  table=new Rep_RecordTable();
  panel=new JScrollPane(table);
  panel.setBounds(10, 10, 700, 90);
  previous=new JButton("上一页");
  previous.setBounds(150, 135, 75,20);
  next=new JButton("下一页");
  next.setBounds(255, 135, 75, 20);
  add=new JButton("添加");
  add.setBounds(630,105, 75, 25);
  delete=new JButton("删除");
  delete.setBounds(500, 135, 65, 20);
  previous.addActionListener(this);
  next.addActionListener(this);
  add.addActionListener(this);
  delete.addActionListener(this);
  label1=new JLabel("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
  label1.setBounds(10, 135, 130, 20);
 
  l1 = new JLabel("姓名");
  l1.setBounds(110, 105, 50, 25);
  l2 = new JLabel("密码");
  l2.setBounds(230,105,50,25);
  l3 = new JLabel("权限");
  l3.setBounds(350,105,50,25);
  l4 = new JLabel("实验室");
  l4.setBounds(470,105,50,25);
  

  t1 = new JTextField("");
  t1.setBounds(140,105,80,25);
  t2 = new JTextField("");
  t2.setBounds(260,105,80,25);
  t3 = new JTextField("");
  t3.setBounds(380,105,80,25);
  t4 = new JTextField("");
  t4.setBounds(520,430,80,25);
  tip = new JLabel("tip");
  tip.setBounds(250,500,150,20);
  this.setLayout(null);
  this.add(panel);
  this.add(previous);
  this.add(next);
  this.add(add);
  this.add(delete);
  this.add(label1);
  this.add(t1);
  this.add(t2);
  this.add(t3);
  this.add(t4);
  this.add(l1);
  this.add(l2);
  this.add(l3);
  this.add(l4);
  this.add(tip);
  table.setOpaque(false);
  t1.setOpaque(false);
  t2.setOpaque(false);
  t3.setOpaque(false);
  t4.setOpaque(false);
  tip.setOpaque(false);
  tip.setFont(new java.awt.Font("Dialog",1,15)); 
  tip.setForeground(Color.red);
  this.setVisible(true);
 }
 /**
  * 按钮事件
  */
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
  JButton button=(JButton) e.getSource();
  if(button.equals(previous)){
   int i=table.getPreviousPage();
   if(i==-1)return;
  }
  if(button.equals(next)){
   int i=table.getNextPage();
   if(i==-1)return;
  }
  if(button.equals(delete)){
   int i=table.getSelectedRow();
   if(i==-1)return ;
   Integer id=(Integer) table.getValueAt(i,0);
   if(id==null)return ;
   PersonnelManage s=null;
   for(PersonnelManage pm:PersonnelManage.PersonnelManages){
    if(pm.getId()==id)
     s=pm;
   }
   int index=PersonnelManage.PersonnelManages.indexOf(s);
   PersonnelManage.PersonnelManages.remove(index);
   table.initTable();
   label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
   
   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqltest", "root", "");
	      
	      java.sql.PreparedStatement pstmt = null;
	      String sql = "delete from manager where ID = ?";
	     
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1,id);
	         
	      int line = pstmt.executeUpdate();
	      
	     
	      conn.close(); // 关闭连接
	      //rs.close(); // 关闭查询
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
   
   return;
  }
  if(button.equals(add)){
	    int ID = 0;
	    String name=null;
    	String psd=null;
    	int level=0;
    	int LabID=0;
    	if(t1.getText().length()!=0){
        	name = t1.getText().toString();
        }
    	
    	else{
    		tip.setText("请输入用户名");
    	 }
    	if(t2.getText().length()!=0){
        	psd = t2.getText().toString();
        }
    	
    	else{
    		tip.setText("请输入密码");
    	 }
    	if(t3.getText().length()!=0){
        	level = Integer.parseInt(t3.getText().toString());
        }
    	
    	else{
    		tip.setText("请输入权限");
    	 }
    	if(t4.getText().length()!=0){
        	LabID = Integer.parseInt(t4.getText().toString());
        }
    	
    	else{
    		tip.setText("请输入实验室");
    	 }
	  
	  if(name!=null&&psd!=null&&level!=0&&LabID!=0){
	  try{
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqltest", "root", "");
	      
	     
	      java.sql.PreparedStatement pstmt = null;
	      String sql = "insert into manager(name,password,level,LabID) values (?,?,?,?)";
	     
	      pstmt = conn.prepareStatement(sql);		      		         
	     

	      
	      //pstmt.setInt(1,ID);
	      pstmt.setString(1,name);
	      pstmt.setString(2,psd);
	      pstmt.setInt(3,level);
	      pstmt.setInt(4,LabID);
	      
	      
	     
		  pstmt.executeUpdate();
	      
		  sql = "select ID from manager where name = ? and password = ?";
		  pstmt = conn.prepareStatement(sql);
		  pstmt.setString(1,name);
	      pstmt.setString(2,psd);
	      ResultSet rs = pstmt.executeQuery();
	      if(rs.next())
	    	  ID = rs.getInt(1);
		  
	      
	      rs.close();
	      conn.close(); // 关闭连接
	      
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }  
	  }
	  
   PersonnelManage personnelManage=new PersonnelManage(ID,name,psd,level,LabID);
   PersonnelManage.PersonnelManages.add(personnelManage);
   table.initTable();
   label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
   return;
  }
  DefaultTableModel model=new DefaultTableModel(table.getPageData(),table.columnNames);
  table.setModel(model);
  label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
 }

}
