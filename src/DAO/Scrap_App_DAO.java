package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Entity.Scrap_App;

public class Scrap_App_DAO {
	public void select(int LABID){
		String[] number = null;
	     int[] scrap_app_id = null;
	     int[] lab_id = null;
	     int[] equipment_id = null;
	     String[] scrap_app_reason = null;
	     String[] scrap_app_status = null;
	     Timestamp[] scrap_app_date = null;
	     int result = 0;
	     try{
	    	 Connection conn = MysqlCon.connect();
	     
	      String sql = "select * from Scrap_App";
	     
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      
	      // ȡ������
	      if (rs.last()){
	       result = rs.getRow();
	       rs.beforeFirst(); // ָ��ع�
	      }
	     
	      scrap_app_id = new int[result];
	      lab_id = new int[result];
	      equipment_id = new int[result];
	      scrap_app_reason = new String[result];
	      scrap_app_status = new String[result];
	      scrap_app_date = new Timestamp[result];
	      number = new String[rs.getMetaData().getColumnCount() - 1]; // ���������ݱ�ͷ��һά����
	      rs.next();
	      for (int j = 0; j < rs.getMetaData().getColumnCount() - 1; j++) {
	       number[j] = rs.getMetaData().getColumnName(j + 2); // ȡ����ͷ���������
	      }
	      int i = 0;
	      rs.beforeFirst();
	      while (rs.next()) {
	    	  scrap_app_id[i]=rs.getInt(1);
	    	  lab_id[i]=rs.getInt(2);
	    	  equipment_id[i]=rs.getInt(3);
	    	  scrap_app_reason[i]=rs.getString(4);
	    	  scrap_app_status[i]=rs.getString(5);
	    	  scrap_app_date[i]=rs.getTimestamp(6);
	     
	       i++;
	      }
	      conn.close(); // �ر�����
	      rs.close(); // �رղ�ѯ
	     } catch (Exception ex) {
	      System.err.println("Exception:" + ex.getMessage());
	     }
	     if(Scrap_App.Scrap_Apps.size()!=0)
	    	 Scrap_App.Scrap_Apps.clear();
	     for(int i=0;i<result;i++){
	    	 Scrap_App s = new Scrap_App(scrap_app_id[i],lab_id[i],equipment_id[i],scrap_app_reason[i],scrap_app_status[i],scrap_app_date[i]);
	    	 if(LABID==0)
	    		 Scrap_App.Scrap_Apps.add(s);
	    	 else if(lab_id[i]==LABID)
	    		 Scrap_App.Scrap_Apps.add(s);
	     }
	}
}
