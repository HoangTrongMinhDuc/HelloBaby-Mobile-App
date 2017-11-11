package com.example.admin.xmltest.Education;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.xmltest.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ToanDem extends AppCompatActivity {


      DatabaseReference mDatabase;
      ListView lvtest;
      ArrayAdapter adapter=null;
      ArrayList<String> mangId;
      List<String> ids;
      List<String> links;
      List<String> questions;
      List<String> results;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toan_dem);
        lvtest= (ListView) findViewById(R.id.lvTest);
        mangId=new ArrayList<String>();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, mangId);
        lvtest.setAdapter(adapter);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("MATH").child("TẬP ĐẾM").child("TỪ 1 ĐẾN 10").addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            ToanDemIdenX toandem1den10 = dataSnapshot.getValue(ToanDemIdenX.class);
                mangId.add(toandem1den10.getId());
                adapter.notifyDataSetChanged();
            Toast.makeText(getBaseContext(),toandem1den10.getId(),Toast.LENGTH_SHORT).show();
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
}
