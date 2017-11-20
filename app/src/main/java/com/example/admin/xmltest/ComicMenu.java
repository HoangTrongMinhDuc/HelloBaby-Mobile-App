package com.example.admin.xmltest;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.xmltest.Comic.TruyenTranhAdapter;
import com.example.admin.xmltest.models.Truyen;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HTML5 on 21/09/2017.
 */

public class ComicMenu extends Fragment{
    private TextView tvTruyen;
    private DatabaseReference mDatabase;
    private List<Truyen> truyens;
    private TruyenTranhAdapter truyenTranhAdapter;
    private RecyclerView recyclerTruyentranh;
    private Dialog progressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.screen_comic, container, false);

        tvTruyen = (TextView)view.findViewById(R.id.tvTRUYEN);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NABILA.TFF");
        tvTruyen.setTypeface(typeface);
        progressDialog=new Dialog(getContext(),R.style.Theme_AppCompat_Dialog);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        recyclerTruyentranh = (RecyclerView) view.findViewById(R.id.recycler_truyentranh);
        truyens = new ArrayList<>();
        truyenTranhAdapter = new TruyenTranhAdapter(getContext(),truyens);
        LinearLayoutManager mLayout = new LinearLayoutManager(getActivity());
        mLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTruyentranh.setLayoutManager(mLayout);
        recyclerTruyentranh.setHasFixedSize(true);
        recyclerTruyentranh.setAdapter(truyenTranhAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Comic").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name = dataSnapshot.getKey();
                String thumb = dataSnapshot.child("thumbnail").getValue().toString();
                Truyen truyen = new Truyen();
                truyen.setName(name);
                truyen.setThumbnail(thumb);
                truyens.add(truyen);
                truyenTranhAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
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



        return view;
    }
}
