package com.example.admin.xmltest.Education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.xmltest.R;

public class EduMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edumain);
    }

    public void btnDem(View view) {
        Intent i = new Intent(EduMainActivity.this, ToanDem.class);
        startActivity(i);
    }

    public void btnCong(View view) {
        Intent i = new Intent(EduMainActivity.this, ToanCong.class);
        startActivity(i);
    }

    public void btnTru(View view) {
        Intent i = new Intent(EduMainActivity.this, ToanTru.class);
        startActivity(i);
    }

    public void btnNhan(View view) {
        Intent i = new Intent(EduMainActivity.this, ToanNhan.class);
        startActivity(i);
    }
    public void btnChia(View view) {
        Intent i = new Intent(EduMainActivity.this, ToanChia.class);
        startActivity(i);
    }




}
