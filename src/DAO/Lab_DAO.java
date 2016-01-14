package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import Entity.Lab;

public class Lab_DAO {
	public void select(){

		  // TODO Auto-generated method stub
			 
		     String[] number = null;
		     int[] Lab_Id = null;
		     String[] Lab_Name = null;

		     int result = 0;
		     try{
		    	 Connection conn = MysqlCon.connect();
		      
		     
		      String sql = "select * from lab";
		     
		      ResultSet rs = conn.createStatement().executeQuery(sql);
		      
		      // 取得行数
		      if (rs.last()){
		       result = rs.getRow();
		       rs.beforeFirst(); // 指针回滚
		      }
		     
		      Lab_Id = new int[result];
		      Lab_Name = new String[result];
		     
		      number = new String[rs.getMetaData().getColumnCount() - 1]; // 定义存放数据表头的一维数组
		      rs.next();
		      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
		       number[j] = rs.getMetaData().getColumnName(j + 2); // 取出表头并存放数组
		      }
		      int i = 0;
		      rs.beforeFirst();
		      while (rs.next()) {
		    	  Lab_Id[i]=rs.getInt(1);
		    	  Lab_Name[i]=rs.getString(2);
		     
		       i++;
		      }
		      conn.close(); // 关闭连接
		      rs.close(); // 关闭查询
		     } catch (Exception ex) {
		      System.err.println("Exception:" + ex.getMessage());
		     }
		     if(Lab.Labs.size()!=0)
		    	 Lab.Labs.clear();
		     for(int i=0;i<result;i++){
		    	 Lab s = new Lab(Lab_Id[i],Lab_Name[i]);
		    	 Lab.Labs.add(s);
		     }
		 
	}
}
