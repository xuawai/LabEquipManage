package Entity;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
public class Equipment {
 private int LabID;
 private int equipmentID;
 private String Factory;
 private String Model;
 private Timestamp PurchaseDate;
 private String EquipmentStatus;

 public static List<Equipment> Equipments=new ArrayList<Equipment>();
 public Equipment(int pequipmentID,int pLabID,String pFactory,String pModel,Timestamp pPurchaseDate,String pEquipmentStatus){
	 LabID = pLabID;
	 equipmentID = pequipmentID;
	 Factory = pFactory;
	 Model = pModel;
	 PurchaseDate = pPurchaseDate;
	 EquipmentStatus = pEquipmentStatus;
 }
public int getLabID() {
	return LabID;
}
public void setLabID(int labID) {
	LabID = labID;
}
public int getEquipmentID() {
	return equipmentID;
}
public void setEquipmentID(int equipmentID) {
	this.equipmentID = equipmentID;
}
public String getFactory() {
	return Factory;
}
public void setFactory(String factory) {
	Factory = factory;
}
public String getModel() {
	return Model;
}
public void setModel(String model) {
	Model = model;
}
public Timestamp getPurchaseDate() {
	return PurchaseDate;
}
public void setPurchaseDate(Timestamp purchaseDate) {
	PurchaseDate = purchaseDate;
}
public String getEquipmentStatus() {
	return EquipmentStatus;
}
public void setEquipmentStatus(String equipmentStatus) {
	EquipmentStatus = equipmentStatus;
}

 
}
