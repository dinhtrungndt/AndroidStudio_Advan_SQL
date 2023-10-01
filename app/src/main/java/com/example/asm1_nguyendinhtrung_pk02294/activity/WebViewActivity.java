package com.example.asm1_nguyendinhtrung_pk02294.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.asm1_nguyendinhtrung_pk02294.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.webView);

        String link = getIntent().getStringExtra("link");

        webView.loadUrl(link);

    }
}