package com.example.admin.xmltest.Vietnamese.Exercise;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    private ViewPager mVPText;
    com.example.admin.xmltest.Vietnamese.Exercise.textAdapter textAdapter ;
    ArrayList<text> mlist;
    ArrayList<text> mlist1;
    EditText edtResult;
    private TextView tvCurrentOverTotal;
    private int check = 0;
    static int score = 0;
    private TextView mScore;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        mlist = new ArrayList<text>();
        mlist1 = new ArrayList<text>();
        textAdapter = new textAdapter(this, R.layout.item_dem, mlist);

        mVPText = (ViewPager) findViewById(R.id.vpExer);
        mVPText.setAdapter(textAdapter);

        mScore = (TextView) findViewById(R.id.tvScore);
        edtResult= (EditText) findViewById(R.id.edtResult);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Alphabet").child("Luyện tập").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dt : dataSnapshot.getChildren()){
                    text textt = dt.getValue(text.class);
                    mlist1.add(textt);
                }
                for( text abc : mlist1){
                    mlist.add(abc);


                }
                textAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mVPText.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    public void check(View view) {

        if (mlist.get(mVPText.getCurrentItem()).getStatus() == 0) {
            mlist.get(mVPText.getCurrentItem()).setStatus(1);
            if (edtResult.getText().toString().toUpperCase().equals(mlist.get(mVPText.getCurrentItem()).getResult())) {
                Toast.makeText(this, "Ket qua dung", Toast.LENGTH_SHORT).show();

                edtResult.setText("");
                mVPText.setCurrentItem(mVPText.getCurrentItem() + 1);
                Toast.makeText(this, mVPText.getCurrentItem() + "", Toast.LENGTH_SHORT).show();
                score +=1;
                mScore.setText(""+score);
                textAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Ket qua Sai", Toast.LENGTH_LONG).show();
                edtResult.setText("");
                mVPText.setCurrentItem(mVPText.getCurrentItem() + 1);
            }
        }
        else {Toast.makeText(this, "Da dien vao roi", Toast.LENGTH_LONG).show();
            mVPText.setCurrentItem(mVPText.getCurrentItem() + 1);
            edtResult.setText("");}
    }

    private void setCurrentOverTotal(int position){


    }

}

