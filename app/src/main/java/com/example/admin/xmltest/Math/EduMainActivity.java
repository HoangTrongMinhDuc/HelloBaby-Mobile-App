package com.example.admin.xmltest.Math;

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
        Intent intent = new Intent(EduMainActivity.this, ToanctncActivity.class);
        Bundle bundle = new Bundle();
        // Các dữ liệu gửi kèm
        bundle.putString("key1","PHÉP CỘNG");
        bundle.putString("key2", "CÁC SỐ DƯỚI 10");
        intent.putExtra("data",bundle);
        startActivity(intent);

    }

    public void btnTru(View view) {
        Intent intent = new Intent(EduMainActivity.this, ToanctncActivity.class);
        Bundle bundle = new Bundle();
        // Các dữ liệu gửi kèm
        bundle.putString("key1","PHÉP TRỪ");
        bundle.putString("key2", "CÁC SỐ DƯỚI 10");
        intent.putExtra("data",bundle);
        startActivity(intent);

    }

    public void btnNhan(View view) {
        Intent intent = new Intent(EduMainActivity.this, ToanctncActivity.class);
        Bundle bundle = new Bundle();
        // Các dữ liệu gửi kèm
        bundle.putString("key1","PHÉP NHÂN");
        bundle.putString("key2", "CÁC SỐ DƯỚI 10");
        intent.putExtra("data",bundle);
       startActivity(intent);
    }
    public void btnChia(View view) {
        Intent intent = new Intent(EduMainActivity.this, ToanctncActivity.class);
        Bundle bundle = new Bundle();
        // Các dữ liệu gửi kèm
        bundle.putString("key1","PHÉP CHIA");
        bundle.putString("key2", "CÁC SỐ DƯỚI 10");
        intent.putExtra("data",bundle);
        startActivity(intent);

    }




}
