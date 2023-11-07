package database;

import java.util.Date;
import java.util.Vector;

public class DataItem {
    private Date time;
    private String data1;
    private String data2;
    private String operation;
    private Vector vc;
    public DataItem() {}

    public DataItem(Date time, String data1, String data2, String operation) {
        this.time = time;
        this.data1 = data1;
        this.data2 = data2;
        this.operation = operation;
        vc=new Vector<>();
        vc.addElement(time);
        vc.addElement(data1);
        vc.addElement(data2);
        vc.addElement(operation);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Vector getVec(){
        return vc;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "time=" + time +
                ", data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
