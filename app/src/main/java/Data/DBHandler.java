package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Model.MyData;

import static Data.Constants.*;

/**
 * Created by Sai sreenivas on 3/4/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private ArrayList<HashMap<String,String>> allData = new ArrayList<>();

    public DBHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BIO_TABLE ="CREATE TABLE " + Constants.TABLE_NAME+ "( " + Constants.KEY_ID +
                " INTEGER PRIMARY KEY, "+ Constants.TNO_NAME + " TEXT , " + PERSON_NAME + " TEXT, "
                + QUAL_NAME + " TEXT, " + DOB_NAME +
                " TEXT, " + HEIGHT_NAME + " TEXT, " + COLOR_NAME + " TEXT, " + SGOT_NAME +
                " TEXT, " + MGOT_NAME + " TEXT, " + FATHER_NAME + " TEXT, " + MOTHER_NAME + " TEXT,"
                + VILLAGE_NAME + " TEXT, " + MANDAL_NAME+ " TEXT, " + CELL_NAME +
                " TEXT, " + BRO1_NAME + " TEXT, " + BRO2_NAME + " TEXT, " + SIS1_NAME +
                " TEXT, "+ SIS2_NAME + " TEXT, " + P_NAME + " TEXT, " + D_NAME + " TEXT, " +
                SALARY_NAME +" TEXT, " + JOB_AT_NAME + " TEXT);";
        db.execSQL(CREATE_BIO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllProducts(String sortOrder, String columnName, String itemName) {
        allData = new ArrayList<>();
        Cursor cursor = null;
        if(sortOrder.equals("all") && columnName.equals("all")){
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
            SQLiteDatabase database = this.getReadableDatabase();
            cursor = database.rawQuery(selectQuery, null);
//            Log.v("getAllProducts", "all");
        }
        else if (!itemName.isEmpty()){
            String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + columnName + "='" + itemName+"'";
            SQLiteDatabase database = this.getReadableDatabase();
            cursor = database.rawQuery(selectQuery, null);
//            Log.v("getAllProducts", "all with column and item");

        }
        else if(sortOrder.equals(" Desc") && itemName.equals("%") && columnName.equals(Constants.KEY_ID)){
            String selectQuery = "SELECT * FROM " + TABLE_NAME;
            SQLiteDatabase database = this.getReadableDatabase();
            cursor = database.query(TABLE_NAME, new String[]{Constants.KEY_ID, Constants.TNO_NAME, Constants.PERSON_NAME,
                            Constants.QUAL_NAME, Constants.DOB_NAME, Constants.HEIGHT_NAME,
                            Constants.COLOR_NAME, Constants.SGOT_NAME, Constants.MGOT_NAME,
                            Constants.FATHER_NAME, Constants.MOTHER_NAME, Constants.VILLAGE_NAME,
                            Constants.MANDAL_NAME, Constants.CELL_NAME, Constants.BRO1_NAME,
                            Constants.BRO2_NAME, Constants.SIS1_NAME, Constants.SIS2_NAME, Constants.P_NAME,
                            Constants.D_NAME, Constants.SALARY_NAME, Constants.JOB_AT_NAME}, null, null, null, null,
                    columnName + sortOrder);
        }
        else {
            String selectQuery = "SELECT * FROM " + TABLE_NAME;
            SQLiteDatabase database = this.getReadableDatabase();
            cursor = database.query(TABLE_NAME, new String[]{Constants.KEY_ID, Constants.TNO_NAME, Constants.PERSON_NAME,
                            Constants.QUAL_NAME, Constants.DOB_NAME, Constants.HEIGHT_NAME,
                            Constants.COLOR_NAME, Constants.SGOT_NAME, Constants.MGOT_NAME,
                            Constants.FATHER_NAME, Constants.MOTHER_NAME, Constants.VILLAGE_NAME,
                            Constants.MANDAL_NAME, Constants.CELL_NAME, Constants.BRO1_NAME,
                            Constants.BRO2_NAME, Constants.SIS1_NAME, Constants.SIS2_NAME, Constants.P_NAME,
                            Constants.D_NAME, Constants.SALARY_NAME, Constants.JOB_AT_NAME}, null, null, null, null,
                    columnName + sortOrder);
            }
        if (cursor.moveToFirst()) {
            do {
                //Id, Company,Name,Price
//                Log.v("DBHandler", cursor.getCount() + "");
                HashMap<String, String> map = new HashMap<>();
                map.put(Constants.KEY_ID, cursor.getString(0));
                map.put(Constants.TNO_NAME, cursor.getString(1));
                map.put(Constants.PERSON_NAME, cursor.getString(2));
                map.put(Constants.QUAL_NAME, cursor.getString(3));
                map.put(Constants.DOB_NAME, cursor.getString(4));
                map.put(Constants.HEIGHT_NAME, cursor.getString(5));
                map.put(Constants.COLOR_NAME, cursor.getString(6));
                map.put(Constants.SGOT_NAME, cursor.getString(7));
                map.put(Constants.MGOT_NAME, cursor.getString(8));
                map.put(Constants.FATHER_NAME, cursor.getString(9));
                map.put(Constants.MOTHER_NAME, cursor.getString(10));
                map.put(Constants.VILLAGE_NAME, cursor.getString(11));
                map.put(Constants.MANDAL_NAME, cursor.getString(12));
                map.put(Constants.CELL_NAME, cursor.getString(13));
                map.put(Constants.BRO1_NAME, cursor.getString(14));
                map.put(Constants.BRO2_NAME, cursor.getString(15));
                map.put(Constants.SIS1_NAME, cursor.getString(16));
                map.put(Constants.SIS2_NAME, cursor.getString(17));
                map.put(Constants.P_NAME, cursor.getString(18));
                map.put(Constants.D_NAME, cursor.getString(19));
                map.put(Constants.SALARY_NAME, cursor.getString(20));
                map.put(Constants.JOB_AT_NAME, cursor.getString(21));
                allData.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allData;
    }

    public int insert(String table, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        return (int) db.insert(table, null, values);
    }

    public MyData getOneProduct(String columnName, String itemName){
        Cursor cursor = null;
        MyData map = new MyData();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + columnName + "='" + itemName+"'";
        SQLiteDatabase database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //Id, Company,Name,Price
//                Log.v("DBHandler", cursor.getCount() + "");
                map = new MyData();
                map.set_id(Integer.parseInt(cursor.getString(0)));
                map.setTNo(cursor.getString(1));
                map.setName(cursor.getString(2));
                map.setQly(cursor.getString(3));
                map.setDOB(cursor.getString(4));
                map.setHeight(cursor.getString(5));
                map.setColor(cursor.getString(6));
                map.setSGot(cursor.getString(7));
                map.setMGot(cursor.getString(8));
                map.setFatherName(cursor.getString(9));
                map.setMotherName(cursor.getString(10));
                map.setVillage(cursor.getString(11));
                map.setMandal(cursor.getString(12));
                map.setCell(cursor.getString(13));
                map.setBro1(cursor.getString(14));
                map.setBro2(cursor.getString(15));
                map.setSis1(cursor.getString(16));
                map.setSis2(cursor.getString(17));
                map.setP(cursor.getString(18));
                map.setD(cursor.getString(19));
                map.setSalary(cursor.getString(20));
                map.setJobAt(cursor.getString(21));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return map;
    }

    public List<String> getList(String any, String order){
        List<String> anyData = new ArrayList<>();
        String selectQuery = "SELECT " + any + " FROM " + TABLE_NAME + " ORDER BY " + any + order;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                anyData.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

// add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(anyData);
        anyData.clear();
        anyData.addAll(hs);
        Collections.sort(anyData);
        /*for(int i=0;i<anyData.size();i++){
            Log.v("getList", anyData.get(i));
        }*/
        db.close();
        cursor.close();
        return anyData;
    }

    public void insertEachItem(MyData myData) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.TNO_NAME, myData.getTNo());
        values.put(Constants.PERSON_NAME, myData.getName());
        values.put(Constants.QUAL_NAME, myData.getQly());
        values.put(Constants.DOB_NAME, myData.getDOB());
        values.put(Constants.HEIGHT_NAME, myData.getHeight());
        values.put(Constants.COLOR_NAME, myData.getColor());
        values.put(Constants.SGOT_NAME, myData.getSGot());
        values.put(Constants.MGOT_NAME, myData.getMGot());
        values.put(Constants.FATHER_NAME, myData.getFatherName());
        values.put(Constants.MOTHER_NAME, myData.getMotherName());
        values.put(Constants.VILLAGE_NAME, myData.getVillage());
        values.put(Constants.MANDAL_NAME, myData.getMandal());
        values.put(Constants.CELL_NAME, myData.getCell());
        values.put(Constants.BRO1_NAME, myData.getBro1());
        values.put(Constants.BRO2_NAME, myData.getBro2());
        values.put(Constants.SIS1_NAME, myData.getSis1());
        values.put(Constants.SIS2_NAME, myData.getSis2());
        values.put(Constants.P_NAME, myData.getP());
        values.put(Constants.D_NAME, myData.getD());
        values.put(Constants.SALARY_NAME, myData.getSalary());
        values.put(Constants.JOB_AT_NAME, myData.getJobAt());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }

    public void close(){
        SQLiteDatabase db = getWritableDatabase();
        db.close();
    }

    public Cursor getAllRow(String table) {
        SQLiteDatabase db = getReadableDatabase();
        db.close();
        return db.query(table, null, null, null, null, null, KEY_ID);
    }
}
