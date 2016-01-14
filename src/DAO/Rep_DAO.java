package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Entity.Rep_Record;

public class Rep_DAO {
	public void select(int LABID){
		String[] number = null;
	     int[] Rep_Record_Id = null;
	     int[] Lab_Id = null;
	     int[] Equipment_Id = null;
	     Timestamp[] Rep_Start_Date = null;
	     Timestamp[] Rep_End_Date = null;
	     String[] Rep_Price = null;
	     int result = 0;
	     try{
	    	 Connection conn = MysqlCon.connect();
	      
	     
	      String sql = "select * from rep_record";
	     
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      
	      // 取得行数
	      if (rs.last()){
	       result = rs.getRow();
	       rs.beforeFirst(); // 指针回滚
	      }
	     
	      Rep_Record_Id = new int[result];
	      Lab_Id = new int[result];
	      Equipment_Id = new int[result];
	      Rep_Start_Date = new Timestamp[result];
	      Rep_End_Date = new Timestamp[result];
	      Rep_Price = new String[result];
	      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
	      rs.next();
	      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
	       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
	      }
	      int i = 0;
	      rs.beforeFirst();
	      while (rs.next()) {
	    	  Rep_Record_Id[i]=rs.getInt(1);
	    	  Lab_Id[i]=rs.getInt(2);
	    	  Equipment_Id[i]=rs.getInt(3);
	    	  Rep_Start_Date[i]=rs.getTimestamp(4);
	    	  Rep_End_Date[i]=rs.getTimestamp(5);
	    	  Rep_Price[i]=rs.getString(6);
	     
	       i++;
	      }
	      conn.close(); // 关闭连接
	      rs.close(); // 关闭查询
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
	     
	     if( Rep_Record.Rep_Records.size()!=0){
	    	 Rep_Record.Rep_Records.clear();
	     }
	    	 
	     for(int i=0;i<result;i++){
	    	 Rep_Record s = new Rep_Record(Rep_Record_Id[i],Lab_Id[i],Equipment_Id[i],Rep_Start_Date[i],Rep_End_Date[i],Rep_Price[i]);
	    	 if(LABID==0)
	    		 Rep_Record.Rep_Records.add(s);
	    	 else if(Lab_Id[i]==LABID)
	    		 Rep_Record.Rep_Records.add(s);
	     }
	}
}
