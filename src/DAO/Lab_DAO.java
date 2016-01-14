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
		      
		      // ȡ������
		      if (rs.last()){
		       result = rs.getRow();
		       rs.beforeFirst(); // ָ��ع�
		      }
		     
		      Lab_Id = new int[result];
		      Lab_Name = new String[result];
		     
		      number = new String[rs.getMetaData().getColumnCount() - 1]; // ���������ݱ�ͷ��һά����
		      rs.next();
		      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
		       number[j] = rs.getMetaData().getColumnName(j + 2); // ȡ����ͷ���������
		      }
		      int i = 0;
		      rs.beforeFirst();
		      while (rs.next()) {
		    	  Lab_Id[i]=rs.getInt(1);
		    	  Lab_Name[i]=rs.getString(2);
		     
		       i++;
		      }
		      conn.close(); // �ر�����
		      rs.close(); // �رղ�ѯ
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
