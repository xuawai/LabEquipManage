package Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Pur_Record {
	private int pur_id;
	private int pur_number;
	private String pur_price;
	private Timestamp pur_finish_date;
	private String pur_factory;
	private String pur_model;
	private int lab_id;
	public static List<Pur_Record> Pur_Records=new ArrayList<Pur_Record>();
	public Pur_Record(int pur_id,int pur_number,String pur_price,Timestamp pur_finish_date,String pur_factory,String pur_model,int lab_id){
		this.pur_id=pur_id;
		this.pur_number=pur_number;
		this.pur_price=pur_price;
		this.pur_finish_date=pur_finish_date;
		this.pur_factory=pur_factory;
		this.pur_model=pur_model;
		this.lab_id=lab_id;
	}
	public int getPur_id() {
		return pur_id;
	}
	public void setPur_id(int pur_id) {
		this.pur_id = pur_id;
	}
	public int getPur_number() {
		return pur_number;
	}
	public void setPur_number(int pur_number) {
		this.pur_number = pur_number;
	}
	public String getPur_price() {
		return pur_price;
	}
	public void setPur_price(String pur_price) {
		this.pur_price = pur_price;
	}
	public Timestamp getPur_finish_date() {
		return pur_finish_date;
	}
	public void setPur_finish_date(Timestamp pur_finish_date) {
		this.pur_finish_date = pur_finish_date;
	}
	public String getPur_factory() {
		return pur_factory;
	}
	public void setPur_factory(String pur_factory) {
		this.pur_factory = pur_factory;
	}
	public String getPur_model() {
		return pur_model;
	}
	public void setPur_model(String pur_model) {
		this.pur_model = pur_model;
	}
	public int getLab_id() {
		return lab_id;
	}
	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
	}
	
	
}
