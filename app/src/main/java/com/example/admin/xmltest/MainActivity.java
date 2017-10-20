package com.example.admin.xmltest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWords;
    Button btnLogin, btnGiaoDuc, btnComic, btnVideo, btnChonlua;
    TextView tvSignUp;
    FirebaseAuth mAuth;
    SharedPreferences pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
        setComponents();
        setEvents();

    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
    }

    private void setEvents() {
        //khi có dữ liệu rồi thì chạy vào để sang màn hình tiếp
        pre = getSharedPreferences("login_data",MODE_PRIVATE);

        if(pre.getString("username","")!="")
        {
            Intent intent=new Intent(MainActivity.this, NavigationDrawer.class);
            startActivity(intent);
            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences pre=getSharedPreferences("login_data",MODE_PRIVATE);
                if(edtPassWords.getText().toString()!=""&&edtUserName.getText().toString()!=""){
                SharedPreferences.Editor edit=pre.edit();

                edit.putString("username",edtPassWords.getText().toString());
                edit.putString("password",edtUserName.getText().toString());
                edit.commit();
                
                xuLyDangNhap();
                }

            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });






    }

    private void xuLyDangNhap() {
        String email = edtUserName.getText().toString();
        String password = edtPassWords.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();


                            SharedPreferences.Editor editor=pre.edit();
                            editor.putString("ID",user.getUid().toString());
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, NavigationDrawer.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Đăng nhập không thành công",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
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
