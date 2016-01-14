package Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Scrap_Record {
	private int scrap_id;
	private int lab_id;
	private int equipment_id;
	private Timestamp scrap_date;
	public static List<Scrap_Record> Scrap_Records=new ArrayList<Scrap_Record>();
	public Scrap_Record(int scrap_id,int lab_id,int equipment_id,Timestamp scrap_date2){
		this.scrap_id=scrap_id;
		this.lab_id=lab_id;
		this.equipment_id=equipment_id;
		this.scrap_date=scrap_date2;
	}
	public int getScrap_id() {
		return scrap_id;
	}
	public void setScrap_id(int scrap_id) {
		this.scrap_id = scrap_id;
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
	public Timestamp getScrap_date() {
		return scrap_date;
	}
	public void setScrap_date(Timestamp scrap_date) {
		this.scrap_date = scrap_date;
	}
	
}
