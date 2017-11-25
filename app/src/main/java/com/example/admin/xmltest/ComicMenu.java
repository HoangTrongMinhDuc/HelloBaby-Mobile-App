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
    private FirebaseAuth mAuth;
    private String idAcc;
    private List<Video> videoF;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.screen_comic, container, false);
        addControls(view);
        setView();
        addData();
        getData();
        addEvents();
        return view;
    }

    private void addControls(View view){
        tvTruyen = (TextView)view.findViewById(R.id.tvTRUYEN);
        tvTruyentranh = (TextView)view.findViewById(R.id.tvTruyentranh);
        btnNext = (ImageView) view.findViewById(R.id.btnNextComic);
        recyclerTruyentranh = (RecyclerView) view.findViewById(R.id.recycler_truyentranh);
        recyclerTruyenke = (RecyclerView) view.findViewById(R.id.recycler_truyenke);
    }

    private void setView(){
        //set font cho man hinh
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NABILA.TFF");
        Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MAINFONT2.OTF");
        tvTruyen.setTypeface(typeface);
        tvTruyentranh.setTypeface(typeface2);
        //dialog cho load du lieu
        progressDialog=new Dialog(getContext(),R.style.Theme_AppCompat_Dialog);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void addData(){
        //tao danh sach truyen
        truyens = new ArrayList<>();
        //tao danh sach video cac the loai
        categories = new ArrayList<>();
        //adapter cho truyen ke
        mAdapterTruyenKe = new VerticalAdapter(getActivity(),categories, "Truyen");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerTruyenke.setLayoutManager(mLayoutManager);
        recyclerTruyenke.setHasFixedSize(true);
        recyclerTruyenke.setAdapter(mAdapterTruyenKe);
        //adapter cho truyen
        comicProfileAdapter = new ComicProfileAdapter(getContext(), truyens);
        LinearLayoutManager mLayout = new LinearLayoutManager(getActivity());
        mLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTruyentranh.setLayoutManager(mLayout);
        recyclerTruyentranh.setHasFixedSize(true);
        recyclerTruyentranh.setAdapter(comicProfileAdapter);
        //lay bien lieu tu firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        //id cua acc
        idAcc = mAuth.getCurrentUser().getUid().toString();
        //tao the loai yeu thich
        favorite = new Category();
        favorite.setNameType("Đã yêu thích");
        //danh sach video yeu thich
        videoF = new ArrayList<>();
        favorite.setVideos(videoF);
        //them the loai video yeu thich vao danh sach cac the loai
        categories.add(favorite);
    }

    private void getData(){
        //lay du lieu cac bo truyen tranh
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
        //lay du lieu cac the loai video
        mDatabase.child("KeTruyen").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Category category = dataSnapshot.getValue(Category.class); //get doi tuong category ve
                categories.add(category); //them doi tuong vao list
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
        //lay danh sach cac video yeu thich
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
                //bat video da unlike va xoa no trong danh sach yeu thich
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
    }

    private void addEvents(){
        //khi nhan nut next thi chuyen qua man hinh xem toan bo cac truyen dang co
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(getActivity(), ViewAllComic.class);
                getActivity().startActivity(intentNext);
            }
        });
    }
}
