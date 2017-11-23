package com.example.admin.xmltest;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.models.MotherAccountProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Provider;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWords;
    Button btnLogin, btnGiaoDuc, btnComic, btnVideo, btnChonlua;
    TextView tvSignUp;
    FirebaseAuth mAuth;
    DatabaseReference mData;
    SharedPreferences pre, pre2;
    Boolean isMother = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED
              ){
            String []permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS
                };
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
        init();
        setComponents();
        setDefault();
        setEvents();

    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        pre = getSharedPreferences("login_data", MODE_PRIVATE);
        pre2 = getSharedPreferences("for", MODE_PRIVATE);
    }

    private void setDefault() {
        String a = pre2.getString("forObject", "");
        if(a.compareTo("son") == 0){
            isMother = false;
        }
        else if(a.compareTo("mom") == 0){
            isMother = true;
        }
    }

    private void setEvents() {
        //khi có dữ liệu rồi thì chạy vào để sang màn hình tiếp


        if(pre.getString("username", "") != "")
        {
            Intent intent = new Intent(MainActivity.this, NavigationDrawer.class);
            startActivity(intent);
            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences pre=getSharedPreferences("login_data",MODE_PRIVATE);
                if(edtPassWords.getText().toString() != "" && edtUserName.getText().toString() != ""){
                SharedPreferences.Editor edit=pre.edit();

                edit.putString("username", edtPassWords.getText().toString());
                edit.putString("password", edtUserName.getText().toString());
                edit.commit();
                
                xuLyDangNhap();
                }

            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });






    }

    private void xuLyDangNhap() {
        String email = edtUserName.getText().toString();
        String password = edtPassWords.getText().toString();
        if(email!=null&&password!=null) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(MainActivity.this, "Đăng nhập thành công",
                                        Toast.LENGTH_SHORT).show();


                                kiemTraTrongProfile(user.getUid().toString());


                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(MainActivity.this, "Đăng nhập không thành công",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    private void kiemTraTrongProfile(final String uid) {

        String child;
        if(isMother)
            child = "mother";
        else
            child = "son";

            mData.child(child).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.child(uid).exists()){
                        SharedPreferences.Editor editor=pre.edit();


                        Toast.makeText(MainActivity.this, "Đăng nhập thành công 2"+uid,
                                Toast.LENGTH_SHORT).show();
                        editor.putString("ID",uid);
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, NavigationDrawer.class);
                        startActivity(intent);
                        finish();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }

    private void setComponents() {
        edtUserName= (EditText) findViewById(R.id.edtUserName);
        edtPassWords= (EditText) findViewById(R.id.edtPassWords);
        btnLogin= (Button) findViewById(R.id.btnLogin);
        tvSignUp= (TextView) findViewById(R.id.tvSignUp);
        btnGiaoDuc = (Button) findViewById(R.id.btnGiaoDuc);
        btnComic=(Button) findViewById(R.id.btnComic);
        btnVideo=(Button) findViewById(R.id.btnVideo);
    }

}
