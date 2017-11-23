package com.example.admin.xmltest.Comic;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.models.Truyen;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewAllComic extends AppCompatActivity {
    private TextView tvName;
    private GridView gridView;
    private List<Truyen> truyenList;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_all_on_category);
        tvName = (TextView)findViewById(R.id.typeName);
        gridView = (GridView)findViewById(R.id.GridVideo);
        truyenList = new ArrayList<>();

        tvName.setText("Truyện Tranh");
        Typeface typeface = Typeface.createFromAsset(ViewAllComic.this.getAssets(), "fonts/NABILA.TFF");
        tvName.setTypeface(typeface);


        final ViewAllAdapter mAdapter = new ViewAllAdapter(ViewAllComic.this, R.layout.item_truyentranh,truyenList);
        gridView.setNumColumns(3);
        gridView.setAdapter(mAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Comic").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name = dataSnapshot.getKey();
                String thumb = dataSnapshot.child("thumbnail").getValue().toString();
                Truyen truyen = new Truyen();
                truyen.setName(name);
                truyen.setThumbnail(thumb);
                truyenList.add(truyen);
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
