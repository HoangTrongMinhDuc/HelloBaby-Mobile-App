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

import com.example.admin.xmltest.VideoYoutube.VerticalAdapter;
import com.example.admin.xmltest.models.Category;
import com.example.admin.xmltest.models.Video;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by HTML5 on 21/09/2017.
 */

public class VideoMenu extends Fragment{
    private VerticalAdapter mAdapter;
    private List<Category> categories, temp;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private List<Video> videos;
    private TextView tvVideoName;
    private Dialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.screen_video, container, false);

        progressDialog=new Dialog(getContext(),R.style.Theme_AppCompat_Dialog);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        recyclerView = (RecyclerView) view.findViewById(R.id.vertical_list_video);
        tvVideoName = (TextView) view.findViewById(R.id.tvVideoName);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NABILA.TFF");

        tvVideoName.setTypeface(typeface);
        tvVideoName.setText("Kho video");
        //tao danh sach cac tham so du lieu dau vao
        categories = new ArrayList<>();
        temp = new ArrayList<>();
        videos = new ArrayList<>();

        //đưa dữ liệu vào adapter
        mAdapter = new VerticalAdapter(getActivity(),categories);
        //set bố cục dọc cho adapter dọc
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        //set du liệu hiển thị của recycler
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        //fake du liệu
        Video v = new Video("Wiw-WtjtOT4","Doraemon Tập 259 - Vòng Hào Quang Cảm Kích, Người Ấy Ở Đại Sảnh - Hoạt Hình Tiếng Việt");
        videos = Arrays.asList(v,v,v,v);

        Category c = new Category("amnhac", "Âm Nhạc", videos);




        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Category").child("hoahinh").child("videos").child("3").setValue(v);
        mDatabase.child("Category").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Category category = dataSnapshot.getValue(Category.class);
                categories.add(category);
                Collections.sort(categories, new Comparator<Category>() {
                    @Override
                    public int compare(Category o1, Category o2) {
                        return o1.getType().compareTo(o2.getType());
                    }
                });
                mAdapter.addItems(categories);
                mAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                //Toast.makeText(getContext(),category.getNameType()+category.getVideos().get(1).getTitle(),Toast.LENGTH_SHORT).show();
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
