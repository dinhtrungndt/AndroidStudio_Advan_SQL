package com.example.asm1_nguyendinhtrung_pk02294.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm1_nguyendinhtrung_pk02294.R;
import com.example.asm1_nguyendinhtrung_pk02294.dao.NguoiDungDAO;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtUser, edtPass, edtTen;
    private Button btnLogin, btnRegister;
    boolean passwordEye;
    private NguoiDungDAO nguoiDungDAO;
    private IntentFilter intentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUser = findViewById(R.id.edtUser);
        edtTen = findViewById(R.id.edtTen);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        nguoiDungDAO = new NguoiDungDAO(RegisterActivity.this);

        // Khai báo intentFillter
        intentFilter = new IntentFilter();
        intentFilter.addAction("dangKy");

        edtPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= edtPass.getRight() - edtPass.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = edtPass.getSelectionEnd();
                        if (passwordEye) {
                            // set drawable image here
                            edtPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_off, 0);
                            // for hide password
                            edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordEye = false;
                        } else {
                            // set drawable image here
                            edtPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_onl, 0);
                            // for show password
                            edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordEye = true;
                        }
                        edtPass.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                Toast.makeText(RegisterActivity.this, "Wellcome Login", Toast.LENGTH_SHORT).show();
            }
        });

        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTen.getText().toString();
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                boolean check = nguoiDungDAO.Register(ten, user, pass);
                if (check) {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}