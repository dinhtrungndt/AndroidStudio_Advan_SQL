package com.example.asm1_nguyendinhtrung_pk02294.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm1_nguyendinhtrung_pk02294.database.DbHelper;
import com.example.asm1_nguyendinhtrung_pk02294.model.MonHoc;

import java.util.ArrayList;

public class MonHocDAO {
    private DbHelper dbHelper;
    public MonHocDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    // Lấy danh sách môn học (bao gồm các môn học mà user đăng ký)
    public ArrayList<MonHoc> getDSMonHoc(int id){
        ArrayList<MonHoc> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT mh.code, mh.name, mh.teacher, dk.id FROM MONHOC mh LEFT JOIN DANGKY dk ON mh.code = dk.code AND dk.id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new MonHoc(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                ));
            }while (cursor.moveToNext());
        }
        return list;
    }

    // đăng ký khóa học
    public boolean DangKyKhoaHoc(int idNguoiDung, String code){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",idNguoiDung);
        contentValues.put("code",code);
        long check = sqLiteDatabase.insert("DANGKY",null,contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }

    // hủy đăng ký môn học
    public boolean HuyDangKyMonHoc(int idNguoiDung, String code){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("DANGKY",
                "id = ? AND code = ?",
                new String[]{String.valueOf(idNguoiDung), code});
        if (check == -1){
            return false;
        }
        return true;
    }

    public ArrayList<MonHoc> getMyCourse(int idNguoiDung){
        ArrayList<MonHoc> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT mh.code, mh.name, mh.teacher, dk.id FROM MONHOC mh, DANGKY dk WHERE mh.code = dk.code AND dk.id = ? ", new String[]{String.valueOf(idNguoiDung)});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new MonHoc(cursor.getString(0), cursor.getString(1),cursor.getString(2), cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
