package com.example.admin.xmltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWords;
    Button btnCreate;
    TextView tvLogin;
    Spinner snMotherSon;
    boolean motherOrSon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateAccountActivity.this,"Tạo tài khoản thành công",Toast.LENGTH_LONG).show();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreateAccountActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


    private void addControls() {
        edtUserName= (EditText) findViewById(R.id.edtUserName);
        edtPassWords= (EditText) findViewById(R.id.edtPassWords);
        btnCreate= (Button) findViewById(R.id.btnCreate);
        tvLogin= (TextView) findViewById(R.id.tvLogin);


    }
}
