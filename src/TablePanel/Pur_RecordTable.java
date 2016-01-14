package TablePanel;


import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Entity.Pur_Record;
@SuppressWarnings("serial")
public class Pur_RecordTable extends JTable{
 //JTable���ҳ��Ϣ��ر���
 public int currentPage=1;
 public int pageCount=7;
 public int totalPage=0;
 public int totalRowCount=0;
 public int column=0;
 public int restCount;
 public Object[][] resultData;
 //JTable����Ϣ��ر���
 public List<Pur_Record> Pur_Records=Pur_Record.Pur_Records;
 public String[] columnNames={"pur_id","pur_number","pur_price","pur_finish_date","pur_factory","pur_model","Lab_ID"};
 public DefaultTableModel model=null;
 public Pur_RecordTable(){initTable();}
 /**
  * ��ȡ��һҳ
  */
 public int getNextPage(){
  if(this.currentPage!=this.totalPage){
   return ++currentPage;
  }
  return -1;
 }
 /**
  * ��ȡ��һҳ
  */
 public int getPreviousPage(){
  if(this.currentPage!=1){
   return --currentPage;
  }
  return -1;
 }
 /**
  * ��ȡ���һҳ
  */
 public int getLastPage(){
  return this.totalPage;
 }
 /**
  * ��ȡ��һҳ
  */
 public int getFirstPage(){
  return 1;
 }
 /**
  * ��ȡ��ҳ��
  */
 public int getTotolPage(){
  return this.totalPage;
 }
 /**
  * ��ȡ��ǰҳ
  */
 public int getCurrentPage(){
  return this.currentPage;
 }
 /**
  * ���ԭʼ���ݼ�
  * @param students
  * @return
  */
 public Object[][] getData(List<Pur_Record> Pur_Records){
  if(Pur_Records.size()>0){
   Object[][] data=new Object[Pur_Records.size()][7];
   for(int i=0;i<Pur_Records.size();i++){
	   Pur_Record s=Pur_Records.get(i);
    Object[] a={s.getPur_id(),s.getPur_number(),s.getPur_price(),s.getPur_finish_date(),s.getPur_factory(),s.getPur_model(),s.getLab_id()};//��List���ϵ����ݸ���Object����
    data[i]=a;//�������ֵ������ά�����һ��
   }
   return data;
  }
  return null;
 }
 /**
  * ��ʼ�������
  * @param data
  */
 public void initResultData(Object[][] data){
  if(data!=null){
   resultData=data;//�ܵĽ����
   column=data[0].length;//�������
   totalRowCount=data.length;//��ĳ���
   totalPage=totalRowCount%pageCount==0?totalRowCount/pageCount:totalRowCount/pageCount+1;//���������ҳ��
   restCount=totalRowCount%pageCount==0?5:totalRowCount%pageCount;//���һҳ��������
  }
 }
 /**
  * ��ȡ��ҳ����
  * @return
  */
 public Object[][] getPageData(){
  Object[][] currentPageData=new Object[pageCount][column];//����ÿҳ���ݼ�
  if(this.getCurrentPage()<this.totalPage){//�����ǰҳ��С����ҳ������ôÿҳ��ĿӦ���ǹ涨����pageCount
   for(int i=pageCount*(this.getCurrentPage()-1);i<pageCount*(this.getCurrentPage()-1)+pageCount;i++){
    for(int j=0;j<column;j++){
     //�ѽ�����ж�Ӧÿҳ��ÿһ������ȫ����ֵ����ǰҳ��ÿһ�е�ÿһ��
     currentPageData[i%pageCount][j]=resultData[i][j];
    }
   }
  }else{
   //�ڶ�̬�ı����ݽ������ʱ�������ǰҳû�������ˣ���ص�ǰһҳ��һ��������һҳ���ԣ�
   if(pageCount*(this.getCurrentPage()-1)>=totalRowCount)this.currentPage--;
   for(int i=pageCount*(this.getCurrentPage()-1);i<pageCount*(this.getCurrentPage()-1)+restCount;i++){
    for(int j=0;j<column;j++){
     currentPageData[i%pageCount][j]=resultData[i][j];
    }
   }
  } 
  return currentPageData;
 }
 /**
  * ��ʼ���������
  */
 public void initTable(){
  Object[][] data=getData(Pur_Records);
  if(data!=null){
   initResultData(data);
   model=new DefaultTableModel(getPageData(),columnNames); 
  }else{
   //����������û�����ݣ���ô���ÿ����������ݼ��е�ÿһ��
   Object[][] nothing={{},{},{},{},{}};
   model=new DefaultTableModel(nothing,columnNames);
   totalRowCount=0;
  }
  this.setModel(model);
  this.setRowHeight(20);
  DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
 }
 
 @Override
 public boolean isCellEditable(int row, int column)
 {
 // TODO Auto-generated method stub
 return false;
 }
}
