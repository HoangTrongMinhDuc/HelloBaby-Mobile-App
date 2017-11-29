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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by USER on 11/20/2017.
 */

public class ToanctncActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    ListView lvtest;
    ArrayAdapter adapter=null;
    private ViewPager mVPctnc;
    toanctncAdapter adapterctnc ;
    ArrayList<Toanctncbe10> mlist;
    ArrayList<Toanctncbe10> mlist1;
    EditText edtResultp;
    private TextView tvScore;
    private TextView tvCurrentOverTotal1;
    private TextView tvTopics;
    private int score =0;
    private  int check=0;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toanctnc);
        init();
        setComponents();
        setDefault();
        setEvents();

    }

    private void setEvents() {
        mVPctnc.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        Bundle extras = getIntent().getBundleExtra("data");
        String a1= extras.getString("key1");
        String a2 = extras.getString("key2");
        String a3 = extras.getString("key3");
        mVPctnc.setAdapter(adapterctnc);
        mDatabase.child("MATH").child(a1).child(a2).addValueEventListener(new ValueEventListener(){

            @Override

                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot dt : dataSnapshot.getChildren()){
                        Toanctncbe10 toanctncduoi10 = dt.getValue(Toanctncbe10.class);
                        mlist1.add(toanctncduoi10);
                        long seed = System.nanoTime();
                        Collections.shuffle(mlist1, new Random(seed));
                    }
                    for( Toanctncbe10 tdItoX : mlist1){
                        if (check==10) {
                            check =0;
                            break;
                        }
                        check++;
                        mlist.add(tdItoX);
                    }
                    adapterctnc.notifyDataSetChanged();
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            tvTopics.setText(a3);
    }

    private void setComponents() {
        tvCurrentOverTotal1 = (TextView)findViewById(R.id.tvCurrentOverTotal1);
        edtResultp= (EditText)findViewById(R.id.edtResultp);
        mVPctnc = (ViewPager) findViewById(R.id.vpPctnc);
        tvTopics=(TextView)findViewById(R.id.tvTopics);
        tvScore=(TextView)findViewById(R.id.tvScorectnc);
    }

    private void init() {
        mlist = new ArrayList<Toanctncbe10>();
        mlist1 = new ArrayList<Toanctncbe10>();
        adapterctnc = new toanctncAdapter(this, R.layout.item_phep_toan, mlist);
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }

    public void checkp(View view) {
        //for(int i = 0 ; i< mlist.size();i++){
        if (mlist.get(mVPctnc.getCurrentItem()).getStatus() == 0) {
            mlist.get(mVPctnc.getCurrentItem()).setStatus(1);
            if (edtResultp.getText().toString().equals(mlist.get(mVPctnc.getCurrentItem()).getResult())) {
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
                edtResultp.setText("");
                mVPctnc.setCurrentItem(mVPctnc.getCurrentItem() + 1);
                adapterctnc.notifyDataSetChanged();
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
                edtResultp.setText("");
                mVPctnc.setCurrentItem(mVPctnc.getCurrentItem() + 1);
            }
        }
        else {Toast.makeText(this, "Da dien vao roi", Toast.LENGTH_LONG).show();
            mVPctnc.setCurrentItem(mVPctnc.getCurrentItem() + 1);}
    }
    private void setCurrentOverTotal(int position){

        tvCurrentOverTotal1.setText((position + 1)+"/" + 10);
    }

}

