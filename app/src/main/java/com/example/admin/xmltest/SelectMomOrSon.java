package com.example.admin.xmltest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectMomOrSon extends AppCompatActivity {

    Button btnForMom,btnForSon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mom_or_son);

        addControls();
        addEvents();
    }

    private void addEvents() {
        final SharedPreferences pre=getSharedPreferences("for",MODE_PRIVATE);
        if(pre.getString("forObject","")!="") {
            Intent intent=new Intent(SelectMomOrSon.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
            ;
        final SharedPreferences.Editor edit=pre.edit();
        btnForSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("forObject","son");
                edit.commit();
                Intent intent=new Intent(SelectMomOrSon.this,MainActivity.class);//?????
                startActivity(intent);
                finish();
            }
        });
        btnForMom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("forObject","mom");
                edit.commit();
                Intent intent=new Intent(SelectMomOrSon.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addControls() {
        btnForMom= (Button) findViewById(R.id.btnForMom);
        btnForSon= (Button) findViewById(R.id.btnForSon);
    }
}
