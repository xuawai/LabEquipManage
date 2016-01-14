package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import Entity.PersonnelManage;

public class Manager_DAO {
	public void select(){

		  // TODO Auto-generated method stub
			 
		     String[] number = null;
		     int[] ID = null;
		     String[] name = null;
		     String[] psd = null;
		     int[] level = null;
		     int[] LabID = null;
		     int result = 0;
		     try{
		    	 Connection conn = MysqlCon.connect();
		      
		     
		      String sql = "select * from manager";
		     
		      ResultSet rs = conn.createStatement().executeQuery(sql);
		      
		      // ȡ������
		      if (rs.last()){
		       result = rs.getRow();
		       rs.beforeFirst(); // ָ��ع�
		      }
		     
		      ID = new int[result];
		      name = new String[result];
		      psd = new String[result];
		      level = new int[result];
		      LabID = new int[result];
		      number = new String[rs.getMetaData().getColumnCount() - 1]; // ���������ݱ�ͷ��һά����
		      rs.next();
		      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
		       number[j] = rs.getMetaData().getColumnName(j + 2); // ȡ����ͷ���������
		      }
		      int i = 0;
		      rs.beforeFirst();
		      while (rs.next()) {
		    	  ID[i]=rs.getInt(1);
		    	  name[i]=rs.getString(2);
		    	  psd[i]=rs.getString(3);
		    	  level[i]=rs.getInt(4);
		    	  LabID[i]=rs.getInt(5);
		     
		       i++;
		      }
		      conn.close(); // �ر�����
		      rs.close(); // �رղ�ѯ
		     } catch (Exception ex) {
		      System.err.println("Exception:" + ex.getMessage());
		     }
		     
		     if( PersonnelManage.PersonnelManages.size()!=0){
		    	 PersonnelManage.PersonnelManages.clear();
		     }
		     for(int i=0;i<result;i++){
		    	 PersonnelManage s = new PersonnelManage(ID[i],name[i],psd[i],level[i],LabID[i]);
			     PersonnelManage.PersonnelManages.add(s);
		     }
		 
	}
}
