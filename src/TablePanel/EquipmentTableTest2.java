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

import DAO.Equipment_DAO;
import Entity.Equipment;
import Entity.PersonnelManage;
import Entity.Pur_Record;
import Entity.Rep_Record;


@SuppressWarnings("serial")
public class EquipmentTableTest2 extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous;
 private JLabel label1;
 private EquipmentTable table;
 private JLabel tip;
 private int LABID;

 public EquipmentTableTest2(int LABID) throws ClassNotFoundException, SQLException{
  this.LABID = LABID;
  initTableData();
  initComponent();

 }
 private void initTableData() {
  // TODO Auto-generated method stub
	 
//     String[] number = null;
//     int[] EquipmentID = null;
//     int[] LabID = null;
//     String[] Factory = null;
//     String[] Model = null;
//     Timestamp[] PurchaseDate = null;
//     String[] EquipmentStatus = null;
//     int result = 0;
//     try{
//      Class.forName("com.mysql.jdbc.Driver");
//      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
//      
//     
//      String sql = "select * from equipment";
//     
//      ResultSet rs = conn.createStatement().executeQuery(sql);
//      
//      // 取得行数
//      if (rs.last()){
//       result = rs.getRow();
//       rs.beforeFirst(); // 指针回滚
//      }
//     
//      EquipmentID = new int[result];
//      LabID = new int[result];
//      Factory = new String[result];
//      Model = new String[result];
//      PurchaseDate = new Timestamp[result];
//      EquipmentStatus = new String[result];
//      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
//      rs.next();
//      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
//       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
//      }
//      int i = 0;
//      rs.beforeFirst();
//      while (rs.next()) {
//    	  EquipmentID[i]=rs.getInt(1);
//    	  LabID[i]=rs.getInt(2);
//    	  Factory[i]=rs.getString(3);
//    	  Model[i]=rs.getString(4);
//    	  PurchaseDate[i]=rs.getTimestamp(5);
//    	  EquipmentStatus[i]=rs.getString(6);
//     
//       i++;
//      }
//      conn.close(); // 关闭连接
//      rs.close(); // 关闭查询
//     } catch (Exception ex) {
//      System.err.println("Exception:" + ex.getMessage());
//     }
//     
//     if( Equipment.Equipments.size()!=0){
//    	 Equipment.Equipments.clear();
//     }
//     
//     for(int i=0;i<result;i++){
//    	 Equipment s = new Equipment(EquipmentID[i],LabID[i],Factory[i],Model[i],PurchaseDate[i],EquipmentStatus[i]);
//    	 Equipment.Equipments.add(s);
//     }
	 Equipment_DAO ED=new Equipment_DAO();
	 ED.select(LABID);
 }
 private void initComponent() {
  // TODO Auto-generated method stub
  this.setSize(800,600);
  table=new EquipmentTable();
  panel=new JScrollPane(table);
  panel.setBounds(10, 10, 700, 345);
  previous=new JButton("上一页");
  previous.setBounds(150, 475, 75,20);
  next=new JButton("下一页");
  next.setBounds(255, 475, 75, 20);
  previous.addActionListener(this);
  next.addActionListener(this);
  label1=new JLabel("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
  label1.setBounds(10, 475, 130, 20);
 

  
  tip = new JLabel("");
  tip.setBounds(280,410,150,20);
  this.setLayout(null);
  this.add(panel);
  this.add(previous);
  this.add(next);
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
  DefaultTableModel model=new DefaultTableModel(table.getPageData(),table.columnNames);
  table.setModel(model);
  label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
 }

}
