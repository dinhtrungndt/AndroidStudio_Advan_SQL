package com.example.asm1_nguyendinhtrung_pk02294.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asm1_nguyendinhtrung_pk02294.R;
import com.example.asm1_nguyendinhtrung_pk02294.adapter.ListCourseAdapter;
import com.example.asm1_nguyendinhtrung_pk02294.dao.MonHocDAO;
import com.example.asm1_nguyendinhtrung_pk02294.model.MonHoc;
import com.example.asm1_nguyendinhtrung_pk02294.service.ListCourseService;

import java.util.ArrayList;

public class ListCourse_Activity extends AppCompatActivity {

    private RecyclerView recyclerListCourse;
    private IntentFilter intentFilter;
    private Toolbar toolBar;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerListCourse = findViewById(R.id.recyclerListCourse);
        toolBar = findViewById(R.id.toolBar);

        Intent intent = getIntent();
        status = intent.getIntExtra("status", -1);
        if (status == 1){
            toolBar.setTitle("DANH SÁCH KHÓA HỌC");
            toolBar.setBackgroundColor(Color.GREEN);
        } else {
            toolBar.setTitle("KHÓA HỌC CỦA TÔI");
            toolBar.setBackgroundColor(Color.DKGRAY);
        }

        intentFilter = new IntentFilter();
        intentFilter.addAction("LayDSMonHoc");
        intentFilter.addAction("DKvHDMonHoc");

        getDS();

    }

    private void getDS(){
        SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        int idNguoiDung = sharedPreferences.getInt("idNguoiDung", -1);
        Intent intent = new Intent(ListCourse_Activity.this, ListCourseService.class);
        intent.putExtra("idNguoiDung", idNguoiDung);
        intent.putExtra("status",status);
        startService(intent);
    }

    private void loadData(ArrayList<MonHoc> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListCourse_Activity.this);
        recyclerListCourse.setLayoutManager(linearLayoutManager);

        ListCourseAdapter adapter = new ListCourseAdapter(ListCourse_Activity.this, list);
        recyclerListCourse.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(myBroadcast,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcast);
    }

    private BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("LayDSMonHoc")) {
                Bundle bundle = intent.getExtras();
                ArrayList<MonHoc> list = (ArrayList<MonHoc>) bundle.getSerializable("DSMonHoc");
                loadData(list);
            }else if (intent.getAction().equals("DKvHDMonHoc")){
                boolean status = intent.getBooleanExtra("status",false);
                if (status){
                    getDS();
                }else {
                    Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}