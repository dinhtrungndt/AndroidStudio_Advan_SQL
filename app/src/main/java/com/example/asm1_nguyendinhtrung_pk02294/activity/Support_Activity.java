package com.example.asm1_nguyendinhtrung_pk02294.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm1_nguyendinhtrung_pk02294.R;
import com.example.asm1_nguyendinhtrung_pk02294.dao.NguoiDungDAO;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Support_Activity extends AppCompatActivity {
    private LinearLayout SupportCourse, SupportMaps,SupportNews,SupportSocial;
    TextView txtOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        SupportCourse  = findViewById(R.id.SupportCourse);
        SupportMaps = findViewById(R.id.SupportMaps);
        SupportNews = findViewById(R.id.SupportNews);
        SupportSocial = findViewById(R.id.SupportSocial);
        txtOut = findViewById(R.id.txtOut);

        SupportCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Support_Activity.this, Course_Activity.class));
                Toast.makeText(Support_Activity.this, "Wellcome Course !!!", Toast.LENGTH_SHORT).show();
            }
        });

        SupportMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Support_Activity.this, Maps_FPT_Activity.class));
                Toast.makeText(Support_Activity.this, "Wellcome Maps !!!", Toast.LENGTH_SHORT).show();
            }
        });

        SupportNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Support_Activity.this, News_Activity.class));
                Toast.makeText(Support_Activity.this, "Wellcome News !!!", Toast.LENGTH_SHORT).show();
            }
        });

        SupportSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Support_Activity.this, ShareContent_Activity.class));
                Toast.makeText(Support_Activity.this, "Wellcome ShareContent !!!", Toast.LENGTH_SHORT).show();
            }
        });
        txtOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Support_Activity.this, LoginActivity.class));
                Toast.makeText(Support_Activity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}