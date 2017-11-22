package com.example.admin.xmltest.Comic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ComicPlayer extends AppCompatActivity {
    private ListView lvComic;
    private TextView tvName, tvChapter;
    private DatabaseReference mDatabase;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_player);
        lvComic = (ListView) findViewById(R.id.lvComic);
        tvName = (TextView) findViewById(R.id.tvNameComic);
        tvChapter = (TextView) findViewById(R.id.tvNameChapter);
        Intent intent = getIntent();
        String name = intent.getStringExtra("TRUYEN");
        int id = intent.getIntExtra("CHUONG",0);
        tvName.setText(name);

        mList = new ArrayList<>();

        final ArrayList<String> arrLink = new ArrayList<>();
        final ComicPlayerAdapter mAdapter = new ComicPlayerAdapter(this,R.layout.item_comic_pic, mList);
        lvComic.setAdapter(mAdapter);
        String numChapter = (id+1) +"";
        tvChapter.setText("Chương "+ numChapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Comic").child(name).child("chuongs").child(id+"").child("listLink")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String link = dataSnapshot.getValue().toString();
                        mList.add(link);
                        mAdapter.notifyDataSetChanged();
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
