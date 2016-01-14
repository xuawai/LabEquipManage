package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Entity.Scrap_Record;

public class Scrap_DAO {
	public void select(int LABID){
		String[] number = null;
	     int[] scrap_id = null;
	     int[] lab_id = null;
	     int[] equipment_id = null;
	     Timestamp[] scrap_date = null;
	     int result = 0;
	     try{
	    	 Connection conn = MysqlCon.connect();
	     
	      String sql = "select * from scrap_Record";
	     
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      
	      // 取得行数
	      if (rs.last()){
	       result = rs.getRow();
	       rs.beforeFirst(); // 指针回滚
	      }
	     
	      scrap_id = new int[result];
	      lab_id = new int[result];
	      equipment_id = new int[result];
	      scrap_date = new Timestamp[result];
	      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
	      rs.next();
	      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
	       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
	      }
	      int i = 0;
	      rs.beforeFirst();
	      while (rs.next()) {
	    	  scrap_id[i]=rs.getInt(1);
	    	  lab_id[i]=rs.getInt(2);
	    	  equipment_id[i]=rs.getInt(3);
	    	  scrap_date[i]=rs.getTimestamp(4);
	     
	       i++;
	      }
	      conn.close(); // 关闭连接
	      rs.close(); // 关闭查询
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
	     if(Scrap_Record.Scrap_Records.size()!=0)
	    	 Scrap_Record.Scrap_Records.clear();
	     for(int i=0;i<result;i++){
	    	 Scrap_Record s = new Scrap_Record(scrap_id[i],lab_id[i],equipment_id[i],scrap_date[i]);
	    	 if(LABID==0)
	    		 Scrap_Record.Scrap_Records.add(s);
	    	 else if(lab_id[i]==LABID)
	    		 Scrap_Record.Scrap_Records.add(s);
	     }
	}
}
