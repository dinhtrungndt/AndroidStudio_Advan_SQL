package com.example.asm1_nguyendinhtrung_pk02294.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asm1_nguyendinhtrung_pk02294.database.DbHelper;

public class NguoiDungDAO {
    private DbHelper dbHelper;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public int Login(String user, String pass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE username = ? AND password = ?", new String[]{user,pass});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return -1;
    }

    public boolean Register(String user, String pass,String name){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",pass);
        contentValues.put("name",name);
        Long check = sqLiteDatabase.insert("NGUOIDUNG",null,contentValues);
        if(check == -1) return false;

        return true;

    }

}
