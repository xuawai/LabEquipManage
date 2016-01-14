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
import Entity.Rep_App;


@SuppressWarnings("serial")
public class Rep_AppTableTest extends JPanel implements ActionListener {
 private JScrollPane panel;
 private JButton next,previous,delete;
 private JLabel label1;
 private Rep_AppTable table;
 private JLabel tip;
 private int LABID;

 public Rep_AppTableTest(int LABID) throws ClassNotFoundException, SQLException{
  this.LABID = LABID;
  initTableData();
  initComponent();

 }
 private void initTableData() {
  // TODO Auto-generated method stub

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
	  delete=new JButton("删除");
	  delete.setBounds(500, 135, 65, 20);
	  previous.addActionListener(this);
	  next.addActionListener(this);
	  delete.addActionListener(this);
	  label1=new JLabel("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
	  label1.setBounds(10, 135, 130, 20);
	 
	  tip = new JLabel("tip");
	  tip.setBounds(250,500,150,20);
	  this.setLayout(null);
	  this.add(panel);
	  this.add(previous);
	  this.add(next);
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
  
  DefaultTableModel model=new DefaultTableModel(table.getPageData(),table.columnNames);
  table.setModel(model);
  label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页");
 }

}
