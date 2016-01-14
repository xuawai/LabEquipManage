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

import DAO.Rep_App_DAO;
import Entity.PersonnelManage;
import Entity.Pur_App;
import Entity.Rep_App;


@SuppressWarnings("serial")
public class Rep_AppTableTest2 extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous,add,delete,refuse;
 private JLabel label1;
 private Rep_AppTable table;
 private JLabel tip;
 private int LABID;

 public Rep_AppTableTest2(int LABID) throws ClassNotFoundException, SQLException{
  this.LABID = LABID;
  initTableData();
  initComponent();

 }
 private void initTableData() {
  
	 Rep_App_DAO RAD=new Rep_App_DAO();
	 RAD.select(LABID);
 }
 private void initComponent() {
  // TODO Auto-generated method stub
	  table=new Rep_AppTable();
	  panel=new JScrollPane(table);
	  panel.setBounds(10, 10, 700, 90);
	  previous=new JButton("上一页");
	  previous.setBounds(150, 135, 75,20);
	  next=new JButton("下一页");
	  next.setBounds(255, 135, 75, 20);
	  add=new JButton("同意");
	  add.setBounds(530,105, 75, 25);
	  refuse=new JButton("拒绝");
	  refuse.setBounds(630,105, 75, 25);
	  delete=new JButton("删除");
	  delete.setBounds(500, 135, 65, 20);
	  previous.addActionListener(this);
	  next.addActionListener(this);
	  add.addActionListener(this);
	  refuse.addActionListener(this);
	  delete.addActionListener(this);
	  label1=new JLabel("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
	  label1.setBounds(10, 135, 130, 20);
	 

	  tip = new JLabel("");
	  tip.setBounds(280,105,150,20);
	  this.setLayout(null);
	  this.add(panel);
	  this.add(previous);
	  this.add(next);
	  this.add(add);
	  this.add(delete);
	  this.add(refuse);
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
	  if(tip.getText().length()!=0){			//提示字符串
	       	tip.setText("");
	       } 
	  int i=table.getSelectedRow();
	   if(i==-1)return ;
	   Integer id=(Integer) table.getValueAt(i,0);
	   if(id==null)return ;
	   Rep_App s=null;
	   for(Rep_App pm:Rep_App.Rep_Apps){
	    if(pm.getRep_App_Id()==id)
	     s=pm;
	   }

	   
	   if(s.getRep_App_Status().equals("approval")){
		  tip.setText("已同意申请");
		   return;                               //如果申请通过，则无法再次通过
		   
	   }
	   
	   int Equipment_Id = s.getEquipment_Id();
	   int Lab_Id = s.getLab_Id();
	   Timestamp Rep_Start_Date = new Timestamp(System.currentTimeMillis());
	   String Rep_Price = s.getRep_App_Price();
	   
	   s.setRep_App_Status("approval");
	   table.initTable();
	   label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
	   
	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
		      //同意申请
		      java.sql.PreparedStatement pstmt = null;
		      String sql = "UPDATE `equipmentmanagement`.`rep_app` SET `Rep_App_Status`='approval' WHERE `Rep_App_Id`= ?;";
		     
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1,id);
		         
		      int line = pstmt.executeUpdate();
		      
		      //进入维修状态
		      java.sql.PreparedStatement pstmt2 = null;
		      String sql2 = "INSERT INTO `equipmentmanagement`.`inrep` (`Equipment_Id`, `Lab_Id`, `Rep_Start_Date`, `Rep_Price`) VALUES (?,?,?,?);";
		     
		      pstmt2 = conn.prepareStatement(sql2);
		      pstmt2.setInt(1,Equipment_Id);
		      pstmt2.setInt(2, Lab_Id);
		      pstmt2.setTimestamp(3, Rep_Start_Date);
		      pstmt2.setString(4,Rep_Price);
		         
		      line = pstmt2.executeUpdate();
		      
		      
		      //equipment表更改设备状态
		      java.sql.PreparedStatement pstmt3 = null;
		      String sql3 = "UPDATE `equipmentmanagement`.`equipment` SET `Eqpm_Sta`='in repair' WHERE `Equipment_Id`=?;";
		     
		      pstmt3 = conn.prepareStatement(sql3);
		      pstmt3.setInt(1,Equipment_Id);
		      
		         
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
