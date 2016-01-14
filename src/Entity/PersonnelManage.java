package Entity;


import java.util.ArrayList;
import java.util.List;
public class PersonnelManage {
 private int id;
 private String name;
 private String psd;
 private int level;
 private int LabID;
 public static List<PersonnelManage> PersonnelManages=new ArrayList<PersonnelManage>();
 public PersonnelManage(int pid,String pname,String ppsd,int plevel,int pLabID){
	 id = pid;
	 name = pname;
	 psd = ppsd;
	 level = plevel;
	 LabID = pLabID;
 }
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPsd() {
	return psd;
}
public void setPsd(String psd) {
	this.psd = psd;
}
public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public int getLabID() {
	return LabID;
}
public void setLabID(int labID) {
	LabID = labID;
}

 
}
