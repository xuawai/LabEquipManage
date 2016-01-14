package Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TablePanel.Borrowed_EquipmentTableTest;
import TablePanel.EquipmentTableTest;
import TablePanel.EquipmentTableTest2;
import TablePanel.InRepTableTest;
import TablePanel.InRepTableTest2;
import TablePanel.PersonnelManageTableTest;
import TablePanel.Pur_AppTableTest;
import TablePanel.Pur_AppTableTest2;
import TablePanel.Pur_RecordTableTest;
import TablePanel.Rep_AppTableTest;
import TablePanel.Rep_AppTableTest2;
import TablePanel.Rep_RecordTableTest;
import TablePanel.Scrap_AppTableTest;
import TablePanel.Scrap_AppTableTest2;
import TablePanel.Scrap_RecordTableTest;


public class MainMenuInterface2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	int LabId;
	JLabel title,username,pic;
	JButton exit,relogin,bt1,bt2,bt3,bt4,bt5,bt6;
	ImageIcon background;
	EquipmentTableTest2 equipmentTableTest;
	PersonnelManageTableTest personnelManageTableTest;
	Rep_AppTableTest2 rep_AppTableTest;
	Rep_RecordTableTest rep_RecordTableTest;
	Pur_AppTableTest2 pur_AppTableTest;
	Pur_RecordTableTest pur_RecordTableTest;
	Scrap_AppTableTest2 scrap_AppTableTest;
	Scrap_RecordTableTest scrap_RecordTableTest;
	Borrowed_EquipmentTableTest borrowed_EquipmentTableTest;
	InRepTableTest2 inRepTableTest;
	public MainMenuInterface2(String name,int LabId) {
		this.LabId = LabId;
		title = new JLabel("实验室设备管理系统");
		username = new JLabel("欢迎："+name);
		exit = new JButton("退出");
		relogin = new JButton("重新登录");
		bt1 = new JButton("查看设备");
		bt2 = new JButton("采购设备");
		bt3 = new JButton("设备外借");
		bt4 = new JButton("设备报废");
		bt5 = new JButton("设备维修");
		bt6 = new JButton("人员管理");
		background = new ImageIcon("image\\bkg.jpg");//背景图片
		pic = new JLabel(background);
		
		this.setLayout(null);
		
        pic.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);  
        // 把背景图片添加到分层窗格的最底层作为背景  
        this.getLayeredPane().add(pic, new Integer(Integer.MIN_VALUE));
        
        username.setBounds(950,20,100,30);
        this.add(username);
        username.setOpaque(false);
        
        relogin.setBounds(660,100,100,20);
        this.add(relogin);
        relogin.setOpaque(false);
        
        exit.setBounds(780,100,100,20);
        this.add(exit);
        exit.setOpaque(false);
        
        title.setBounds(410,40,250,30);
        title.setFont(new java.awt.Font("Dialog",1,26)); 
        this.add(title);
        title.setOpaque(false);
        
        bt1.setBounds(50,180,120,30);
        this.add(bt1);
        bt1.setOpaque(false);
        
        bt2.setBounds(50,250,120,30);
        this.add(bt2);
        bt2.setOpaque(false);
        
        bt3.setBounds(50,320,120,30);
        this.add(bt3);
        bt3.setOpaque(false);
        
        bt4.setBounds(50,390,120,30);
        this.add(bt4);
        bt4.setOpaque(false);
        
        bt5.setBounds(50,460,120,30);
        this.add(bt5);
        bt5.setOpaque(false);
        
        bt6.setBounds(50,530,120,30);
        this.add(bt6);
        bt6.setOpaque(false);
        
        relogin.addActionListener(new ActionListener() {    //重新登录事件
            @Override
            public void actionPerformed(ActionEvent e) {
            	LoginInterface loginInterface = new LoginInterface();
             	dispose();             	
             }
            
        });
        
        exit.addActionListener(new ActionListener() {    //退出事件
            @Override
            public void actionPerformed(ActionEvent e) {           	
             	dispose();             	
             }
            
        });
        
       
        
        bt1.addActionListener (new ActionListener() {    //产看实验设备块
            @Override
            public void actionPerformed(ActionEvent e){      
            	update();
				try {
					equipmentTableTest = new EquipmentTableTest2(LabId);
					add(equipmentTableTest);
					equipmentTableTest.setSize(900,500);
					equipmentTableTest.setLocation(220, 140);
					equipmentTableTest.setOpaque(false);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            	
            }
            
        });
        
        
        bt2.addActionListener (new ActionListener() {    //采购实验设备块
            @Override
            public void actionPerformed(ActionEvent e){      
            	update();
            	
				try {
					pur_AppTableTest = new Pur_AppTableTest2(LabId);
					add(pur_AppTableTest);
					pur_AppTableTest.setSize(900,250);
					pur_AppTableTest.setLocation(220, 140);
					pur_AppTableTest.setOpaque(false);
					
					pur_RecordTableTest = new Pur_RecordTableTest(LabId);
					add(pur_RecordTableTest);
					pur_RecordTableTest.setSize(900,250);
					pur_RecordTableTest.setLocation(220, 390);
					pur_RecordTableTest.setOpaque(false);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            	
            }
            
        });
        
        bt3.addActionListener (new ActionListener() {    //设备外借模块
            @Override
            public void actionPerformed(ActionEvent e){ 
            	update();
				
				try {
					borrowed_EquipmentTableTest = new Borrowed_EquipmentTableTest(LabId);
					add(borrowed_EquipmentTableTest);
					borrowed_EquipmentTableTest.setSize(900,500);
					borrowed_EquipmentTableTest.setLocation(220, 140);
					borrowed_EquipmentTableTest.setOpaque(false);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            	
            }
            
        });
        
        bt4.addActionListener (new ActionListener() {    //报废实验设备块
            @Override
            public void actionPerformed(ActionEvent e){      
            	update();
            	
				try {
					scrap_AppTableTest = new Scrap_AppTableTest2(LabId);
					add(scrap_AppTableTest);
					scrap_AppTableTest.setSize(900,250);
					scrap_AppTableTest.setLocation(220, 140);
					scrap_AppTableTest.setOpaque(false);
					
					scrap_RecordTableTest = new Scrap_RecordTableTest(LabId);
					add(scrap_RecordTableTest);
					scrap_RecordTableTest.setSize(900,250);
					scrap_RecordTableTest.setLocation(220,390);
					scrap_RecordTableTest.setOpaque(false);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            	
            }
            
        });
        
        
        bt5.addActionListener (new ActionListener() {    //维修实验设备块
            @Override
            public void actionPerformed(ActionEvent e){      
            	update();
				
				try {
					
					inRepTableTest = new InRepTableTest2(LabId);
					add(inRepTableTest);
					inRepTableTest.setSize(900,155);
					inRepTableTest.setLocation(220, 130);
					inRepTableTest.setOpaque(false);
					
					rep_AppTableTest = new Rep_AppTableTest2(LabId);
					add(rep_AppTableTest);
					rep_AppTableTest.setSize(900,155);
					rep_AppTableTest.setLocation(220, 305);
					rep_AppTableTest.setOpaque(false);
					
					rep_RecordTableTest = new Rep_RecordTableTest(LabId);
					add(rep_RecordTableTest);
					rep_RecordTableTest.setSize(900,155);
					rep_RecordTableTest.setLocation(220,480);
					rep_RecordTableTest.setOpaque(false);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            	
            }
            
        });
        
        bt6.addActionListener (new ActionListener() {    //人员管理模块
            @Override
            public void actionPerformed(ActionEvent e){ 
            	update();
				
				try {
					personnelManageTableTest = new PersonnelManageTableTest();
					add(personnelManageTableTest);
					personnelManageTableTest.setSize(900,500);
					personnelManageTableTest.setLocation(220, 140);
					personnelManageTableTest.setOpaque(false);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            	
            }
            
        });
        
      
        
        //设置显示
        this.setSize(1100, 700);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("实验室设备管理系统");
	}
	

	 
	 public void update(){
     	if(personnelManageTableTest!=null)
     		getContentPane().remove(personnelManageTableTest);
     	if(equipmentTableTest!=null)
     		getContentPane().remove(equipmentTableTest);
     	if(rep_AppTableTest!=null)
     		getContentPane().remove(rep_AppTableTest);
     	if(rep_RecordTableTest!=null)
     		getContentPane().remove(rep_RecordTableTest);
     	if(pur_AppTableTest!=null)
     		getContentPane().remove(pur_AppTableTest);
     	if(pur_RecordTableTest!=null)
     		getContentPane().remove(pur_RecordTableTest);
     	if(scrap_AppTableTest!=null)
     		getContentPane().remove(scrap_AppTableTest);
     	if(scrap_RecordTableTest!=null)
     		getContentPane().remove(scrap_RecordTableTest);
     	if(borrowed_EquipmentTableTest!=null)
     		getContentPane().remove(borrowed_EquipmentTableTest);
     	if(inRepTableTest!=null)
     		getContentPane().remove(inRepTableTest);
     }

}
