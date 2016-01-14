package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Entity.Equipment;

public class Equipment_DAO {
	public void select(int LABID){
		String[] number = null;
	     int[] EquipmentID = null;
	     int[] LabID = null;
	     String[] Factory = null;
	     String[] Model = null;
	     Timestamp[] PurchaseDate = null;
	     String[] EquipmentStatus = null;
	     int result = 0;
	     try{
	    	 Connection conn = MysqlCon.connect();
	      
	     
	      String sql = "select * from equipment";
	     
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      
	      // ȡ������
	      if (rs.last()){
	       result = rs.getRow();
	       rs.beforeFirst(); // ָ��ع�
	      }
	     
	      EquipmentID = new int[result];
	      LabID = new int[result];
	      Factory = new String[result];
	      Model = new String[result];
	      PurchaseDate = new Timestamp[result];
	      EquipmentStatus = new String[result];
	      number = new String[rs.getMetaData().getColumnCount() - 1]; // ���������ݱ�ͷ��һά����
	      rs.next();
	      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
	       number[j] = rs.getMetaData().getColumnName(j + 2); // ȡ����ͷ���������
	      }
	      int i = 0;
	      rs.beforeFirst();
	      while (rs.next()) {
	    	  EquipmentID[i]=rs.getInt(1);
	    	  LabID[i]=rs.getInt(2);
	    	  Factory[i]=rs.getString(3);
	    	  Model[i]=rs.getString(4);
	    	  PurchaseDate[i]=rs.getTimestamp(5);
	    	  EquipmentStatus[i]=rs.getString(6);
	     
	       i++;
	      }
	      conn.close(); // �ر�����
	      rs.close(); // �رղ�ѯ
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
	     
	     if( Equipment.Equipments.size()!=0){
	    	 Equipment.Equipments.clear();
	     }
	     
	     for(int i=0;i<result;i++){
	    	 Equipment s = new Equipment(EquipmentID[i],LabID[i],Factory[i],Model[i],PurchaseDate[i],EquipmentStatus[i]);
	    	 if(LABID==0)
	    		 Equipment.Equipments.add(s);
	    	 else if(LabID[i]==LABID)
	    		 Equipment.Equipments.add(s);
	     }
	}
}
