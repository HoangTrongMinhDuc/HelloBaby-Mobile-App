package com.example.admin.xmltest.Vietnamese;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.Vietnamese.BangChuCai.BangChuCai;
import com.example.admin.xmltest.Vietnamese.Exercise.ExerciseActivity;
import com.example.admin.xmltest.Vietnamese.Vidu.Vidu;

public class VNEduMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnedu_main);
    }

    public void btnBCC(View view) {
        Intent i = new Intent(VNEduMainActivity.this, BangChuCai.class);
        startActivity(i);
    }

    public void btnVidu(View view) {
        Intent i = new Intent(VNEduMainActivity.this, Vidu.class);
        startActivity(i);
    }
    public void btnExer(View view) {
        Intent i = new Intent(VNEduMainActivity.this, ExerciseActivity.class);
        startActivity(i);
    }
}
