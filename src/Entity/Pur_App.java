package Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Pur_App {
	private int pur_app_id;
	private int lab_id;
	private String pur_app_factory;
	private String pur_app_model;
	private Timestamp pur_app_date;
	private String pur_app_price;
	private String pur_app_status;
	private int pur_app_number;
	public static List<Pur_App> Pur_Apps=new ArrayList<Pur_App>();
	public Pur_App(int pur_app_id,int lab_id,String pur_app_factory,String pur_app_model,Timestamp pur_app_date,String pur_app_price,String pur_app_status,int pur_app_number){
		this.pur_app_id=pur_app_id;
		this.lab_id=lab_id;
		this.pur_app_factory=pur_app_factory;
		this.pur_app_model=pur_app_model;
		this.pur_app_date=pur_app_date;
		this.pur_app_price=pur_app_price;
		this.pur_app_status=pur_app_status;
		this.pur_app_number=pur_app_number;
	}
	public int getPur_app_id() {
		return pur_app_id;
	}
	public void setPur_app_id(int pur_app_id) {
		this.pur_app_id = pur_app_id;
	}
	public int getLab_id() {
		return lab_id;
	}
	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
	}
	public String getPur_app_factory() {
		return pur_app_factory;
	}
	public void setPur_app_factory(String pur_app_factory) {
		this.pur_app_factory = pur_app_factory;
	}
	public String getPur_app_model() {
		return pur_app_model;
	}
	public void setPur_app_model(String pur_app_model) {
		this.pur_app_model = pur_app_model;
	}
	public Timestamp getPur_app_date() {
		return pur_app_date;
	}
	public void setPur_app_date(Timestamp pur_app_date) {
		this.pur_app_date = pur_app_date;
	}
	public String getPur_app_price() {
		return pur_app_price;
	}
	public void setPur_app_price(String pur_app_price) {
		this.pur_app_price = pur_app_price;
	}
	public String getPur_app_status() {
		return pur_app_status;
	}
	public void setPur_app_status(String pur_app_status) {
		this.pur_app_status = pur_app_status;
	}
	public int getPur_app_number() {
		return pur_app_number;
	}
	public void setPur_app_number(int pur_app_number) {
		this.pur_app_number = pur_app_number;
	}
	
}
