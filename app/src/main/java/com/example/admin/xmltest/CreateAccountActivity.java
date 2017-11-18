package com.example.admin.xmltest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.models.MotherAccountProfile;
import com.example.admin.xmltest.models.SonAccountProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWords;
    Button btnCreate;
    TextView tvLogin;
    FirebaseAuth mAuth;
    DatabaseReference mData;
    Boolean isMother;
    SharedPreferences pre,pre2;
    String id = "";
//    Spinner snMotherSon;
//    boolean motherOrSon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        init();
        setComponents();
        setDefault();
        setEvents();
    }


    private void init() {
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        pre = getSharedPreferences("for", MODE_PRIVATE);
        pre2 = getSharedPreferences("login_data", MODE_PRIVATE);
    }

    private void setDefault() {
        String a = pre.getString("forObject", "");
        if(a.compareTo("son") == 0){
            isMother = false;
        }
        else if(a.compareTo("mom") == 0){
            isMother = true;
        }
    }

    private void setEvents() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTaoTaiKhoan();

            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void xuLyTaoTaiKhoan() {
        final String name=edtUserName.getText().toString();
        final String pass=edtPassWords.getText().toString();


        mAuth.createUserWithEmailAndPassword(name, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CreateAccountActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            dangNhapThu(name, pass);

                        } else {

                            Toast.makeText(CreateAccountActivity.this, "Đăng kí không thành công ",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    private void dangNhapThu(String name, String pass){

        mAuth.signInWithEmailAndPassword(name, pass)
                .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            id = user.getUid().toString();
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(CreateAccountActivity.this, "Đăng nhập thành công"+id,
                                    Toast.LENGTH_SHORT).show();

                            if(isMother){
                                MotherAccountProfile proFile = new MotherAccountProfile();
                                proFile.setUserName(pre2.getString("username", ""));
                                proFile.setPhone("");
                                proFile.setRealName("");
                                proFile.setSonPhone("");
                                proFile.setId(id);
                                mData.child("mother").child(id).setValue(proFile);
                            }
                            else {
                                SonAccountProfile proFile = new SonAccountProfile();
                                proFile.setUserName(pre2.getString("username", ""));
                                proFile.setPhone("");
                                proFile.setRealName("");
                                proFile.setId(id);
                                mData.child("son").child(id).setValue(proFile);

                            }
                            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(CreateAccountActivity.this, "Đăng nhập không thành công",
                                    Toast.LENGTH_SHORT).show();
                        }


                        // ...
                    }
                });


    }


    private void setComponents() {
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassWords = (EditText) findViewById(R.id.edtPassWords);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        tvLogin = (TextView) findViewById(R.id.tvLogin);


    }
}
