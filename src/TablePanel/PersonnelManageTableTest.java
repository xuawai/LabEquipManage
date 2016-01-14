package TablePanel;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.Manager_DAO;
import Entity.PersonnelManage;


@SuppressWarnings("serial")
public class PersonnelManageTableTest extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous,add,delete;
 private JLabel label1;
 private PersonnelManageTable table;
 private JTextField t1,t2,t3,t4;
 private JLabel l1,l2,l3,l4;
 private JLabel tip;
 

 public PersonnelManageTableTest() throws ClassNotFoundException, SQLException{
  
  initTableData();
  initComponent();

 }
 private void initTableData() {
  // TODO Auto-generated method stub
	 
//     String[] number = null;
//     int[] ID = null;
//     String[] name = null;
//     String[] psd = null;
//     int[] level = null;
//     int[] LabID = null;
//     int result = 0;
//     try{
//      Class.forName("com.mysql.jdbc.Driver");
//      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
//      
//     
//      String sql = "select * from manager";
//     
//      ResultSet rs = conn.createStatement().executeQuery(sql);
//      
//      // ȡ������
//      if (rs.last()){
//       result = rs.getRow();
//       rs.beforeFirst(); // ָ��ع�
//      }
//     
//      ID = new int[result];
//      name = new String[result];
//      psd = new String[result];
//      level = new int[result];
//      LabID = new int[result];
//      number = new String[rs.getMetaData().getColumnCount() - 1]; // ���������ݱ�ͷ��һά����
//      rs.next();
//      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
//       number[j] = rs.getMetaData().getColumnName(j + 2); // ȡ����ͷ���������
//      }
//      int i = 0;
//      rs.beforeFirst();
//      while (rs.next()) {
//    	  ID[i]=rs.getInt(1);
//    	  name[i]=rs.getString(2);
//    	  psd[i]=rs.getString(3);
//    	  level[i]=rs.getInt(4);
//    	  LabID[i]=rs.getInt(5);
//     
//       i++;
//      }
//      conn.close(); // �ر�����
//      rs.close(); // �رղ�ѯ
//     } catch (Exception ex) {
//      System.err.println("Exception:" + ex.getMessage());
//     }
//     
//     if( PersonnelManage.PersonnelManages.size()!=0){
//    	 PersonnelManage.PersonnelManages.clear();
//     }
//     for(int i=0;i<result;i++){
//    	 PersonnelManage s = new PersonnelManage(ID[i],name[i],psd[i],level[i],LabID[i]);
//	     PersonnelManage.PersonnelManages.add(s);
//     }
	 Manager_DAO MD=new Manager_DAO();
	 MD.select();
 }
 private void initComponent() {
  // TODO Auto-generated method stub

  table=new PersonnelManageTable();
  panel=new JScrollPane(table);
  panel.setBounds(10, 10, 700, 375);
  previous=new JButton("��һҳ");
  previous.setBounds(150, 475, 75,20);
  next=new JButton("��һҳ");
  next.setBounds(255, 475, 75, 20);
  add=new JButton("���");
  add.setBounds(630, 430, 75, 25);
  delete=new JButton("ɾ��");
  delete.setBounds(500, 475, 65, 20);
  previous.addActionListener(this);
  next.addActionListener(this);
  add.addActionListener(this);
  delete.addActionListener(this);
  label1=new JLabel("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
  label1.setBounds(10, 475, 130, 20);
 
  l1 = new JLabel("����");
  l1.setBounds(110, 430, 50, 25);
  l2 = new JLabel("����");
  l2.setBounds(230,430,50,25);
  l3 = new JLabel("Ȩ��");
  l3.setBounds(350,430,50,25);
  l4 = new JLabel("ʵ����");
  l4.setBounds(470,430,50,25);
  

  t1 = new JTextField("");
  t1.setBounds(140,430,80,25);
  t2 = new JTextField("");
  t2.setBounds(260,430,80,25);
  t3 = new JTextField("");
  t3.setBounds(380,430,80,25);
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
	      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
	      
	      java.sql.PreparedStatement pstmt = null;
	      String sql = "delete from manager where Manager_Id = ?";
	     
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
	    int ID = 0;
	    String name=null;
    	String psd=null;
    	int level=0;
    	int LabID=0;
    	if(t1.getText().length()!=0){
        	name = t1.getText().toString();
        }
    	
    	else{
    		tip.setText("�������û���");
    	 }
    	if(t2.getText().length()!=0){
        	psd = t2.getText().toString();
        }
    	
    	else{
    		tip.setText("����������");
    	 }
    	if(t3.getText().length()!=0){
        	level = Integer.parseInt(t3.getText().toString());
        }
    	
    	else{
    		tip.setText("������Ȩ��");
    	 }
    	if(t4.getText().length()!=0){
        	LabID = Integer.parseInt(t4.getText().toString());
        }
    	
    	else{
    		tip.setText("������ʵ����");
    	 }
	  
	  if(name!=null&&psd!=null&&level!=0&&LabID!=0){
	  try{
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
	      
	     
	      java.sql.PreparedStatement pstmt = null;
	      String sql = "INSERT INTO `equipmentmanagement`.`manager` (`Manager_Name`, `Password`, `Character`, `Lab_Id`) values (?,?,?,?)";
	     
	      pstmt = conn.prepareStatement(sql);		      		         
	     
	     // System.out.println(name+psd+level+LabID);
	      
	      //pstmt.setInt(1,ID);
	      pstmt.setString(1,name);
	      pstmt.setString(2,psd);
	      pstmt.setInt(3,level);
	      pstmt.setInt(4,LabID);
	      
	      
	     
		  pstmt.executeUpdate();
	      
		  sql = "select Manager_Id from manager where Manager_Name = ? and Password = ?";
		  pstmt = conn.prepareStatement(sql);
		  pstmt.setString(1,name);
	      pstmt.setString(2,psd);
	      ResultSet rs = pstmt.executeQuery();
	      if(rs.next())
	    	  ID = rs.getInt(1);
		  
	      
	      rs.close();
	      conn.close(); // �ر�����
	      
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }  
	  }
	  
   PersonnelManage personnelManage=new PersonnelManage(ID,name,psd,level,LabID);
   PersonnelManage.PersonnelManages.add(personnelManage);
   table.initTable();
   label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
   return;
  }
  DefaultTableModel model=new DefaultTableModel(table.getPageData(),table.columnNames);
  table.setModel(model);
  label1.setText("�ܹ�"+table.totalRowCount+"��¼|��ǰ��"+table.currentPage+"ҳ");
 }

}
