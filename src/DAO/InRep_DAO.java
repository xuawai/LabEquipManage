package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Entity.InRep;

public class InRep_DAO {
	public void select(int LABID){

		  // TODO Auto-generated method stub
			 
		     String[] number = null;
		     int[] Equipment_Id = null;
		     int[] Lab_Id = null;
		     Timestamp[] Rep_Start_Date = null;
		     String[] Rep_Price = null;

		     int result = 0;
		     try{
		    	 Connection conn = MysqlCon.connect();
		     
		      String sql = "select * from inrep";
		     
		      ResultSet rs = conn.createStatement().executeQuery(sql);
		      
		      // 取得行数
		      if (rs.last()){
		       result = rs.getRow();
		       rs.beforeFirst(); // 指针回滚
		      }
		     
		      Equipment_Id = new int[result];
		      Lab_Id = new int[result];
		      Rep_Start_Date = new Timestamp[result];
		      Rep_Price = new String[result];
		      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
		      rs.next();
		      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
		       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
		      }
		      int i = 0;
		      rs.beforeFirst();
		      while (rs.next()) {
		    	  Equipment_Id[i]=rs.getInt(1);
		    	  Lab_Id[i]=rs.getInt(2);
		    	  Rep_Start_Date[i]=rs.getTimestamp(3);
		    	  Rep_Price[i]=rs.getString(4);
		     
		       i++;
		      }
		      conn.close(); // 关闭连接
		      rs.close(); // 关闭查询
		     } catch (Exception ex) {
		      System.err.println("Exception:" + ex.getMessage());
		     }
		     if(InRep.InReps.size()!=0)
		    	 InRep.InReps.clear();
		     for(int i=0;i<result;i++){
		    	 InRep s = new InRep(Equipment_Id[i],Lab_Id[i],Rep_Start_Date[i],Rep_Price[i]);
		    	 if(LABID==0)
		    		 InRep.InReps.add(s);
		    	 else if(Lab_Id[i]==LABID)
		    		 InRep.InReps.add(s);
		     }
		 
	}
}
