package com.example.asm1_nguyendinhtrung_pk02294.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.asm1_nguyendinhtrung_pk02294.dao.MonHocDAO;
import com.example.asm1_nguyendinhtrung_pk02294.model.MonHoc;

import java.util.ArrayList;

public class ListCourseService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int idNguoiDung = intent.getIntExtra("idNguoiDung", -1);
        int status = intent.getIntExtra("status",-1);
        MonHocDAO monHocDAO = new MonHocDAO(this);
        ArrayList<MonHoc> list = new ArrayList<>();
        if (status == 1){
            list = monHocDAO.getDSMonHoc(idNguoiDung);
        } else {
            list = monHocDAO.getMyCourse(idNguoiDung);
        }

        Intent intentBR = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("DSMonHoc", list);
        intentBR.putExtras(bundle);
        intentBR.setAction("LayDSMonHoc");
        sendBroadcast(intentBR);
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
