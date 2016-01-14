package Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Scrap_App {
	private int scrap_app_id;
	private int lab_id;
	private int equipment_id;
	private String scrap_app_reason;
	private String scrap_app_status;
	private Timestamp scrap_app_date;
	public static List<Scrap_App> Scrap_Apps=new ArrayList<Scrap_App>();
	public Scrap_App(int scrap_app_id,int lab_id,int equipment_id,String scrap_app_reason,String scrap_app_status,Timestamp scrap_app_date2){
		this.scrap_app_id=scrap_app_id;
		this.lab_id=lab_id;
		this.equipment_id=equipment_id;
		this.scrap_app_reason=scrap_app_reason;
		this.scrap_app_status=scrap_app_status;
		this.scrap_app_date=scrap_app_date2;
	}
	public int getScrap_app_id() {
		return scrap_app_id;
	}
	public void setScrap_app_id(int scrap_app_id) {
		this.scrap_app_id = scrap_app_id;
	}
	public int getLab_id() {
		return lab_id;
	}
	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public String getScrap_app_reason() {
		return scrap_app_reason;
	}
	public void setScrap_app_reason(String scrap_app_reason) {
		this.scrap_app_reason = scrap_app_reason;
	}
	public String getScrap_app_status() {
		return scrap_app_status;
	}
	public void setScrap_app_status(String scrap_app_status) {
		this.scrap_app_status = scrap_app_status;
	}
	public Timestamp getScrap_app_date() {
		return scrap_app_date;
	}
	public void setScrap_app_date(Timestamp scrap_app_date) {
		this.scrap_app_date = scrap_app_date;
	}
}
