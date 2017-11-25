package com.example.admin.xmltest.Vietnamese.Vidu;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.xmltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Vidu extends AppCompatActivity {

    DatabaseReference mDatabase;
    ListView lvAlphabet;
    ArrayAdapter adapter = null;
    private ViewPager mVPAlpha;
    ImageAdapter alphabetAdapter;
    ArrayList<Image> mList,mList1;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidu);

        mList = new ArrayList<Image>();
        mList1 = new ArrayList<Image>();
        alphabetAdapter = new ImageAdapter(this, R.layout.item_bcc, mList);
        mVPAlpha = (ViewPager) findViewById(R.id.vpAlphabet);
        mVPAlpha.setAdapter(alphabetAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Alphabet").child("Bảng chữ cái").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dt : dataSnapshot.getChildren())
                {
                    Image image = dt.getValue(Image.class);
                    mList1.add(image);
                }
                for(Image bcc : mList1)
                {
                    mList.add(bcc);
                }
                alphabetAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mVPAlpha.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}