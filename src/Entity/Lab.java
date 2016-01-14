package Entity;


import java.util.ArrayList;
import java.util.List;
public class Lab {
 private int Lab_Id;
 private String Lab_Name;
 public static List<Lab> Labs=new ArrayList<Lab>();
 public Lab(int Lab_Id,String Lab_Name){
	 this.Lab_Id=Lab_Id;
	 this.Lab_Name=Lab_Name;
 }
public int getLab_Id() {
	return Lab_Id;
}
public void setLab_Id(int lab_Id) {
	Lab_Id = lab_Id;
}
public String getLab_Name() {
	return Lab_Name;
}
public void setLab_Name(String lab_Name) {
	Lab_Name = lab_Name;
}

}
