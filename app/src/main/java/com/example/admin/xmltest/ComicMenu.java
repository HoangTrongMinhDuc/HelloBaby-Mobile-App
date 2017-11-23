package com.example.admin.xmltest;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.xmltest.Comic.ComicProfileAdapter;
import com.example.admin.xmltest.Comic.ViewAllComic;
import com.example.admin.xmltest.VideoYoutube.VerticalAdapter;
import com.example.admin.xmltest.models.Category;
import com.example.admin.xmltest.models.Truyen;
import com.example.admin.xmltest.models.Video;
import com.google.firebase.auth.FirebaseAuth;
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
    private TextView tvTruyen, tvTruyentranh;
    private DatabaseReference mDatabase;
    private List<Truyen> truyens;
    private ComicProfileAdapter comicProfileAdapter;
    private RecyclerView recyclerTruyentranh;
    private Dialog progressDialog;
    private RecyclerView recyclerTruyenke;
    private List<Category> categories;
    private VerticalAdapter mAdapterTruyenKe;
    private ImageView btnNext;
    private Category favorite;
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
        tvTruyentranh = (TextView)view.findViewById(R.id.tvTruyentranh);
        btnNext = (ImageView) view.findViewById(R.id.btnNextComic);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NABILA.TFF");
        Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MAINFONT2.OTF");
        tvTruyen.setTypeface(typeface);
        tvTruyentranh.setTypeface(typeface2);
        progressDialog=new Dialog(getContext(),R.style.Theme_AppCompat_Dialog);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        recyclerTruyentranh = (RecyclerView) view.findViewById(R.id.recycler_truyentranh);
        recyclerTruyenke = (RecyclerView) view.findViewById(R.id.recycler_truyenke);
        truyens = new ArrayList<>();
        categories = new ArrayList<>();
        mAdapterTruyenKe = new VerticalAdapter(getActivity(),categories, "Truyen");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerTruyenke.setLayoutManager(mLayoutManager);
        recyclerTruyenke.setHasFixedSize(true);
        recyclerTruyenke.setAdapter(mAdapterTruyenKe);
        comicProfileAdapter = new ComicProfileAdapter(getContext(), truyens);
        LinearLayoutManager mLayout = new LinearLayoutManager(getActivity());
        mLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTruyentranh.setLayoutManager(mLayout);
        recyclerTruyentranh.setHasFixedSize(true);
        recyclerTruyentranh.setAdapter(comicProfileAdapter);

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
                comicProfileAdapter.notifyDataSetChanged();
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

        mDatabase.child("KeTruyen").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Category category = dataSnapshot.getValue(Category.class); //get doi tuong category ve
                categories.add(category); //them doi tuong vao list
                //sap xep lai doi tuong trong danh sach

                mAdapterTruyenKe.addItems(categories);  //them doi tuong vao adapter
                mAdapterTruyenKe.notifyDataSetChanged(); //notify
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

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String idAcc = mAuth.getCurrentUser().getUid().toString();
        favorite = new Category();
        favorite.setNameType("Đã yêu thích");
        final List<Video> videoF = new ArrayList<>();
        favorite.setVideos(videoF);
        categories.add(favorite);

        mDatabase.child("favorite").child(idAcc).child("Truyen").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Video video = dataSnapshot.getValue(Video.class);
                videoF.add(video);
                mAdapterTruyenKe.addItems(categories);
                mAdapterTruyenKe.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Video videoX = dataSnapshot.getValue(Video.class);
                for (int i = 0; i < videoF.size(); i++){
                    if (videoX.getId() == videoF.get(i).getId()){
                        videoF.remove(i);
                        mAdapterTruyenKe.notifyDataSetChanged();
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(getActivity(), ViewAllComic.class);
                getActivity().startActivity(intentNext);
            }
        });

        return view;
    }
}
