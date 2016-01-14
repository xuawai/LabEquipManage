package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Entity.Rep_App;

public class Rep_App_DAO {
	public void select(int LABID){
		String[] number = null;
	     int[] Rep_App_Id = null;
	     int[] Lab_Id = null;
	     int[] Equipment_Id = null;
	     Timestamp[] Rep_App_Date = null;
	     String[] Rep_App_Price = null;
	     String[] Rep_App_Status = null;
	     int result = 0;
	     try{
	    	 Connection conn = MysqlCon.connect();
	     
	      String sql = "select * from rep_app";
	     
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      
	      // 取得行数
	      if (rs.last()){
	       result = rs.getRow();
	       rs.beforeFirst(); // 指针回滚
	      }
	     
	      Rep_App_Id = new int[result];
	      Lab_Id = new int[result];
	      Equipment_Id = new int[result];
	      Rep_App_Date = new Timestamp[result];
	      Rep_App_Price = new String[result];
	      Rep_App_Status = new String[result];
	      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
	      rs.next();
	      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
	       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
	      }
	      int i = 0;
	      rs.beforeFirst();
	      while (rs.next()) {
	    	  Rep_App_Id[i]=rs.getInt(1);
	    	  Lab_Id[i]=rs.getInt(2);
	    	  Equipment_Id[i]=rs.getInt(3);
	    	  Rep_App_Date[i]=rs.getTimestamp(4);
	    	  Rep_App_Price[i]=rs.getString(5);
	    	  Rep_App_Status[i]=rs.getString(6);
	     
	       i++;
	      }
	      conn.close(); // 关闭连接
	      rs.close(); // 关闭查询
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
	     
	     if( Rep_App.Rep_Apps.size()!=0){
	    	 Rep_App.Rep_Apps.clear();
	     }
	     for(int i=0;i<result;i++){
	    	 Rep_App s = new Rep_App(Rep_App_Id[i],Lab_Id[i],Equipment_Id[i],Rep_App_Date[i],Rep_App_Price[i],Rep_App_Status[i]);
	    	 if(LABID==0)
	    		 Rep_App.Rep_Apps.add(s);
	    	 else if(Lab_Id[i]==LABID)
	    		 Rep_App.Rep_Apps.add(s);
	     }
	}
}
