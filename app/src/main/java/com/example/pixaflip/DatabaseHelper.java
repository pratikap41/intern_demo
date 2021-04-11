package com.example.pixaflip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "allpdf";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_URL = "URL";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_ISFAV = "ISFAV";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "pdf.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q1 = "Create Table " + TABLE_NAME + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_URL + " TEXT, " + COLUMN_DESCRIPTION + " TEXT , " + COLUMN_ISFAV + " INTEGER DEFAULT 0)";
        db.execSQL(q1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void cleartable() {
        SQLiteDatabase db = this.getWritableDatabase();
        int succ = db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public boolean update(String pdfName, int isfav) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ISFAV, isfav);
        String whereClause = COLUMN_NAME + "= '" + pdfName + "'";
        int succ = db.update(TABLE_NAME, cv, whereClause, null);
        return succ > 0;
    }

    public boolean add(pdf pdfObj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, pdfObj.getName());
        cv.put(COLUMN_URL, pdfObj.getUrl());
        cv.put(COLUMN_DESCRIPTION, pdfObj.getDescription());
        long suc = db.insert(TABLE_NAME, null, cv);
        db.close();
        return suc > 0;

    }


    public List<pdf> getAll() {
        List<pdf> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String pdfName = cursor.getString(1);
                String pdfURL = cursor.getString(2);
                String pdfDescription = cursor.getString(3);
                boolean idFav = cursor.getInt(4) == 1;
                pdf pdfObj = new pdf(pdfName, pdfURL, pdfDescription);
                pdfObj.setIsfav(idFav);
                returnList.add(pdfObj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
