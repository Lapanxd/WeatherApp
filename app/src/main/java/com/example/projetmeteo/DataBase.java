package com.example.projetmeteo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME = "app-meteo";
    private static final String PKEY = "pkey";
    private static final String CITY_NAME = "name";
    private static final String TABLE_NAME = "my_cities";

    private static final String CREATE_TABLE_CITIES="CREATE TABLE " + TABLE_NAME +
            "(" + PKEY + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + CITY_NAME + " VARCHAR(40))";

    private static final String ORIGINAL_CITIES="insert into " + TABLE_NAME + " (" + CITY_NAME + ") VALUES('null')";

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public boolean insertCity(String s) {
        if (readData() == 5 /*|| insertComparison(s, getCities()) == true*/) return false;

        Log.i("APP", "Insert in database");
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("name", s);
        sqLiteDatabase.insertOrThrow(TABLE_NAME, null, values);
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

        return true;
    }

    public void removeCity(String s) {
        Log.i("APP", "Remove data in database");
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.delete(TABLE_NAME, "name = ?", new String[]{s});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @SuppressLint("Range")
    public int readData() {
        Log.i("APP", "Reading number of values in database ...");
        String select = new String("SELECT name from " + TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("APP", "Number of entries: " + cursor.getCount());

        return cursor.getCount();
    }

    @SuppressLint("Range")
    public String[] getCities(){
        String response[] = new String[5];
        String select = new String("SELECT " + CITY_NAME + " from " + TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            int i=0;
            do {
                response[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        return response;
    }

    public boolean insertComparison(String s, String[] cities){
        for (int i=0; i<cities.length; i++) {
            if(cities[i].equals(s)) return true;
        }

        return false;
    }
}
