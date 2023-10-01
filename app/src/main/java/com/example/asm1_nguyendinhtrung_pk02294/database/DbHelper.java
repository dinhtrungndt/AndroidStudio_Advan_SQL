package com.example.asm1_nguyendinhtrung_pk02294.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, "DANGKYMONHOC", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qNguoiDung = "CREATE TABLE NGUOIDUNG (id integer primary key autoincrement, username text, password text, name text)";
        db.execSQL(qNguoiDung);
        String qMonHoc = "CREATE TABLE MONHOC (code text primary key, name text, teacher text)";
        db.execSQL(qMonHoc);
        String qDangKy = "CREATE TABLE DANGKY (id integer references NGUOIDUNG(id), code text references MONHOC(code))";
        db.execSQL(qDangKy);
        String qThongTin = "CREATE TABLE THONGTIN (id integer primary key autoincrement, code text references MONHOC(code), date text,address text)";
        db.execSQL(qThongTin);

        // data
        String insNguoiDung = "INSERT INTO NGUOIDUNG VALUES(1,'lake','123','Trí Định'),(2,'minhtri','123abc123','Minh Trí')";
        db.execSQL(insNguoiDung);
        String insMonHoc = "INSERT INTO MONHOC VALUES('MOB201','Android Nâng Cao','Nguyễn Trí Định'),('MOB306','React Native','Trần Anh Hùng'),('MOB2041','Dự Án Mẫu','Nguyễn Trí Định')";
        db.execSQL(insMonHoc);
        String insThongTin = "INSERT INTO THONGTIN VALUES(1, 'MOB201', 'Ca 2 - 19/09/2022', 'T1011'),(2, 'MOB201', 'Ca 2 - 21/09/2022', 'T1011'),(3, 'MOB201', 'Ca 2 - 23/09/2022', 'T1011'),(4, 'MOB306', 'Ca 5 - 20/09/2022', 'F204'),(5, 'MOB306', 'Ca 5 - 22/09/2022', 'F204'),(6, 'MOB2041', 'Ca 1 - 20/09/2022', 'Online - https://meet.google.com/rku-beuk-wqu')";
        db.execSQL(insThongTin);
        String insDangKy = "INSERT INTO DANGKY VALUES(1,'MOB201'),(1,'MOB306'),(2,'MOB306')";
        db.execSQL(insDangKy);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1){
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS MONHOC");
            db.execSQL("DROP TABLE IF EXISTS DANGKY");
            db.execSQL("DROP TABLE IF EXISTS THONGTIN");
            onCreate(db);
        }
    }
}
