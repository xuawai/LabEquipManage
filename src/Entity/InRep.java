package Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class InRep {
	private int Equipment_Id;
	private int Lab_Id;
	private Timestamp Rep_Start_Date;
	private String Rep_Price;
	public static List<InRep> InReps=new ArrayList<InRep>();
	public InRep(int Equipment_Id,int Lab_Id,Timestamp Rep_Start_Date,String Rep_Price){
		this.Equipment_Id=Equipment_Id;
		this.Lab_Id=Lab_Id;
		this.Rep_Start_Date=Rep_Start_Date;
		this.Rep_Price=Rep_Price;
	}
	public int getEquipment_Id() {
		return Equipment_Id;
	}
	public void setEquipment_Id(int equipment_Id) {
		Equipment_Id = equipment_Id;
	}
	public int getLab_Id() {
		return Lab_Id;
	}
	public void setLab_Id(int lab_Id) {
		Lab_Id = lab_Id;
	}
	public Timestamp getRep_Start_Date() {
		return Rep_Start_Date;
	}
	public void setRep_Start_Date(Timestamp rep_Start_Date) {
		Rep_Start_Date = rep_Start_Date;
	}
	public String getRep_Price() {
		return Rep_Price;
	}
	public void setRep_Price(String rep_Price) {
		Rep_Price = rep_Price;
	}
}
