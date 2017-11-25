package com.example.admin.xmltest.English;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.admin.xmltest.Math.ToanDemIdenX;
import com.example.admin.xmltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by USER on 11/23/2017.
 */

public class numberActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    ListView lvtest;
    ArrayAdapter adapter=null;
    private ViewPager mVPalpha;
    numberAdapter adapternum ;
    ArrayList<Number> mlist;
    ArrayList<Number> mlist1;
    private Button btnSound;
    private int mCurrentPageIndex;




    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        init();
        setComponents();
        setDefault();
        setEvents();
    }

    private void setEvents() {
        mVPalpha.setCurrentItem(mCurrentPageIndex);
        mVPalpha.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPageIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Number num = mlist.get(mCurrentPageIndex);
                TextReader.getInstance().speak(num.getContent());
            }
        });
    }

    private void setDefault() {
        mVPalpha.setAdapter(adapternum);
        Bundle extras = getIntent().getBundleExtra("data");
        String a1= extras.getString("key1");
        String a2 = extras.getString("key2");
        mDatabase.child("ENGLISH").child(a1).child(a2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Number num = dt.getValue(Number.class);
                    mlist.add(num);
                }
                adapternum.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void setComponents() {
        btnSound = (Button)findViewById(R.id.btnSound);
        mVPalpha = (ViewPager) findViewById(R.id.vpAlpha);
    }
    private void init() {
        mCurrentPageIndex = 0;
        mlist = new ArrayList<Number>();
        adapternum = new numberAdapter(this, R.layout.item_number, mlist);
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }

}
