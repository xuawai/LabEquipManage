package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Entity.Pur_App;

public class Pur_App_DAO {
	public void select(int LABID){
		String[] number = null;
	     int[] pur_app_id = null;
	     int[] lab_id = null;
	     String[] pur_app_factory = null;
	     String[] pur_app_model = null;
	     Timestamp[] pur_app_date = null;
	     String[] pur_app_price = null;
	     String[] pur_app_status = null;
	     int[] pur_app_number = null;
	     int result = 0;
	     try{
	     Connection conn = MysqlCon.connect();
	      
	     
	      String sql = "select * from pur_app";
	     
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      
	      // 取得行数
	      if (rs.last()){
	       result = rs.getRow();
	       rs.beforeFirst(); // 指针回滚
	      }
	     
	      pur_app_id = new int[result];
	      lab_id = new int[result];
	      pur_app_factory = new String[result];
	      pur_app_model = new String[result];
	      pur_app_date = new Timestamp[result];
	      pur_app_price = new String[result];
	      pur_app_status = new String[result];
	      pur_app_number = new int[result];
	      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
	      rs.next();
	      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
	       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
	      }
	      int i = 0;
	      rs.beforeFirst();
	      while (rs.next()) {
	    	  pur_app_id[i]=rs.getInt(1);
	    	  lab_id[i]=rs.getInt(2);
	    	  pur_app_factory[i]=rs.getString(3);
	    	  pur_app_model[i]=rs.getString(4);
	    	  pur_app_date[i]=rs.getTimestamp(5);
	    	  pur_app_price[i]=rs.getString(6);
	    	  pur_app_status[i]=rs.getString(7);
	    	  pur_app_number[i]=rs.getInt(8);
	     
	       i++;
	      }
	      conn.close(); // 关闭连接
	      rs.close(); // 关闭查询
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
	     if(Pur_App.Pur_Apps.size()!=0)
	    	 Pur_App.Pur_Apps.clear();
	     for(int i=0;i<result;i++){
	    	 Pur_App s = new Pur_App(pur_app_id[i],lab_id[i],pur_app_factory[i],pur_app_model[i],pur_app_date[i],pur_app_price[i],pur_app_status[i],pur_app_number[i]);
	    	 if(LABID==0)
	    	   Pur_App.Pur_Apps.add(s);	
	    	 else if(lab_id[i]==LABID)
	    	   Pur_App.Pur_Apps.add(s);
	     }
	}
}
