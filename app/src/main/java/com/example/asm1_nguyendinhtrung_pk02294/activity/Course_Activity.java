package com.example.asm1_nguyendinhtrung_pk02294.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asm1_nguyendinhtrung_pk02294.R;

public class Course_Activity extends AppCompatActivity {
    private Button DangKyKH,KhoaHocDDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        DangKyKH = findViewById(R.id.DangKyKH);
        KhoaHocDDK = findViewById(R.id.KhoaHocDDK);

        DangKyKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course_Activity.this,ListCourse_Activity.class);
                intent.putExtra("status",1);
                // tương ứng với lấy tất cả khóa học
                startActivity(intent);
            }
        });

        KhoaHocDDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course_Activity.this,ListCourse_Activity.class);
                intent.putExtra("status",0);
                // tương ứng với lấy khóa học của tôi
                startActivity(intent);
            }
        });

    }
}