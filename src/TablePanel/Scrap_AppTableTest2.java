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

import DAO.Scrap_App_DAO;
import Entity.PersonnelManage;
import Entity.Pur_App;
import Entity.Scrap_App;


@SuppressWarnings("serial")
public class Scrap_AppTableTest2 extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous,add,delete,refuse;
 private JLabel label1;
 private Scrap_AppTable table;
 private JLabel tip;
 private int LABID;

 public Scrap_AppTableTest2(int LABID) throws ClassNotFoundException, SQLException{
  this.LABID = LABID;
  initTableData();
  initComponent();

 }
 private void initTableData() {
	 Scrap_App_DAO SAD=new Scrap_App_DAO();
	 SAD.select(LABID);
 }
 private void initComponent() {
  // TODO Auto-generated method stub
	 table=new Scrap_AppTable();
	  panel=new JScrollPane(table);
	  panel.setBounds(10, 10, 700, 170);
	  previous=new JButton("��һҳ");
	  previous.setBounds(150, 215, 75,20);
	  next=new JButton("��һҳ");
	  next.setBounds(255, 215, 75, 20);
	  add=new JButton("ͬ��");
	  add.setBounds(530,185, 75, 25);
	  refuse=new JButton("�ܾ�");
	  refuse.setBounds(630,185, 75, 25);
	  delete=new JButton("ɾ��");
	  delete.setBounds(500, 215, 65, 20);
	  previous.addActionListener(this);
	  next.addActionListener(this);
	  add.addActionListener(this);
	  refuse.addActionListener(this);
	  delete.addActionListener(this);
	  label1=new JLabel("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
	  label1.setBounds(10, 215, 130, 20);
	 
	  tip = new JLabel("tip");
	  tip.setBounds(280,185,150,20);
	  this.setLayout(null);
	  this.add(panel);
	  this.add(previous);
	  this.add(next);
	  this.add(add);
	  this.add(delete);
	  this.add(label1);
	  this.add(refuse);
	  this.add(tip);
	  table.setOpaque(false);
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
   PersonnelManage s=null;
   for(PersonnelManage pm:PersonnelManage.PersonnelManages){
    if(pm.getId()==id)
     s=pm;
   }
   int index=PersonnelManage.PersonnelManages.indexOf(s);
   PersonnelManage.PersonnelManages.remove(index);
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
  if(button.equals(add)){             //ͬ������
	  if(tip.getText().length()!=0){			//��ʾ�ַ���
	       	tip.setText("");
	       } 
	  int i=table.getSelectedRow();
	   if(i==-1)return ;
	   Integer id=(Integer) table.getValueAt(i,0);
	   if(id==null)return ;
	   Scrap_App s=null;
	   for(Scrap_App pm:Scrap_App.Scrap_Apps){
	    if(pm.getScrap_app_id()==id)
	     s=pm;
	   }

	   
	   if(s.getScrap_app_status().equals("approval")){
		  tip.setText("��ͬ������");
		   return;                               //�������ͨ�������޷��ٴ�ͨ��
		   
	   }
		   
	   s.setScrap_app_status("approval");
	   table.initTable();
	   label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
	   
	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
		      
		      java.sql.PreparedStatement pstmt = null;
		      String sql = "UPDATE `equipmentmanagement`.`scrap_app` SET `Scrap_App_Status`='approval' WHERE `Scrap_App_Id`= ?;";
		     
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
  if(button.equals(refuse)){             //ͬ������
	  if(tip.getText().length()!=0){			//��ʾ�ַ���
	       	tip.setText("");
	       } 
	  int i=table.getSelectedRow();
	   if(i==-1)return ;
	   Integer id=(Integer) table.getValueAt(i,0);
	   if(id==null)return ;
	   Scrap_App s=null;
	   for(Scrap_App pm:Scrap_App.Scrap_Apps){
	    if(pm.getScrap_app_id()==id)
	     s=pm;
	   }

	   
	   if(s.getScrap_app_status().equals("refuse")){
		  tip.setText("�Ѿܾ�����");
		   return;                               //�������ͨ�������޷��ٴ�ͨ��
		   
	   }
		   
	   s.setScrap_app_status("refuse");
	   table.initTable();
	   label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
	   
	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
		      
		      java.sql.PreparedStatement pstmt = null;
		      String sql = "UPDATE `equipmentmanagement`.`scrap_app` SET `Scrap_App_Status`='refuse' WHERE `Scrap_App_Id`= ?;";
		     
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
  DefaultTableModel model=new DefaultTableModel(table.getPageData(),table.columnNames);
  table.setModel(model);
  label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
 }

}