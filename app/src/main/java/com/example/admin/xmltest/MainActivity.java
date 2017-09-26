package com.example.admin.xmltest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWords;
    Button btnLogin, btnGiaoDuc,btnComic,btnVideo,btnChonlua;
    TextView tvSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addEvents() {
        //khi có dữ liệu rồi thì chạy vào để sang màn hình tiếp
        final SharedPreferences pre=getSharedPreferences("login_data",MODE_PRIVATE);

        if(pre.getString("username","")!="")
        {
            Intent intent=new Intent(MainActivity.this,NavigationDrawer.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences pre=getSharedPreferences("login_data",MODE_PRIVATE);
                Intent intent=new Intent(MainActivity.this,NavigationDrawer.class);
                intent.putExtra("username",edtPassWords.getText().toString());
                intent.putExtra("password",edtUserName.getText().toString());
                SharedPreferences.Editor edit=pre.edit();

                edit.putString("username",edtPassWords.getText().toString());
                edit.putString("password",edtUserName.getText().toString());
                edit.commit();
                startActivity(intent);
                finish();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });






    }

    private void addControls() {
        edtUserName= (EditText) findViewById(R.id.edtUserName);
        edtPassWords= (EditText) findViewById(R.id.edtPassWords);
        btnLogin= (Button) findViewById(R.id.btnLogin);
        tvSignUp= (TextView) findViewById(R.id.tvSignUp);
        btnGiaoDuc = (Button) findViewById(R.id.btnGiaoDuc);
        btnComic=(Button) findViewById(R.id.btnComic);
        btnVideo=(Button) findViewById(R.id.btnVideo);
    }

}
