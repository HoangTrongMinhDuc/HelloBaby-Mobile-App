package com.example.admin.xmltest.English;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.xmltest.Math.ToanDemIdenX;
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
 * Created by USER on 11/29/2017.
 */

public class praticeAlphabetActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    ListView lvtest;
    ArrayAdapter adapter=null;
    private ViewPager mVPalpha;
    praticeAlphabetAdapter adapterAl ;
    ArrayList<Alphabet> mlist;
    ArrayList<Alphabet> mlist1;
    private Button btnSound;
    private int mCurrentPageIndex;
    EditText edtResultd;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_pratice);
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
                    mlist1.add(alphabet);
                    long seed = System.nanoTime();
                    Collections.shuffle(mlist1, new Random(seed));
                }
                for( Alphabet tdItoX : mlist1){
                    mlist.add(tdItoX);
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
        btnSound = (Button)findViewById(R.id.btnSoundPra);
        mVPalpha = (ViewPager) findViewById(R.id.vpAlphaPra);
        mVPalpha.setAdapter(adapterAl);
        mVPalpha.setCurrentItem(mCurrentPageIndex);
        edtResultd=(EditText)findViewById(R.id.edtResulpra);
    }

    private void init() {

        mlist = new ArrayList<Alphabet>();
        mlist1 = new ArrayList<Alphabet>();
        mCurrentPageIndex = 0;
        adapterAl = new praticeAlphabetAdapter(this, R.layout.item_alphabet_pratice_, mlist);
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }
    public void checkpra(View view) {

        if (mlist.get(mVPalpha.getCurrentItem()).getStatus() == 0) {
            mlist.get(mVPalpha.getCurrentItem()).setStatus(1);
            if (edtResultd.getText().toString().toUpperCase().equals(mlist.get(mVPalpha.getCurrentItem()).getContent())) {
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
                edtResultd.setText("");
                mVPalpha.setCurrentItem(mVPalpha.getCurrentItem() + 1);

                adapterAl.notifyDataSetChanged();
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
                mVPalpha.setCurrentItem(mVPalpha.getCurrentItem() + 1);
            }
        }
        else {Toast.makeText(this, "Da dien vao roi", Toast.LENGTH_LONG).show();
            mVPalpha.setCurrentItem(mVPalpha.getCurrentItem() + 1);
            edtResultd.setText("");}
    }

}

