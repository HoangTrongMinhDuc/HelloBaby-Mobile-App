package com.example.admin.xmltest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.xmltest.models.MotherAccountProfile;
import com.example.admin.xmltest.models.SonAccountProfile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetupAccountActivity extends AppCompatActivity {

    Button btnUpdateProfile;
    EditText edtRealName, edtPhone, edtSonPhone;
    DatabaseReference mData;
    SharedPreferences pre,pre2;
    boolean isMother;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_account);
        init();
        setComponents();
        setDefault();
        setEvents();
    }



    private void init() {
        mData= FirebaseDatabase.getInstance().getReference();
        pre=getSharedPreferences("for", MODE_PRIVATE);
        pre2=getSharedPreferences("login_data",MODE_PRIVATE);
    }

    private void setComponents() {
        edtRealName = (EditText) findViewById(R.id.edtRealName);
        edtPhone = (EditText) findViewById(R.id.edtPhoneNumber);
        edtSonPhone = (EditText) findViewById(R.id.edtSonPhoneNumber);
        btnUpdateProfile = (Button) findViewById(R.id.btnUpdateProfile);
    }

    private void setDefault() {

        String a=pre.getString("forObject","");
        if(a.compareTo("son")==0){
            edtSonPhone.setEnabled(false);
            isMother=false;
        }
        else if(a.compareTo("mom")==0){
            isMother=true;
        }

    }

    private void setEvents() {
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMother){
                    MotherAccountProfile proFile=new MotherAccountProfile();
                    proFile.setUserName(pre2.getString("username",""));
                    proFile.setPhone(edtPhone.getText().toString());
                    proFile.setRealName(edtRealName.getText().toString());
                    proFile.setSonPhone(edtSonPhone.getText().toString());
                    if(pre2.getString("id","")=="") {
                        SharedPreferences.Editor editor = pre2.edit();
                        proFile.setId(mData.child("mother").push().getKey());
                        editor.putString("id",mData.child("mother").push().getKey());
                    }

                    mData.child("mother").push().setValue(proFile);
                }
                else if(!isMother)
                {
                    SonAccountProfile proFile=new SonAccountProfile();
                    proFile.setUserName(pre2.getString("username",""));
                    proFile.setPhone(edtPhone.getText().toString());
                    proFile.setRealName(edtRealName.getText().toString());
                    if(pre2.getString("id","")=="") {
                        SharedPreferences.Editor editor = pre2.edit();
                        proFile.setId(mData.child("son").push().getKey());
                        editor.putString("id",mData.child("son").push().getKey());
                    }
                    mData.child("son").push().setValue(proFile);
                }
                finish();
            }
        });


    }

}
