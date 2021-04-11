package com.example.pixaflip;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class PdfActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        try {
            webView = findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            final String url = getIntent().getStringExtra("pdfURL");
            webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}