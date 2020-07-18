package Data;

import android.content.ContentValues;
import android.util.Log;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

public class Excel2SQLiteHelper {

    public static void insertExcelToSqlite(DBHandler dbAdapter, Sheet sheet) {

        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            Row row = rit.next();

            ContentValues values = new ContentValues();
            row.getCell(0, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(5, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(6, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(7, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(8, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(9, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(10, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(11, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(12, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(13, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(14, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(15, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(16, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(17, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(18, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(19, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(20, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);

            values.put(Constants.TNO_NAME, row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.PERSON_NAME, row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.QUAL_NAME, row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.DOB_NAME, row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.HEIGHT_NAME, row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.COLOR_NAME, row.getCell(5, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.SGOT_NAME, row.getCell(6, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.MGOT_NAME, row.getCell(7, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.FATHER_NAME, row.getCell(8, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.MOTHER_NAME, row.getCell(9, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.VILLAGE_NAME, row.getCell(10, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.MANDAL_NAME, row.getCell(11, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.CELL_NAME, row.getCell(12, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.BRO1_NAME, row.getCell(13, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.BRO2_NAME, row.getCell(14, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.SIS1_NAME, row.getCell(15, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.SIS2_NAME, row.getCell(16, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.P_NAME, row.getCell(17, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.D_NAME, row.getCell(18, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.SALARY_NAME, row.getCell(19, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Constants.JOB_AT_NAME, row.getCell(20, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            try {
                if (dbAdapter.insert(Constants.TABLE_NAME, values) < 0) {
                    return;
                }
            } catch (Exception ex) {
                Log.d("Exception in importing", ex.getMessage().toString());
            }
        }
    }
}
