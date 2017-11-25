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
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.xmltest.VideoYoutube.VerticalAdapter;
import com.example.admin.xmltest.VideoYoutube.ViewAllOnCategory;
import com.example.admin.xmltest.models.Category;
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

public class VideoMenu extends Fragment{
    private VerticalAdapter mAdapter;
    private List<Category> categories;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private TextView tvVideoName;
    private Dialog progressDialog;
    private Spinner spinner;
    private Category favorite;
    private List<String> videoList;
    private AutoCompleteTextView autoSearch;
    private List<String> spinnerList;
    private FirebaseAuth mAuth;
    private ArrayAdapter titleList;
    private String idAcc;
    private List<Video> videoF;
    private ArrayAdapter<String> mAdapterS;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.screen_video, container, false);
        addControls(view);
        setView();
        setData();
        getData();
        addEvents();
        return view;
    }

    private void addControls(View view){
        //import cac view
        recyclerView = (RecyclerView) view.findViewById(R.id.vertical_list_video);
        tvVideoName = (TextView) view.findViewById(R.id.tvVideoName);
        spinner = (Spinner) view.findViewById(R.id.spinnerVideo);
        autoSearch = (AutoCompleteTextView) view.findViewById(R.id.autoSearch);
        //set dialog khi dang load du lieu
        progressDialog=new Dialog(getContext(),R.style.Theme_AppCompat_Dialog);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

    }

    private void setView(){
        //set font chu cho man hinh
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NABILA.TFF");
        tvVideoName.setTypeface(typeface);
        tvVideoName.setText("Kho video");
    }

    private void setData(){
        //tao danh sach cac tham so du lieu dau vao
        categories = new ArrayList<>(); //list danh sach the loai dung cho recycler
        spinnerList = new ArrayList<>();
        videoList = new ArrayList<>();
        //đưa dữ liệu vào adapter
        mAdapter = new VerticalAdapter(getActivity(),categories, "Video");
        //dua du liệu vào adapter cua spinner
        mAdapterS = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,spinnerList);
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
        //set du lieu tu firebase xuong
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        //adapter title cho spinner
        titleList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, videoList);
        //set adapter cho thanh search
        autoSearch.setAdapter(titleList);
        //lay id cua acc
        idAcc = mAuth.getCurrentUser().getUid().toString();
        //tao danh sach yeu thich
        favorite = new Category();
        favorite.setNameType("Đã yêu thích");
        //danh sach video yeu thich
        videoF = new ArrayList<>();
        favorite.setVideos(videoF);
        //them the loai yeu thich vao
        categories.add(favorite);

    }

    private void getData(){
        //lay du lieu tu firebase
        mDatabase.child("Category").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Category category = dataSnapshot.getValue(Category.class); //get doi tuong category ve
                categories.add(category); //them doi tuong vao list
                spinnerList.add(category.getNameType());
                for (int i = 0; i < category.getVideos().size(); i++){
                    videoList.add(category.getVideos().get(i).getTitle());
                }
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
        //lay du lieu cac video yeu thich ve
        mDatabase.child("favorite").child(idAcc).child("Video").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Video video = dataSnapshot.getValue(Video.class);
                videoF.add(video);
                mAdapter.addItems(categories);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //khi unlike video thi xoa video do ra khoi danh sach
                Video videoX = dataSnapshot.getValue(Video.class);
                for (int i = 0; i < videoF.size(); i++){
                    if (videoX.getId() == videoF.get(i).getId()){
                        videoF.remove(i);
                        mAdapter.notifyDataSetChanged();
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    //do nothing
                }
                else {
                    for (int i = 0; i < categories.size(); i++){
                        if (categories.get(i).getNameType() == spinnerList.get(position)){
                            Intent intent = new Intent(getActivity(), ViewAllOnCategory.class);
                            intent.putExtra("videos", categories.get(i));
                            intent.putExtra("TYPE", "Video");
                            getActivity().startActivity(intent);
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        autoSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autoSearch.setText("");
                String titleSearch = titleList.getItem(position).toString();
                for (int i = 0; i < categories.size(); i++){
                    for (int j = 0; j < categories.get(i).getVideos().size(); j++){
                        Video current = categories.get(i).getVideos().get(j);
                        if (current.getTitle() == titleSearch ){
                            Intent intent = new Intent(getActivity(), YoutubePlayer.class);
                            intent.putExtra("ID", current.getId());
                            intent.putExtra("TITLE", current.getTitle());
                            intent.putExtra("TYPE", "Video");
                            getActivity().startActivity(intent);
                        }
                    }

                }
            }
        });
    }
}
