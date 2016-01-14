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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.InRep_DAO;
import Entity.InRep;
import Entity.PersonnelManage;
import Entity.Pur_App;
import Entity.Rep_App;


@SuppressWarnings("serial")
public class InRepTableTest extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous,add,delete;
 private JLabel label1;
 private InRepTable table;
 private JLabel tip;
 private int LABID;

 public InRepTableTest(int LABID) throws ClassNotFoundException, SQLException{
  this.LABID = LABID;
  initTableData();
  initComponent();

 }
 private void initTableData() {
 
	 InRep_DAO ID=new InRep_DAO();
	 ID.select(LABID);
 }
 private void initComponent() {
  // TODO Auto-generated method stub

  table=new InRepTable();
  panel=new JScrollPane(table);
  panel.setBounds(10, 10, 700, 90);
  previous=new JButton("上一页");
  previous.setBounds(150, 135, 75,20);
  next=new JButton("下一页");
  next.setBounds(255, 135, 75, 20);
  add=new JButton("维修完成");
  add.setBounds(630,105, 85, 25);
  delete=new JButton("删除");
  delete.setBounds(500, 135, 65, 20);
  previous.addActionListener(this);
  next.addActionListener(this);
  add.addActionListener(this);
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
	  if(tip.getText().length()!=0){
	       	tip.setText("");
	       }                     //提示字符串
		  int i=table.getSelectedRow();
	  if(i==-1)return ;
		   Integer id=(Integer) table.getValueAt(i,0);
	  if(id==null)return ;
	  InRep s=null;
	  for(InRep pm:InRep.InReps){
      if(pm.getEquipment_Id()==id)
		   s=pm;
	  }
	  
	  int Lab_Id = s.getLab_Id();
	  int Equipment_Id = s.getEquipment_Id();
	  Timestamp Rep_Start_Date = s.getRep_Start_Date();
      Timestamp Rep_End_Date = new Timestamp(System.currentTimeMillis());
      String Rep_Price = s.getRep_Price();

		   			   
	  int index=InRep.InReps.indexOf(s);
	  InRep.InReps.remove(index);
	  table.initTable();
	  label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
		   
	  try{
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
			      
			  java.sql.PreparedStatement pstmt = null;          //维修完成，从原表中删除
			      String sql = "delete from InRep where Equipment_Id = ?";
			     
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1,id);
			         
			  int line = pstmt.executeUpdate();
			      
			  java.sql.PreparedStatement pstmt2 = null;       //插入记录表
			  String sql2 = "INSERT INTO `equipmentmanagement`.`rep_record` (`Lab_Id`, `Equipment_Id`, `Rep_Start_Date`, `Rep_End_Date`, `Rep_Price`) VALUES (?,?,?,?,?);";
              pstmt2 = conn.prepareStatement(sql2);
			  pstmt2.setInt(1,Lab_Id);
			  pstmt2.setInt(2,Equipment_Id);
			  pstmt2.setTimestamp(3,Rep_Start_Date);
			  pstmt2.setTimestamp(4,Rep_End_Date);
			  pstmt2.setString(5,Rep_Price);
			     
			  line = pstmt2.executeUpdate();
			  
			  java.sql.PreparedStatement pstmt3 = null;   //equipment表中状态置为正常
			  String sql3 = "UPDATE `equipmentmanagement`.`equipment` SET `Eqpm_Sta`='normal' WHERE `Equipment_Id`= ?;";
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
