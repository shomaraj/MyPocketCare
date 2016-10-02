package com.shoma.mypocketcare.DataIncomeExpense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.shoma.mypocketcare.model.ViewIncome;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 9/29/2016.
 */
public class PocketDatabase {
    private SQLiteDatabase database;
    private PocketDbHelper helper;

    public PocketDatabase(Context context) {
        helper = new PocketDbHelper(context);
    }

    private SQLiteDatabase openReadableDatabaseInstance() {
        return helper.getReadableDatabase();
    }

    private SQLiteDatabase openWritableDatabaseInstance() {
        return helper.getWritableDatabase();
    }

    private void closeDatabaseConnection() {
        database.close();
        helper.close();
    }

    public long createIncomeDetails(String category, String description,String date,int amount) {

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyPocketContract.IncomeEntry.COLUMN_INCOME_CATEGORY, "" + category);
        contentValues.put(MyPocketContract.IncomeEntry.COLUMN_INCOME_DESCRIPTION, "" + description);
        contentValues.put(MyPocketContract.IncomeEntry.COLUMN_INCOME_DATE, "" + date);
        contentValues.put(MyPocketContract.IncomeEntry.COLUMN_INCOME_AMOUNT, "" + amount);


        long value = database.insert(MyPocketContract.IncomeEntry.TABLE_NAME, null, contentValues);

        closeDatabaseConnection();
        return value;
    }

    public ArrayList<ViewIncome> getAllIncomeDetails() {

        database = openReadableDatabaseInstance();

        Cursor c = database.query(MyPocketContract.IncomeEntry.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<ViewIncome> arrayList = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(MyPocketContract.IncomeEntry._ID));
                String category = c.getString(c.getColumnIndex(MyPocketContract.IncomeEntry.COLUMN_INCOME_CATEGORY));
                String desc = c.getString(c.getColumnIndex(MyPocketContract.IncomeEntry.COLUMN_INCOME_DESCRIPTION));
                String date = c.getString(c.getColumnIndex(MyPocketContract.IncomeEntry.COLUMN_INCOME_DATE));
                double amount = c.getDouble(c.getColumnIndex(MyPocketContract.IncomeEntry.COLUMN_INCOME_AMOUNT));

                ViewIncome viewIncome = new ViewIncome(id, category, desc, date, amount);
                arrayList.add(viewIncome);

            }while (c.moveToNext());
        }

        return arrayList;
    }

    private class PocketDbHelper extends SQLiteOpenHelper {

     //region SQL Statements
     private static final String SQL_CREATE_INCOME_DETAILS_TABLE = "CREATE TABLE " + MyPocketContract.IncomeEntry.TABLE_NAME + "("
             + MyPocketContract.IncomeEntry._ID + " INTEGER PRIMARY KEY, "
             + MyPocketContract.IncomeEntry.COLUMN_INCOME_CATEGORY + " TEXT NOT NULL, "
             + MyPocketContract.IncomeEntry.COLUMN_INCOME_DESCRIPTION + " TEXT , "
             + MyPocketContract.IncomeEntry.COLUMN_INCOME_DATE + " TEXT NOT NULL, "
             + MyPocketContract.IncomeEntry.COLUMN_INCOME_AMOUNT + " NUMBER NOT NULL);";

     private static final String SQL_DROP_INCOME_DETAILS_TABLE = "DROP TABLE " + MyPocketContract.IncomeEntry.TABLE_NAME + ";";
     //endregion

     private static final String DATABASE_NAME = "IncomeDetails.db";

     private static final int DATABASE_VERSION = 2;

     public PocketDbHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
     }

     @Override
     public void onCreate(SQLiteDatabase db) {
         db.execSQL(SQL_CREATE_INCOME_DETAILS_TABLE);
     }

     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         if (newVersion > oldVersion) {
             db.execSQL(SQL_DROP_INCOME_DETAILS_TABLE);
             onCreate(db);
         }
     }
 }

}