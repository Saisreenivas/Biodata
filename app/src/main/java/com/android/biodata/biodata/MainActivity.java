package com.android.biodata.biodata;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.biodata.biodata.R;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Data.Constants;
import Data.DBHandler;
import Data.Excel2SQLiteHelper;
import Model.MyData;

public class MainActivity extends ListActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private int STORAGE_PERMISSION_CODE = 23;
//    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    TextView lbl;
    DBHandler controller = new DBHandler(this);
    Button btnimport, btnsort;
    ListView lv;
    public static final int requestcode = 1;
    ArrayList<MyData> allData= new ArrayList<>();
    Context context;

    public Dialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnimport = (Button) findViewById(R.id.btn_upload);

        lbl = (TextView) findViewById(R.id.txtresult);
        lv = getListView();
        btnimport = (Button) findViewById(R.id.btn_upload);
        btnsort = (Button) findViewById(R.id.btn_sort);

        tryRequest();

//        btnimport.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
//                fileintent.setType("application/excel");
//                try {
//                    if(isReadStorageAllowed()){
//                        //If permission is already having then showing the toast
//                        //Existing the method with return
//                        startActivityForResult(fileintent, requestcode);
//                        return;
//                    }
//                    //If the app has not the permission then asking for the permission
//                    requestStoragePermission();
//                } catch (ActivityNotFoundException e) {
//                    lbl.setText("No activity can handle picking a file. Showing alternatives.");
//                }
//            }
//        });

        /*btnsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogSort();

            }
        });*/


        btnimport.setOnClickListener(this);
        btnsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogSort();
            }
        });

        ArrayList<HashMap<String, String>> myList = controller.getAllProducts("all", "all", "");
        showDataViaAdapter(myList);

    }

    public void alertDialogSort(){
        RadioButton selectedButton;
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Sort By-");

        context = dialog.getContext();

        final RadioGroup radioGroup1 = (RadioGroup) dialog.findViewById(R.id.radioChoice);
        final RadioGroup sortOrder = (RadioGroup) dialog.findViewById(R.id.ascDscOrder);
        Button sortAllBtn = (Button) dialog.findViewById(R.id.sortAllBtn);
        Button sortBtn = (Button) dialog.findViewById(R.id.sortBtn);
        RadioButton mandalDialog = (RadioButton) dialog.findViewById(R.id.mandalDialog);
        RadioButton villageDialog = (RadioButton) dialog.findViewById(R.id.villageDialog);

        mandalDialog.setOnCheckedChangeListener(MainActivity.this);
        villageDialog.setOnCheckedChangeListener(MainActivity.this);

        final Dialog finalDialog = dialog;
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int sortOrderInt = 0;
                int itemSelected=0;

                String spinnerText = null;
                itemSelected = radioGroup1.getCheckedRadioButtonId();


//                Log.v("radioGroup", "CheckedRadioButtonId == " + itemSelected);
                String columnName= null;
                if(itemSelected == -1){
                    columnName = Constants.KEY_ID;
                    spinnerText = "%";
//                    Log.v("itemSelected", "CheckedRadioButtonId ==  me -1");

                }else{
                    itemSelected = radioGroup1.getCheckedRadioButtonId();
                    Spinner spinner = (Spinner) dialog.findViewById(R.id.spinnerDialog);
                    spinnerText = spinner.getSelectedItem().toString();
//                    Log.v("itemSelected", "CheckedRadioButtonId ==  not -1 " + itemSelected + " " + R.id.villageDialog);
                    if(itemSelected == R.id.villageDialog){
                        columnName = Constants.VILLAGE_NAME;
//                        Log.v("columnName", "VillageName" + itemSelected);
                    }else if(itemSelected == R.id.mandalDialog){
                        columnName = Constants.MANDAL_NAME;
//                        Log.v("columnName", "MandalName" + itemSelected);
                    }
                }


                RadioButton sortOrderAny = null;
                String sortOrderMe = "";
                int select = 0;
                select = sortOrder.getCheckedRadioButtonId();
                if(select == -1){
                    sortOrderMe = " asc";
//                    Log.v("sortOrder", "CheckedRadioButtonId ==  -1 asc");
                } else {
                    sortOrderAny = (RadioButton) finalDialog.findViewById(select);
                    sortOrderMe = sortOrderAny.getText().toString();
//                    Log.v("sortOrder", "CheckedRadioButtonId ==  not -1" + sortOrderMe);
                }
//                Log.v("final", sortOrderMe + " " + columnName + " " + spinnerText);
                ArrayList<HashMap<String, String>> myList = controller.getAllProducts(sortOrderMe, columnName, spinnerText);
                showDataViaAdapter(myList);
                dialog.dismiss();
            }
        });
        sortAllBtn.setOnClickListener(this);
        dialog.show();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        switch (requestCode) {
            case requestcode:
                tryRequest();
        }
    }

    public void tryRequest(){

//                String FilePath = ;
//                String FilePath = data.getData().getPath();
        try {
//            if (resultCode == RESULT_OK) {
                allData.clear();
                AssetManager am = this.getAssets();
                FileInputStream inStream;
                Workbook wb = null;

                try {
//                            inStream = new FileInputStream(new File(FilePath));
                    wb = new HSSFWorkbook(getResources().openRawResource(R.raw.datal97xlswithouthff11));
//                            inStream.close();
                } catch (IOException e) {
                    lbl.setText("First "+e.getMessage().toString());
                    e.printStackTrace();
                }

                HSSFSheet sheet1 = (HSSFSheet) wb.getSheetAt(0);
                sheet1.removeRow(sheet1.getRow(0));

                if (sheet1 == null) {
                    return;
                }

                controller.delete();

                Excel2SQLiteHelper.insertExcelToSqlite(controller, sheet1);
                controller.close();

//            }
        } catch (Exception ex) {
            lbl.setText(ex.getMessage().toString() +   "Second");
        }

        ArrayList<HashMap<String, String>> myList = controller.getAllProducts("all", "all", "");
        showDataViaAdapter(myList);

    }

    public void showDataViaAdapter(final ArrayList<HashMap<String, String>> myList){

        if (myList.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, myList,
                    R.layout.list_row, new String[]{Constants.KEY_ID, Constants.TNO_NAME, Constants.PERSON_NAME,
                    Constants.QUAL_NAME, Constants.DOB_NAME, Constants.HEIGHT_NAME,
                    Constants.COLOR_NAME, Constants.SGOT_NAME, Constants.MGOT_NAME,
                    Constants.FATHER_NAME, Constants.MOTHER_NAME, Constants.VILLAGE_NAME,
                    Constants.MANDAL_NAME, Constants.CELL_NAME, Constants.BRO1_NAME,
                    Constants.BRO2_NAME, Constants.SIS1_NAME, Constants.SIS2_NAME, Constants.P_NAME,
                    Constants.D_NAME, Constants.SALARY_NAME, Constants.JOB_AT_NAME},
                    new int[]{R.id.sr_no, R.id.row_t_number, R.id.row_name, R.id.row_Qly, R.id.row_dob,
                            R.id.row_height, R.id.row_color, R.id.row_s_got, R.id.row_m_got,
                            R.id.row_father_name, R.id.row_mother_name, R.id.row_village,
                            R.id.row_mandal, R.id.row_cell, R.id.row_bro_1, R.id.row_bro_2,
                            R.id.row_sis_1, R.id.row_sis_2, R.id.row_p, R.id.row_d, R.id.row_salary,
                            R.id.row_job_at});

//                    ListAdapter adap = new SimpleAdapter(MainActivity.this, myList, R.layout.list_row,
//                            new String[]{ Constants.TNO_NAME}, new int[]{ R.id.row_t_number});
            setListAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, String> dataAtPosition;
                    dataAtPosition = myList.get(position);
                    int positionFinal = Integer.parseInt(dataAtPosition.get(Constants.KEY_ID));
                    MyData me = controller.getOneProduct(Constants.KEY_ID, Integer.toString(positionFinal));

                    Intent intent = new Intent(MainActivity.this, Person_Data.class);

                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("userObj", me);
                    intent.putExtras(mBundle);

                    startActivity(intent);
                }
            });

        }
    }

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Spinner dropDownList = (Spinner) buttonView.getRootView().findViewById(R.id.spinnerDialog);
        switch (buttonView.getId()){
            case R.id.mandalDialog:
                if(isChecked){
                    List<String> myList = controller.getList(Constants.MANDAL_NAME, " ASC");
//                    Log.d("onCheckedChanged: ", buttonView.getId() + " mandal");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getApplicationContext(),
                            R.layout.simple_spinner_item, myList);
                    dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    dropDownList.setAdapter(dataAdapter);
                    String spinnerText = dropDownList.getSelectedItem().toString();
                }
                break;
            case R.id.villageDialog:
                if(isChecked){
                    List<String> myList = controller.getList(Constants.VILLAGE_NAME, " ASC");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getApplicationContext(),
                            R.layout.simple_spinner_item, myList);
//                    Log.d("onCheckedChanged: ", buttonView.getId() + " village");
                    dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    dropDownList.setAdapter(dataAdapter);
                    String spinnerText = dropDownList.getSelectedItem().toString();
                }
                break;
            case R.id.dobDataDialog:
                break;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_upload:
                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("application/excel");
                try {
                    if(isReadStorageAllowed()){
                        //If permission is already having then showing the toast
                        //Existing the method with return
                        startActivityForResult(fileintent, requestcode);
                        return;
                    }
                    //If the app has not the permission then asking for the permission
                requestStoragePermission();
            } catch (ActivityNotFoundException e) {
                lbl.setText("No activity can handle picking a file. Showing alternatives.");
                }
                break;
            case R.id.sortAllBtn:
                ArrayList<HashMap<String, String>> myList = controller.getAllProducts("all", "all", "");
                showDataViaAdapter(myList);
                dialog.dismiss();
                break;
        }
    }
}
