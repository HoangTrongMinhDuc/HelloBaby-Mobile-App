package com.example.admin.xmltest.Math;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ToanDem extends AppCompatActivity {

    DatabaseReference mDatabase;
    ListView lvtest;
    ArrayAdapter adapter=null;
    private ViewPager mVPdem;
    toandem110Adapter adapter110 ;
    ArrayList<ToanDemIdenX> mlist;
    ArrayList<ToanDemIdenX> mlist1;
    EditText edtResultd;
    private TextView tvCurrentOverTotal;
    private TextView tvScore;
    private int check = 0;
    private int score =0;


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toan_dem);
        init();
        setComponents();
        setDefault();
        setEvents();
    }

    private void setEvents() {
        mVPdem.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentOverTotal(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setCurrentOverTotal(0);
    }

    private void setDefault() {
        tvScore.setText(""+score);
        mVPdem.setAdapter(adapter110);
        mDatabase.child("MATH").child("TẬP ĐẾM").child("TỪ 1 ĐẾN 10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dt : dataSnapshot.getChildren()){
                    ToanDemIdenX toandem1den10 = dt.getValue(ToanDemIdenX.class);
                    mlist1.add(toandem1den10);
                    long seed = System.nanoTime();
                    Collections.shuffle(mlist1, new Random(seed));
                }
                for( ToanDemIdenX tdItoX : mlist1){
                    if (check==10) {
                        check =0;
                        break;
                    }
                    check++;
                    mlist.add(tdItoX);
                }
                adapter110.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setComponents() {
        tvCurrentOverTotal = (TextView)findViewById(R.id.tvCurrentOverTotal);
        mVPdem = (ViewPager) findViewById(R.id.vpDem110);
        edtResultd= (EditText) findViewById(R.id.edtResultd);
        tvScore=(TextView)findViewById(R.id.tvScoredem);
    }

    private void init() {

        mlist = new ArrayList<ToanDemIdenX>();
        mlist1 = new ArrayList<ToanDemIdenX>();
        adapter110 = new toandem110Adapter(this, R.layout.item_dem, mlist);
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }

    public void check(View view) {

        if (mlist.get(mVPdem.getCurrentItem()).getStatus() == 0) {
            mlist.get(mVPdem.getCurrentItem()).setStatus(1);
            if (edtResultd.getText().toString().equals(mlist.get(mVPdem.getCurrentItem()).getResult())) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toasttrue, null);
                Toast toast = new Toast(getApplicationContext());
                //Set vị trí hiển thị cho Toast
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                //Thời gian hiển thị của Toast
                toast.setDuration(Toast.LENGTH_LONG);
                //Set layout mycustom_toast_border.xml cho Toast
                toast.setView(layout);
                //hiển thị Toast
                toast.show();
                score+=10;
                tvScore.setText(""+score);
                edtResultd.setText("");
                mVPdem.setCurrentItem(mVPdem.getCurrentItem() + 1);

                adapter110.notifyDataSetChanged();
            } else {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toastfalse, null);
                Toast toast = new Toast(getApplicationContext());
                //Set vị trí hiển thị cho Toast
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                //Thời gian hiển thị của Toast
                toast.setDuration(Toast.LENGTH_SHORT);
                //Set layout mycustom_toast_border.xml cho Toast
                toast.setView(layout);
                //hiển thị Toast
                toast.show();
                edtResultd.setText("");
                mVPdem.setCurrentItem(mVPdem.getCurrentItem() + 1);
            }
        }
        else {Toast.makeText(this, "ĐÃ ĐIỀN VÀO RỒI !!", Toast.LENGTH_LONG).show();
            mVPdem.setCurrentItem(mVPdem.getCurrentItem() + 1);
            edtResultd.setText("");}
    }

    private void setCurrentOverTotal(int position){

        tvCurrentOverTotal.setText((position + 1)+"/" + 10);
    }

    }

