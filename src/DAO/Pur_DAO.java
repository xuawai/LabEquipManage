package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Entity.Pur_Record;

public class Pur_DAO {
	public void select(int LABID){
		 String[] number = null;
	     int[] pur_id = null;
	     int[] pur_number = null;
	     String[] pur_price = null;   
	     Timestamp[] pur_finish_date = null;
	     String[] pur_factory = null;
	     String[] pur_model = null;
	     int[] lab_id = null;
	     int result = 0;
	     try{
	    	 Connection conn = MysqlCon.connect();
	      
	     
	      String sql = "select * from pur_Record";
	     
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      
	      // 取得行数
	      if (rs.last()){
	       result = rs.getRow();
	       rs.beforeFirst(); // 指针回滚
	      }
	     
	      pur_id = new int[result];
	      pur_number = new int[result];
	      pur_price = new String[result];
	      pur_finish_date = new Timestamp[result];
	      pur_factory = new String[result];
	      pur_model = new String[result];
	      lab_id = new int[result];
	      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
	      rs.next();
	      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
	       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
	      }
	      int i = 0;
	      rs.beforeFirst();
	      while (rs.next()) {
	    	  pur_id[i]=rs.getInt(1);
	    	  pur_number[i]=rs.getInt(2);
	    	  pur_price[i]=rs.getString(3);
	    	  pur_finish_date[i]=rs.getTimestamp(4);
	    	  pur_factory[i]=rs.getString(5);
	    	  pur_model[i]=rs.getString(6);
	    	  lab_id[i]=rs.getInt(7);
	     
	       i++;
	      }
	      conn.close(); // 关闭连接
	      rs.close(); // 关闭查询
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
	     if(Pur_Record.Pur_Records.size()!=0)
	    	 Pur_Record.Pur_Records.clear();
	     for(int i=0;i<result;i++){
	    	 Pur_Record s = new Pur_Record(pur_id[i],pur_number[i],pur_price[i],pur_finish_date[i],pur_factory[i],pur_model[i],lab_id[i]);
	    	 if(LABID==0)
	    		 Pur_Record.Pur_Records.add(s);
	    	 else if(lab_id[i]==LABID)
	    		 Pur_Record.Pur_Records.add(s);
	     }
	}
}
