package com.example.admin.xmltest.Education;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.xmltest.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ToanDem extends AppCompatActivity {


    DatabaseReference mDatabase;
    ListView lvtest;
    ArrayAdapter adapter=null;
    private ViewPager mVPdem;
    toandem110Adapter adapter110 ;
    ArrayList<ToanDemIdenX> mlist;
    EditText edtResultd;



    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toan_dem);
        mlist = new ArrayList<ToanDemIdenX>();
        adapter110 = new toandem110Adapter(this, R.layout.item_dem, mlist);
        mVPdem = (ViewPager) findViewById(R.id.vpDem110);
        mVPdem.setAdapter(adapter110);

        edtResultd= (EditText) findViewById(R.id.edtResultd);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("MATH").child("TẬP ĐẾM").child("TỪ 1 ĐẾN 10").addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ToanDemIdenX toandem1den10 = dataSnapshot.getValue(ToanDemIdenX.class);
                mlist.add(toandem1den10);
                long seed = System.nanoTime();
                Collections.shuffle(mlist, new Random(seed));
                adapter110.notifyDataSetChanged();
            }



            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            });


    }

    public void check(View view) {
        //for(int i = 0 ; i< mlist.size();i++){
            if(mlist.get(mVPdem.getCurrentItem()).getStatus()==0 &&( edtResultd.getText().toString().equals(mlist.get(mVPdem.getCurrentItem()).getResult()))){
                mlist.get(mVPdem.getCurrentItem()).setStatus(1);
                Toast.makeText(this, "Ket qua dung", Toast.LENGTH_SHORT).show();
                edtResultd.setText("");
                mVPdem.setCurrentItem(mVPdem.getCurrentItem() + 1);
                mlist.remove(mVPdem.getCurrentItem()-1);
                adapter110.notifyDataSetChanged();
            }
            else
            {
                edtResultd.setText("");
                mVPdem.setCurrentItem(mVPdem.getCurrentItem() + 1);
            }
        }

    }

