package com.example.admin.xmltest.English;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.Math.ToanDemIdenX;
import com.example.admin.xmltest.Math.toandem110Adapter;
import com.example.admin.xmltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by USER on 11/23/2017.
 */

public class alphabetActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    ListView lvtest;
    ArrayAdapter adapter=null;
    private ViewPager mVPalpha;
    alphabetAdapter adapterAl ;
    ArrayList<Alphabet> mlist;
    ArrayList<Alphabet> mlist1;
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

    private void setDefault() {
        Bundle extras = getIntent().getBundleExtra("data");
        String a1= extras.getString("key1");
        String a2 = extras.getString("key2");
        mDatabase.child("ENGLISH").child(a1).child(a2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dt : dataSnapshot.getChildren()){
                    Alphabet alphabet = dt.getValue(Alphabet.class);
                    mlist.add(alphabet);

                }
                adapterAl.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
    }

    private void setEvents() {
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alphabet alpha = mlist.get(mCurrentPageIndex);
                TextReader.getInstance().speak(alpha.getContent());
            }
        });
    }

    private void setComponents() {
        btnSound = (Button)findViewById(R.id.btnSound);
        mVPalpha = (ViewPager) findViewById(R.id.vpAlpha);
        mVPalpha.setAdapter(adapterAl);
        mVPalpha.setCurrentItem(mCurrentPageIndex);
    }

    private void init() {
        mlist = new ArrayList<Alphabet>();
        mlist1 = new ArrayList<Alphabet>();
        mCurrentPageIndex = 0;
        adapterAl = new alphabetAdapter(this, R.layout.item_alphabet, mlist);
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }

}
