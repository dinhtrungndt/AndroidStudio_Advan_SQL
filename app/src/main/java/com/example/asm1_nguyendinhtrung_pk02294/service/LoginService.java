package com.example.asm1_nguyendinhtrung_pk02294.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.asm1_nguyendinhtrung_pk02294.dao.NguoiDungDAO;

public class LoginService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("user");
        String pass = bundle.getString("pass");
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        int idNguoiDung = nguoiDungDAO.Login(user,pass);

        // gửi qua broadcase
        Intent intentBR = new Intent();
        intentBR.setAction("dangNhap");
        Bundle bundleBR = new Bundle();
        bundleBR.putInt("idNguoiDung",idNguoiDung);
        intentBR.putExtras(bundleBR);
        sendBroadcast(intentBR);

        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
