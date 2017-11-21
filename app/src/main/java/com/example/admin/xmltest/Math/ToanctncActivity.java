package com.example.admin.xmltest.Math;

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
    EditText edtResultp;


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toanctnc);

        Bundle extras = getIntent().getBundleExtra("data");
        String a1= extras.getString("key1");
        String a2 = extras.getString("key2");
       // Toast.makeText(this, a1 + "/" + a2, Toast.LENGTH_SHORT).show();
        edtResultp= (EditText)findViewById(R.id.edtResultp);
        mlist = new ArrayList<Toanctncbe10>();
        adapterctnc = new toanctncAdapter(this, R.layout.item_phep_toan, mlist);
        mVPctnc = (ViewPager) findViewById(R.id.vpPctnc);
        mVPctnc.setAdapter(adapterctnc);

        adapterctnc.notifyDataSetChanged();

        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("MATH").child(a1).child(a2).addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                long seed = System.nanoTime();
                Collections.shuffle(mlist, new Random(seed));
                Toanctncbe10 toanctncduoi10 = dataSnapshot.getValue(Toanctncbe10.class);
                mlist.add(toanctncduoi10);
                //long seed = System.nanoTime();
                Collections.shuffle(mlist, new Random(seed));
                adapterctnc.notifyDataSetChanged();
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
    public void checkp(View view) {
        //for(int i = 0 ; i< mlist.size();i++){
        if (mlist.get(mVPctnc.getCurrentItem()).getStatus() == 0) {
            mlist.get(mVPctnc.getCurrentItem()).setStatus(1);
            if (edtResultp.getText().toString().equals(mlist.get(mVPctnc.getCurrentItem()).getResult())) {
                Toast.makeText(this, "Ket qua dung", Toast.LENGTH_SHORT).show();
                edtResultp.setText("");
                // int m = mVPdem.getCurrentItem();
                mVPctnc.setCurrentItem(mVPctnc.getCurrentItem() + 1);
                // mlist.remove(m);
                adapterctnc.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Ket qua Sai", Toast.LENGTH_LONG).show();
                edtResultp.setText("");
                mVPctnc.setCurrentItem(mVPctnc.getCurrentItem() + 1);
            }
        }
        else {Toast.makeText(this, "Da dien vao roi", Toast.LENGTH_LONG).show();
            mVPctnc.setCurrentItem(mVPctnc.getCurrentItem() + 1);}
    }

}

