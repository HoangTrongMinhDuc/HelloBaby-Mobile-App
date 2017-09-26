package com.example.admin.xmltest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWords;
    Button btnCreate;
    TextView tvLogin;
    FirebaseAuth mAuth;
    Spinner snMotherSon;
    boolean motherOrSon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth= FirebaseAuth.getInstance();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTaoTaiKhoan();

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

    private void xuLyTaoTaiKhoan() {
        final String name=edtUserName.getText().toString();
        final String pass=edtPassWords.getText().toString();
        mAuth.createUserWithEmailAndPassword(name  , pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CreateAccountActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(CreateAccountActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            Toast.makeText(CreateAccountActivity.this, "Đăng kí không thành công ",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
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
