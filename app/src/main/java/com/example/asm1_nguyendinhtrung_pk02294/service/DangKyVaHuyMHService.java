package com.example.asm1_nguyendinhtrung_pk02294.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.asm1_nguyendinhtrung_pk02294.dao.MonHocDAO;

public class DangKyVaHuyMHService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String code = bundle.getString("code");
        int idNguoiDung = bundle.getInt("idNguoiDung");
        int type = bundle.getInt("type");

        MonHocDAO monHocDAO = new MonHocDAO(this);
        boolean status = false;
        // type: 1: đăng ký, 0: Hủy đăng ký
        if (type == 1){
            status = monHocDAO.DangKyKhoaHoc(idNguoiDung,code);
        }else {
            status = monHocDAO.HuyDangKyMonHoc(idNguoiDung,code);
        }

        Intent intentBR = new Intent();
        intentBR.putExtra("status",status);
        intentBR.setAction("DKvHDMonHoc");
        sendBroadcast(intentBR);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
