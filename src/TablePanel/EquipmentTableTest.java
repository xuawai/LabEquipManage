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
public class EquipmentTableTest extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous,add,delete,scrap;
 private JLabel label1;
 private EquipmentTable table;
 private JTextField tPrice;      //ά�޼۸�
 private JLabel lPrice;				//ά�޼۸�
 private JTextField tScrap;      //����ԭ��
 private JLabel lScrap;				//����ԭ��
 private JLabel tip;
 private int LABID;
 

 public EquipmentTableTest(int LABID) throws ClassNotFoundException, SQLException{
  this.LABID = LABID;
  initTableData();
  initComponent();

 }
 private void initTableData() {
  // TODO Auto-generated method stub

	 Equipment_DAO ED=new Equipment_DAO();
	 ED.select(LABID);
 }
 private void initComponent() {
  // TODO Auto-generated method stub
  this.setSize(800,600);
  table=new EquipmentTable();
  panel=new JScrollPane(table);
  panel.setBounds(10, 10, 700, 345);
  previous=new JButton("��һҳ");
  previous.setBounds(150, 475, 75,20);
  next=new JButton("��һҳ");
  next.setBounds(255, 475, 75, 20);
  add=new JButton("�豸ά��");
  add.setBounds(620, 430, 90, 25);
  scrap=new JButton("�豸����");
  scrap.setBounds(620, 400, 90, 25);
  delete=new JButton("ɾ��");
  delete.setBounds(500, 475, 65, 20);
  previous.addActionListener(this);
  next.addActionListener(this);
  add.addActionListener(this);
  scrap.addActionListener(this);
  delete.addActionListener(this);
  label1=new JLabel("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
  label1.setBounds(10, 475, 130, 20);
 

  lPrice = new JLabel("ά�޼۸�");
  lPrice.setBounds(460,430,55,25);
  
  tPrice = new JTextField("");
  tPrice.setBounds(520,430,80,25);
  
  lScrap = new JLabel("����ԭ��");
  lScrap.setBounds(460,400,55,25);
  
  tScrap = new JTextField("");
  tScrap.setBounds(520,400,80,25);
  
  tip = new JLabel("");
  tip.setBounds(280,410,150,20);
  this.setLayout(null);
  this.add(panel);
  this.add(previous);
  this.add(next);
  this.add(add);
  this.add(scrap);
  this.add(delete);
  this.add(label1);
  this.add(lPrice);
  this.add(tPrice);
  this.add(lScrap);
  this.add(tScrap);
  this.add(tip);
  table.setOpaque(false);
  tPrice.setOpaque(false);
  tScrap.setOpaque(false);
  tip.setOpaque(false);
  tip.setFont(new java.awt.Font("Dialog",1,15)); 
  tip.setForeground(Color.red);
  this.setVisible(true);
 }
 /**
  * ��ť�¼�
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
   Equipment s=null;
   for(Equipment pm:Equipment.Equipments){
    if(pm.getEquipmentID()==id)
     s=pm;
   }
   int index=Equipment.Equipments.indexOf(s);
   Equipment.Equipments.remove(index);
   table.initTable();
   label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
   
   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqltest", "root", "");
	      
	      java.sql.PreparedStatement pstmt = null;
	      String sql = "delete from manager where ID = ?";
	     
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1,id);
	         
	      int line = pstmt.executeUpdate();
	      
	     
	      conn.close(); // �ر�����
	      //rs.close(); // �رղ�ѯ
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
   
   return;
  }
  if(button.equals(add)){
	   if(tip.getText().length()!=0){
	       	tip.setText("");
	       }                     //��ʾ�ַ���
	   int i=table.getSelectedRow();
	   if(i==-1)return ;
	   Integer id=(Integer) table.getValueAt(i,0);
	   if(id==null)return ;
	   Equipment s=null;
	   for(Equipment pm:Equipment.Equipments){
	    if(pm.getEquipmentID()==id)
	     s=pm;
	   }
	   
	   String price;
       if(tPrice.getText().length()!=0){
    	   price = tPrice.getText().toString();
          }
       else{
    	   tip.setText("����дά�޽��");
    	   return;
       }
       int Lab_Id = s.getLabID();
       int Equipment_Id = s.getEquipmentID();
       Timestamp Rep_App_Date = new Timestamp(System.currentTimeMillis());
       String Rep_App_Price = price;
       String Rep_App_Status = "applying";
     	
	   s.setEquipmentStatus("apply for repairing");
	   table.initTable();
	   label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
	   
	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
		      
		      java.sql.PreparedStatement pstmt = null;
		      String sql = "UPDATE `equipmentmanagement`.`equipment` SET `Eqpm_Sta`='apply for repairing' WHERE `Equipment_Id`=?;";
		     
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1,id);
		         
		      int line = pstmt.executeUpdate();
		      
		      java.sql.PreparedStatement pstmt2 = null;
		      String sql2 = "INSERT INTO `equipmentmanagement`.`rep_app` (`Lab_Id`, `Equipment_Id`, `Rep_App_Date`, `Rep_App_Price`, `Rep_App_Status`) VALUES (?,?,?,?,?);";
		     
		      pstmt2 = conn.prepareStatement(sql2);
		      pstmt2.setInt(1,Lab_Id);
		      pstmt2.setInt(2,Equipment_Id);
		      pstmt2.setTimestamp(3,Rep_App_Date);
		      pstmt2.setString(4,Rep_App_Price);
		      pstmt2.setString(5,Rep_App_Status);
		         
		      line = pstmt2.executeUpdate();
		      
		      conn.close(); // �ر�����
		      //rs.close(); // �رղ�ѯ
		     } catch (Exception ex) {
		      System.err.println("Exception:" + ex.getMessage());
		     }
	   
	   return;
  }
  
  if(button.equals(scrap)){             //�豸����
	   if(tip.getText().length()!=0){
	       	tip.setText("");
	       }                     //��ʾ�ַ���
	   int i=table.getSelectedRow();
	   if(i==-1)return ;
	   Integer id=(Integer) table.getValueAt(i,0);
	   if(id==null)return ;
	   Equipment s=null;
	   for(Equipment pm:Equipment.Equipments){
	    if(pm.getEquipmentID()==id)
	     s=pm;
	   }
	   
	   String Scrap_App_Reason;
      if(tScrap.getText().length()!=0){
    	  Scrap_App_Reason = tPrice.getText().toString();
         }
      else{
   	   tip.setText("����дά�޽��");
   	   return;
      }
      int Lab_Id = s.getLabID();
      int Equipment_Id = s.getEquipmentID();
      Timestamp Scrap_App_Date = new Timestamp(System.currentTimeMillis());
      String Scrap_App_Status = "applying";
    	
	   s.setEquipmentStatus("apply for scrapping");
	   table.initTable();
	   label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
	   
	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
		      
		      java.sql.PreparedStatement pstmt = null;
		      String sql = "UPDATE `equipmentmanagement`.`equipment` SET `Eqpm_Sta`='apply for scrapping' WHERE `Equipment_Id`=?;";
		     
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1,id);
		         
		      int line = pstmt.executeUpdate();
		      
		      java.sql.PreparedStatement pstmt2 = null;
		      String sql2 = "INSERT INTO `equipmentmanagement`.`scrap_app` (`Lab_Id`, `Equipment_Id`, `Scrap_App_Reason`, `Scrap_App_Status`, `Scrap_App_Date`) VALUES (?,?,?,?,?);";
		     
		      pstmt2 = conn.prepareStatement(sql2);
		      pstmt2.setInt(1,Lab_Id);
		      pstmt2.setInt(2,Equipment_Id);
		      pstmt2.setString(3, Scrap_App_Reason);
		      pstmt2.setString(4, Scrap_App_Status);
		      pstmt2.setTimestamp(5,Scrap_App_Date);
		         
		      line = pstmt2.executeUpdate();
		      
		      conn.close(); // �ر�����
		      //rs.close(); // �رղ�ѯ
		     } catch (Exception ex) {
		      System.err.println("Exception:" + ex.getMessage());
		     }
	   
	   return;
 }
  DefaultTableModel model=new DefaultTableModel(table.getPageData(),table.columnNames);
  table.setModel(model);
  label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
 }

}
