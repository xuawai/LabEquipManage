package Entity;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
public class Rep_Record {
 private int Rep_Record_Id;
 private int Lab_Id;
 private int Equipment_Id;
 private Timestamp Rep_Start_Date;
 private Timestamp Rep_End_Date;
 private String Rep_Price;
 public static List<Rep_Record> Rep_Records=new ArrayList<Rep_Record>();
 public Rep_Record(int Rep_Record_Id,int Lab_Id,int Equipment_Id,Timestamp Rep_Start_Date,Timestamp Rep_End_Date,String Rep_Price){
	 this.Rep_Record_Id=Rep_Record_Id;
	 this.Lab_Id=Lab_Id;
	 this.Equipment_Id=Equipment_Id;
	 this.Rep_Start_Date=Rep_Start_Date;
	 this.Rep_End_Date=Rep_End_Date;
	 this.Rep_Price=Rep_Price;
 }
public int getRep_Record_Id() {
	return Rep_Record_Id;
}
public void setRep_Record_Id(int rep_Record_Id) {
	Rep_Record_Id = rep_Record_Id;
}
public int getLab_Id() {
	return Lab_Id;
}
public void setLab_Id(int lab_Id) {
	Lab_Id = lab_Id;
}
public int getEquipment_Id() {
	return Equipment_Id;
}
public void setEquipment_Id(int equipment_Id) {
	Equipment_Id = equipment_Id;
}
public Timestamp getRep_Start_Date() {
	return Rep_Start_Date;
}
public void setRep_Start_Date(Timestamp rep_Start_Date) {
	Rep_Start_Date = rep_Start_Date;
}
public Timestamp getRep_End_Date() {
	return Rep_End_Date;
}
public void setRep_End_Date(Timestamp rep_End_Date) {
	Rep_End_Date = rep_End_Date;
}
public String getRep_Price() {
	return Rep_Price;
}
public void setRep_Price(String rep_Price) {
	Rep_Price = rep_Price;
}

}
