package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;  

import Interface.MainMenuInterface;
import Interface.MainMenuInterface2;

public class LoginInterface extends JFrame{
       JTextField jTextField ;
       JPasswordField jPasswordField;
       JLabel jLabel1,jLabel2,jLabel3,jLabel4;
       JButton jb1,jb2; 
       ImageIcon background;
       JPanel jp;
       public LoginInterface(){
         jTextField = new JTextField(12);
         jPasswordField = new JPasswordField(12);
         background = new ImageIcon("image\\bkg.jpg");//����ͼƬ
         jLabel1 = new JLabel("�û���");
         jLabel2 = new JLabel("����");
         jLabel3 = new JLabel("");
         jb1 = new JButton("ȷ��");
         jb2 = new JButton("ȡ��");       
         jLabel4 = new JLabel(background);
         jp = new JPanel();
         
         jp = (JPanel) this.getContentPane();
         //this.setLayout(new GridLayout(5,1,10,24));
         this.setLayout(null);
         
         jLabel4.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
         JPanel imagePanel = (JPanel) this.getContentPane();  
         imagePanel.setOpaque(false);  
         // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
         this.getLayeredPane().add(jLabel4, new Integer(Integer.MIN_VALUE));
         
         jLabel1.setBounds(370,200,50,30);
         this.add(jLabel1);
         jLabel1.setOpaque(false);
         
         jPasswordField.setBounds(440,270,180,30);
         this.add(jPasswordField);
         jPasswordField.setOpaque(false);
         
         jLabel2.setBounds(390,270,50,30);
         this.add(jLabel2);
         jLabel2.setOpaque(false);
         
         jb1.setBounds(560,350,70,20);
         this.add(jb1);
         jb1.setOpaque(false);
      
         jb2.setBounds(640,350,70,20);
         this.add(jb2);
         jb2.setOpaque(false);
         
         jTextField.setBounds(440,200,180,30);
         this.add(jTextField);
         jTextField.setOpaque(false);
         
         jLabel3.setBounds(460,410,130,30);
         this.add(jLabel3);
         jLabel3.setOpaque(false);
         jLabel3.setFont(new java.awt.Font("Dialog",1,13)); 
         jLabel3.setForeground(Color.red);
         
         jb1.addActionListener(new ActionListener() {    //��¼�¼�
             @Override
             public void actionPerformed(ActionEvent e) {
            	String name = null;
              	String psd = null;
              	MainMenuInterface mainMenuInterface = null;
              	MainMenuInterface2 mainMenuInterface2 = null;
              	if(jTextField.getText().length()!=0){
                  	name = jTextField.getText().toString();
                  }
              	
              	else{
              		 jLabel3.setText("�������û���");
              	 }
              	if(jPasswordField.getPassword().length!=0){
              		char[] pass = jPasswordField.getPassword();
              	    psd = new String(pass);
              		System.out.println("psd:"+psd);
                  }
              	else{
              		jLabel3.setText("����������");
              	}
              	if(jTextField.getText().length()==0&&jPasswordField.getPassword().length==0){
              		jLabel3.setText("�������û���������");
              	}
              	
              	//jp.setVisible(false);
              	if(jTextField.getText().length()!=0&&jPasswordField.getPassword().length!=0){
              		 try{
              		      Class.forName("com.mysql.jdbc.Driver");
              		      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipmentmanagement", "root", "");
              		      
              		      java.sql.PreparedStatement pstmt = null;
              		      String sql = "select * from manager where Manager_Name = ? and Password = ?";
              		     
              		      pstmt = conn.prepareStatement(sql);
              	          pstmt.setString(1,name);
              	          pstmt.setString(2,psd);
              		      
              	          ResultSet rs = pstmt.executeQuery();
              		     
              	          int type = 3;              //����Ա�����ܸ�����
              	          int LabId = 100;            //ʵ����ID
              	          
              		      if(rs.next()){
              		    	  type = rs.getInt(4);
              		    	  LabId = rs.getInt(5);
              		      }
              		      
              		      if(type == 1){
              		    	 mainMenuInterface = new MainMenuInterface(name,LabId);
              		    	 dispose();
              		      }
              		    	
              		      else if(type == 2){
              		    	 mainMenuInterface2 = new MainMenuInterface2(name,LabId);
              		    	 dispose();
              		      }
              		      else
              		    	jLabel3.setText("�û��������벻ƥ��");
              		      
              		      conn.close(); // �ر�����
              		      rs.close(); // �رղ�ѯ
              		     } catch (Exception ex) {
              		      System.err.println("Exception:" + ex.getMessage());
              		     }
              		                 	
                  	
              	}
              	
              	
              	
              }
             
         });
         
         jb2.addActionListener(new ActionListener() {     //ȡ���¼�
             public void actionPerformed(ActionEvent e) {
             	
             	 if(jTextField.getText().length()!=0){
                 	jTextField.setText("");
                 }
             	if(jPasswordField.getPassword().length!=0){
             		jPasswordField.setText("");
                 }
             	if(jLabel3.getText().length()!=0){
             		jLabel3.setText("");
             	}
          
             }
         });
         //������ʾ
         this.setSize(1100, 700);
         //this.pack();
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
         this.setTitle("��½");
          
     }

 }
