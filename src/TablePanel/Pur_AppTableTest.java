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

import DAO.Pur_App_DAO;
import Entity.PersonnelManage;
import Entity.Pur_App;


@SuppressWarnings("serial")
public class Pur_AppTableTest extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous,add,delete;
 private JLabel label1;
 private Pur_AppTable table;
 private JLabel tip;
 private int LABID;

 public Pur_AppTableTest(int LABID) throws ClassNotFoundException, SQLException{
  this.LABID = LABID;
  initTableData();
  initComponent();

 }
 private void initTableData() {
  // TODO Auto-generated method stub
	 

	 Pur_App_DAO PAD=new Pur_App_DAO();
	 PAD.select(LABID);
 }
 private void initComponent() {
  // TODO Auto-generated method stub
	 table=new Pur_AppTable();
	  panel=new JScrollPane(table);
	  panel.setBounds(10, 10, 700, 170);
	  previous=new JButton("上一页");
	  previous.setBounds(150, 215, 75,20);
	  next=new JButton("下一页");
	  next.setBounds(255, 215, 75, 20);
	  add=new JButton("签收");
	  add.setBounds(630,185, 75, 25);
	  delete=new JButton("删除");
	  delete.setBounds(500, 215, 65, 20);
	  previous.addActionListener(this);
	  next.addActionListener(this);
	  add.addActionListener(this);
	  delete.addActionListener(this);
	  label1=new JLabel("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
	  label1.setBounds(10, 215, 130, 20);
	 

	  tip = new JLabel("");
	  tip.setBounds(280,185,150,20);	 
	  this.setLayout(null);
	  this.add(panel);
	  this.add(previous);
	  this.add(next);
	  this.add(add);
	  this.add(delete);
	  this.add(label1);

	  this.add(tip);
	  table.setOpaque(false);

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
	 Pur_App s=null;
	 for(Pur_App pm:Pur_App.Pur_Apps){
	 if(pm.getPur_app_id()==id)
	   s=pm;
	 }
	 int index=Pur_App.Pur_Apps.indexOf(s);
	 Pur_App.Pur_Apps.remove(index);
	 table.initTable();
	 label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
	

	 
	 try{
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
		      
		      //签收后删除原记录
		      java.sql.PreparedStatement pstmt = null;
		      String sql = "delete from pur_app where Pur_App_Id = ?";
		     
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
	  if(tip.getText().length()!=0){
       	tip.setText("");
       }                     //提示字符串
	  int i=table.getSelectedRow();
	   if(i==-1)return ;
	   Integer id=(Integer) table.getValueAt(i,0);
	   if(id==null)return ;
	   Pur_App s=null;
	   for(Pur_App pm:Pur_App.Pur_Apps){
	    if(pm.getPur_app_id()==id)
	     s=pm;
	   }
	   int pur_App_Number = s.getPur_app_number();
	   String pur_app_price = s.getPur_app_price();
	   Timestamp pur_app_date = new Timestamp(System.currentTimeMillis());
	   String pur_app_factory = s.getPur_app_factory();
	   String pur_app_model = s.getPur_app_model();
	   int Lab_Id = s.getLab_id();
	   
	     
	   if(!s.getPur_app_status().equals("approval")){
		   tip.setText("申请暂未通过！");
		   return;                               //如果没有申请通过，则无法完成采购
		   
	   }
		   
	   int index=Pur_App.Pur_Apps.indexOf(s);
	   Pur_App.Pur_Apps.remove(index);
	   table.initTable();
	   label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
	   
	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
		      
		      java.sql.PreparedStatement pstmt = null;
		      String sql = "delete from pur_app where Pur_App_Id = ?";
		     
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1,id);
		         
		      int line = pstmt.executeUpdate();
		      
		      java.sql.PreparedStatement pstmt2 = null;
		      String sql2 = "INSERT INTO `equipmentmanagement`.`pur_record` (`Pur_Number`, `Pur_Price`, `Pur_Finish_Date`, `Pur_Factory`, `Pur_Model`, `Lab_Id`) VALUES (?,?,?,?,?,?);";
		      pstmt2 = conn.prepareStatement(sql2);
		      pstmt2.setInt(1,pur_App_Number);
		      pstmt2.setString(2,pur_app_price);
		      pstmt2.setTimestamp(3,pur_app_date);
		      pstmt2.setString(4,pur_app_factory);
		      pstmt2.setString(5,pur_app_model);
		      pstmt2.setInt(6, Lab_Id);
		     
		      line = pstmt2.executeUpdate();
		      
		      
		     //签收后实验设备增加
		      
		      java.sql.PreparedStatement pstmt3 = null;
		      String sql3 = "INSERT INTO `equipmentmanagement`.`equipment` (`Lab_id`, `Factory`, `Model`, `Pur_Date`, `Eqpm_Sta`) VALUES (?,?,?,?,?)";

		     
		      pstmt3 = conn.prepareStatement(sql3);
		      pstmt3.setInt(1,Lab_Id);
		      pstmt3.setString(2,pur_app_factory);
		      pstmt3.setString(3, pur_app_model);
		      pstmt3.setTimestamp(4,pur_app_date);
		      pstmt3.setString(5,"normal");
		    
		         
		      line = pstmt3.executeUpdate();
		      
		      
		      conn.close(); // 关闭连接
		      //rs.close(); // 关闭查询
		     } catch (Exception ex) {
		      System.err.println("Exception:" + ex.getMessage());
		     }
	   
	   
	   
	   return;
  }
  DefaultTableModel model=new DefaultTableModel(table.getPageData(),table.columnNames);
  table.setModel(model);
  label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
 }

}
