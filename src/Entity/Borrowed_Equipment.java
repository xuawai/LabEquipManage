package Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Borrowed_Equipment {
	private int equipment_id;
	private int lab_id;
	private int borrowed_id;
	private Timestamp borrowed_date;
	private int student_id;
	public static List<Borrowed_Equipment> Borrowed_Equipments=new ArrayList<Borrowed_Equipment>();
	public Borrowed_Equipment(int equipment_id,int lab_id,int borrowed_id,Timestamp borrowed_date,int student_id){
		this.equipment_id=equipment_id;
		this.lab_id=lab_id;
		this.borrowed_id=borrowed_id;
		this.borrowed_date=borrowed_date;
		this.student_id=student_id;
	}
	public int getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}
	public int getLab_id() {
		return lab_id;
	}
	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
	}
	public int getBorrowed_id() {
		return borrowed_id;
	}
	public void setBorrowed_id(int borrowed_id) {
		this.borrowed_id = borrowed_id;
	}
	public Timestamp getBorrowed_date() {
		return borrowed_date;
	}
	public void setBorrowed_date(Timestamp borrowed_date) {
		this.borrowed_date = borrowed_date;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
}
