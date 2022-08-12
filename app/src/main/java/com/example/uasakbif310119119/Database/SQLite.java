package com.example.uasakbif310119119.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//NIM     : 10119119
//NAMA    : MUHAMMAD HADYAN NUR ADABI
//KELAS   : IF-3

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE = "db_catatan";
    private static final String TABLE = "catatan";
    private static final String COL1 = "id";
    private static final String COL2 = "judul";
    private static final String COL3 = "kategori";
    private static final String COL4 = "isi";
    private static final String COL5 = "date";
    private static final String COL6 = "month";
    private static final String COL7 = "year";
    public SQLite(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT, " +
                COL5 + " TEXT, " +
                COL6 + " TEXT, " +
                COL7 + " TEXT" +
                ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
    }
    public boolean insertData(String judul, String kategori, String isi, String date, String month, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL2, judul);
        values.put(COL3, kategori);
        values.put(COL4, isi);
        values.put(COL5, date);
        values.put(COL6, month);
        values.put(COL7, year);
        long results = db.insert(TABLE, null, values);

        return results != -1;
    }
    public boolean updateData(String id, String judul, String kategori, String isi, String date, String month, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, judul);
        contentValues.put(COL3, kategori);
        contentValues.put(COL4, isi);
        contentValues.put(COL5, date);
        contentValues.put(COL6, month);
        contentValues.put(COL7, year);

        long results = db.update(TABLE, contentValues, COL1 + " = ? ", new String[]{id});

        return results != -1;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        return  db.rawQuery("SELECT * FROM " + TABLE, null);
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE, COL1 + " = ? ", new String[]{id});
    }
}
