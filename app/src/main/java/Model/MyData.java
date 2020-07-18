package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sai sreenivas on 3/4/2017.
 */

public class MyData implements Serializable{
    private static final long serialVersionUID = 10L;
    private int _id;
    private String TNo;
    private String Name;
    private String Qly;
    private String DOB;
    private String Height;
    private String Color;
    private String SGot;
    private String MGot;
    private String FatherName;
    private String MotherName;
    private String Village;
    private String Mandal;
    private String Cell;
    private String Bro1;
    private String Bro2;
    private String Sis1;
    private String Sis2;
    private String P;
    private String D;
    private String Salary;
    private String JobAt;

    public String getHeight() {
        return Height;
    }

    public String getCell() {
        return Cell;
    }

    public String getP() {
        return P;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public void setCell(String cell) {
        Cell = cell;
    }

    public void setP(String p) {
        P = p;
    }

    public MyData() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getTNo() {
        return TNo;
    }

    public void setTNo(String TNo) {
        this.TNo = TNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQly() {
        return Qly;
    }

    public void setQly(String qly) {
        Qly = qly;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getSGot() {
        return SGot;
    }

    public void setSGot(String SGot) {
        this.SGot = SGot;
    }

    public String getMGot() {
        return MGot;
    }

    public void setMGot(String MGot) {
        this.MGot = MGot;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String motherName) {
        MotherName = motherName;
    }

    public String getVillage() {
        return Village;
    }

    public void setVillage(String village) {
        Village = village;
    }

    public String getBro1() {
        return Bro1;
    }

    public void setBro1(String bro1) {
        Bro1 = bro1;
    }

    public String getBro2() {
        return Bro2;
    }

    public void setBro2(String bro2) {
        Bro2 = bro2;
    }

    public String getSis1() {
        return Sis1;
    }

    public void setSis1(String sis1) {
        Sis1 = sis1;
    }

    public String getSis2() {
        return Sis2;
    }

    public void setSis2(String sis2) {
        Sis2 = sis2;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getJobAt() {
        return JobAt;
    }

    public void setJobAt(String jobAt) {
        JobAt = jobAt;
    }

    public String getMandal() {
        return Mandal;
    }

    public void setMandal(String mandal) {
        Mandal = mandal;
    }
}
