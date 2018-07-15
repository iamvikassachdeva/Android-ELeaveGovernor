package com.example.sachdeva.eleavegovernor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sachdeva on 01-Apr-18.
 */

public class SessionHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "LEAVE_GOVERNOR";
    public static final String TABLE_NAME = "SESSION_HANDLER";
    public static final String username = "USERNAME";
    public static final String password = "PASSWORD";
    public static final String status = "STATUS";

    public SessionHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (USERNAME TEXT PRIMARY KEY NOT NULL, PASSWORD TEXT NOT NULL, STATUS TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(db);
    }
    public boolean insertData(String username, String password, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.username,username);
        contentValues.put(this.password,password);
        contentValues.put(this.status,status);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updateData(String username, String password, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.username,username);
        contentValues.put(this.password,password);
        contentValues.put(this.status,status);
        long result = db.update(TABLE_NAME, contentValues,"USERNAME = ?",new String[]{username});
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where "+status+" = \"TRUE\";",null);
        return  cursor;
    }
    public Integer deleteData(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"USERNAME = ?", new String[]{username});
    }
}
