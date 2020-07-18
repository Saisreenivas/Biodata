package com.android.biodata.biodata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import java.util.HashMap;

import Model.MyData;

public class Person_Data extends AppCompatActivity {

//    private TextView tNo, tName, qly, dob, height, color, sGot, mGot, fatherName, motherName,
//            village, mandal, cell, bro1, bro2, sis1, sis2, p, d, salary, jobAt;

    private TextView personData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person__data);

//        tNo = (TextView)findViewById(R.id.person_t_number);
//        tName = (TextView)findViewById(R.id.person_name);
//        qly = (TextView)findViewById(R.id.person_qly);
//        dob = (TextView)findViewById(R.id.person_dob);
//        height = (TextView)findViewById(R.id.person_Height);
//        color = (TextView)findViewById(R.id.person_color);
//        sGot = (TextView)findViewById(R.id.person_s_got);
//        mGot = (TextView)findViewById(R.id.person_m_got);
//        fatherName = (TextView)findViewById(R.id.person_father_name);
//        motherName = (TextView)findViewById(R.id.person_mother_name);
//        village = (TextView)findViewById(R.id.person_village_name);
//        mandal = (TextView)findViewById(R.id.oerson_mandal_name);
//        cell = (TextView)findViewById(R.id.person_cell);
//        bro1 = (TextView)findViewById(R.id.person_bro_1);
//        bro2 = (TextView)findViewById(R.id.person_bro_2);
//        sis1 = (TextView)findViewById(R.id.person_sis_1);
//        sis2 = (TextView)findViewById(R.id.person_sis_2);
//        p = (TextView)findViewById(R.id.person_p);
//        d = (TextView)findViewById(R.id.person_d);
//        salary = (TextView)findViewById(R.id.person_salary);
//        jobAt = (TextView)findViewById(R.id.person_job_at);

        personData = (TextView) findViewById(R.id.person_data);

        MyData data = (MyData) getIntent().getSerializableExtra("userObj");

        Spanned dataMe = Html.fromHtml("<b>T.No: </b>"+ data.getTNo() +"<br><b>Name: </b>"+ data.getName()
        + "<br><b>Qualification: </b>"+ data.getQly() + "<br>" + "<b>DOB: </b>" + data.getDOB() +
        "<b>" + " &nbsp &nbsp &nbsp &nbsp &nbsp" +" Height: </b>"+ data.getHeight() + "<br><b>Color: </b>"+ data.getColor() +
                "<br><b>S.Got: </b>" + data.getSGot() + "<br><b>M.Got: </b>" + data.getMGot()+
                "<br><b>Father's Name: </b>"+ data.getFatherName() + "<br><b>Mother's Name:</b>" +
                data.getMotherName() + "<br><b>Village:</b>"+ data.getVillage() + "<br><b>Mandal: </b>" +
        data.getMandal() + "<br><b>Cell: </b>"+ data.getCell()+ "<br><b>Bro: </b>"+ data.getBro1() +
                "<b>" + getString(R.string.tab) +" Bro: </b>" + data.getBro2() + "<br><b>Sis: </b>"+
                data.getSis1() + "<b>" + getString(R.string.tab) +" Sis: </b>" +
                data.getSis2() + "<br><b>P: </b>"+ data.getP() + "<b>" +getString(R.string.tab) +" &nbsp " +
                "&nbsp &nbsp D: </b>" + data.getD() +
                "<br><b>Salary: </b>" + data.getSalary() + "<br><b>Job At: </b>"+ data.getJobAt());
        personData.setText(dataMe);

//        tNo.setText("T.No.: "+ data.getTNo());
//        tName.setText("Name: "+data.getName());
//        qly.setText("Qualification: "+data.getQly());
//        dob.setText("DOB: "+data.getDOB());
//        height.setText("Height: "+data.getHeight());
//        color.setText("Color:"+data.getColor());
//        sGot.setText("S.Got: "+data.getSGot());
//        mGot.setText("M.Got: "+data.getMGot());
//        fatherName.setText("Father's Name: "+data.getFatherName());
//        motherName.setText("Mother's Name: "+data.getMotherName());
//        village.setText("Village: "+data.getVillage());
//        mandal.setText("Mandal: "+data.getMandal());
//        cell.setText("Cell: "+data.getCell());
//        bro1.setText("Bro: "+data.getBro1());
//        bro2.setText("Bro: "+data.getBro2());
//        sis1.setText("Sis: "+data.getSis1());
//        sis2.setText("Sis:"+data.getSis2());
//        p.setText("P: "+data.getP());
//        d.setText("D: "+data.getD());
//        salary.setText("Salary: "+data.getSalary());
//        jobAt.setText("Job At: "+data.getJobAt());
    }
}
