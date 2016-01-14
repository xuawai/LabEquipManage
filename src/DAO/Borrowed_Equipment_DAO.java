package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import Entity.Borrowed_Equipment;

public class Borrowed_Equipment_DAO {
	public void select(int LABID){
     String[] number = null;
	     int[] equipment_id = null;
	     int[] lab_id = null;
	     int[] borrowed_id = null;
	     Timestamp[] borrowed_date = null;
	     int[] student_id = null;
	     int result = 0;
	     try{
		     
	    	 Connection conn = MysqlCon.connect();
		     String sql = "select * from borrowed_equipment";
		     
		     ResultSet rs = conn.createStatement().executeQuery(sql);
		      // ȡ������
		     if (rs.last()){
	       result = rs.getRow();
	       rs.beforeFirst(); // ָ��ع�
	       } 
	     equipment_id = new int[result];
	      lab_id = new int[result];
	      borrowed_id = new int[result];
	      borrowed_date = new Timestamp[result];
	      student_id = new int[result];
	      number = new String[rs.getMetaData().getColumnCount() - 1]; // ���������ݱ�ͷ��һά����
	      rs.next();
	      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
	       number[j] = rs.getMetaData().getColumnName(j + 2); // ȡ����ͷ���������
	      }
	      int i = 0;
	      rs.beforeFirst();
	      while (rs.next()) {
	    	  equipment_id[i]=rs.getInt(1);
	    	  lab_id[i]=rs.getInt(2);
	    	  borrowed_id[i]=rs.getInt(3);
	    	  borrowed_date[i]=rs.getTimestamp(4);
	    	  student_id[i]=rs.getInt(5);
	     
	       i++;
	      }
	      rs.close(); // �رղ�ѯ
	      conn.close(); // �ر�����
	     
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
	     if(Borrowed_Equipment.Borrowed_Equipments.size()!=0)
	    	 Borrowed_Equipment.Borrowed_Equipments.clear();
	     for(int i=0;i<result;i++){
	    	 Borrowed_Equipment s = new Borrowed_Equipment(equipment_id[i],lab_id[i],borrowed_id[i],borrowed_date[i],student_id[i]);
	    	 if(LABID==0)
	    		 Borrowed_Equipment.Borrowed_Equipments.add(s);
	    	 else if(lab_id[i]==LABID)
	    		 Borrowed_Equipment.Borrowed_Equipments.add(s);
	     }    
	 }
}
