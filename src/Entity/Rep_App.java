package Entity;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
public class Rep_App {
 private int Rep_App_Id;
 private int Lab_Id;
 private int Equipment_Id;
 private Timestamp Rep_App_Date;
 private String Rep_App_Price;
 private String Rep_App_Status;
 public static List<Rep_App> Rep_Apps=new ArrayList<Rep_App>();
 public Rep_App( int Rep_App_Id,int Lab_Id,int Equipment_Id,Timestamp Rep_App_Date,String Rep_App_Price,String Rep_App_Status){
	 this.Rep_App_Id=Rep_App_Id;
	 this.Lab_Id=Lab_Id;
	 this.Equipment_Id=Equipment_Id;
	 this.Rep_App_Date=Rep_App_Date;
	 this.Rep_App_Price=Rep_App_Price;
	 this.Rep_App_Status=Rep_App_Status;
 }
public int getRep_App_Id() {
	return Rep_App_Id;
}
public void setRep_App_Id(int rep_App_Id) {
	Rep_App_Id = rep_App_Id;
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
public Timestamp getRep_App_Date() {
	return Rep_App_Date;
}
public void setRep_App_Date(Timestamp rep_App_Date) {
	Rep_App_Date = rep_App_Date;
}
public String getRep_App_Price() {
	return Rep_App_Price;
}
public void setRep_App_Price(String rep_App_Price) {
	Rep_App_Price = rep_App_Price;
}
public String getRep_App_Status() {
	return Rep_App_Status;
}
public void setRep_App_Status(String rep_App_Status) {
	Rep_App_Status = rep_App_Status;
}
 
}
