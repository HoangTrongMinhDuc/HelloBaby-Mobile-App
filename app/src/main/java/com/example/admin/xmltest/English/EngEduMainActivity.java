package com.example.admin.xmltest.English;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.xmltest.Math.EduMainActivity;
import com.example.admin.xmltest.Math.ToanctncActivity;
import com.example.admin.xmltest.R;

/**
 * Created by USER on 11/21/2017.
 */

public class EngEduMainActivity extends AppCompatActivity {
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engedumain);
        TextReader.init(this);
        TextReader.getInstance().speak("");
    }
    public void btnAlphabet(View view) {
        Intent intent = new Intent(EngEduMainActivity.this, alphabetActivity.class);
        Bundle bundle = new Bundle();
        // Các dữ liệu gửi kèm
        bundle.putString("key1","PRONUNCIATION");
        bundle.putString("key2", "ALPHA");
        intent.putExtra("data",bundle);
        startActivity(intent);

    }

    public void btnNumber(View view) {
        Intent intent = new Intent(EngEduMainActivity.this, numberActivity.class);
        Bundle bundle = new Bundle();
        // Các dữ liệu gửi kèm
        bundle.putString("key1","PRONUNCIATION");
        bundle.putString("key2", "NUMBER");
        intent.putExtra("data",bundle);
        startActivity(intent);

    }
    }


