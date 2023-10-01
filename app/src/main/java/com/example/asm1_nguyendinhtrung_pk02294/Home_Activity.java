package com.example.asm1_nguyendinhtrung_pk02294;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.asm1_nguyendinhtrung_pk02294.activity.LoginActivity;
import com.example.asm1_nguyendinhtrung_pk02294.activity.RegisterActivity;

public class Home_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void onClickLogin(View view) {
        startActivity(new Intent(Home_Activity.this,LoginActivity.class));
        Toast.makeText(Home_Activity.this, "Wellcome Login", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onClickRegister(View view) {
        startActivity(new Intent(Home_Activity.this,RegisterActivity.class));
        Toast.makeText(Home_Activity.this, "Wellcome Register", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onClickNext(View view) {
        startActivity(new Intent(Home_Activity.this,LoginActivity.class));
        Toast.makeText(Home_Activity.this, "Wellcome Login", Toast.LENGTH_SHORT).show();
        finish();
    }
}