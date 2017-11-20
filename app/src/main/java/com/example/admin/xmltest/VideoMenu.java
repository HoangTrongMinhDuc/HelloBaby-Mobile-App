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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.VideoYoutube.VerticalAdapter;
import com.example.admin.xmltest.VideoYoutube.ViewAllOnCategory;
import com.example.admin.xmltest.models.Category;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by HTML5 on 21/09/2017.
 */

public class VideoMenu extends Fragment{
    private VerticalAdapter mAdapter;
    private List<Category> categories;
    private List<Category> tempList;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private TextView tvVideoName;
    private Dialog progressDialog;
    private Spinner spinner;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.screen_video, container, false);

        //set dialog khi dang load du lieu
        progressDialog=new Dialog(getContext(),R.style.Theme_AppCompat_Dialog);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        //import cac view
        recyclerView = (RecyclerView) view.findViewById(R.id.vertical_list_video);
        tvVideoName = (TextView) view.findViewById(R.id.tvVideoName);
        spinner = (Spinner) view.findViewById(R.id.spinnerVideo);
        //set font cho tieu de
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NABILA.TFF");
        tvVideoName.setTypeface(typeface);
        tvVideoName.setText("Kho video");
        //tao danh sach cac tham so du lieu dau vao
        categories = new ArrayList<>(); //list danh sach the loai dung cho recycler
        tempList = new ArrayList<>();
        final List<String> spinnerList = new ArrayList<>();
        //đưa dữ liệu vào adapter
        mAdapter = new VerticalAdapter(getActivity(),categories);
        //dua du liệu vào adapter cua spinner
        ArrayAdapter<String> mAdapterS = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,spinnerList);
        mAdapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //dua du lieu vao adapter cua spinner
        spinnerList.add("Chọn thể loại");
        spinner.setAdapter(mAdapterS);

        //set bố cục dọc cho adapter dọc
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        //set du liệu hiển thị của recycler
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        //lay du lieu tu firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(mDatabase == null ) Toast.makeText(getActivity(),"loi firebase",Toast.LENGTH_SHORT).show();
        else
        {
            mDatabase.child("Category").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Category category = dataSnapshot.getValue(Category.class); //get doi tuong category ve
                    categories.add(category); //them doi tuong vao list
                    spinnerList.add(category.getNameType());
                    //sap xep lai doi tuong trong danh sach
                    Collections.sort(categories, new Comparator<Category>() {
                        @Override
                        public int compare(Category o1, Category o2) {
                            return o1.getType().compareTo(o2.getType());
                        }
                    });

                    mAdapter.addItems(categories);  //them doi tuong vao adapter
                    mAdapter.notifyDataSetChanged(); //notify
                    progressDialog.dismiss(); //neu da load xong du lieu thi tat dialog
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

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0){
                        return;
                    }
                    else {

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    return;
                }
                else {
                    for (int i = 0; i < categories.size(); i++){
                        if (categories.get(i).getNameType() == spinnerList.get(position)){
                            Intent intent = new Intent(getActivity(), ViewAllOnCategory.class);
                            intent.putExtra("videos", categories.get(i));
                            getActivity().startActivity(intent);
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}
