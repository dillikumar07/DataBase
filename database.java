package com.example.myapplication4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "products_details";
    public static final String COL1 = "ID";
    public static final String COL2 = "Name";
    public static final String COL3 = "Price";

    public static final String PRODUCTS_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL2 + " TEXT," +
                    COL3 + " INTEGER)";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(int id, String name, int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1,id);
        cv.put(COL2,name);
        cv.put(COL3,price);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor viewAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }
}
