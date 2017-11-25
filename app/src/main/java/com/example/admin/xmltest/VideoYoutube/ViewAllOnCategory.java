package com.example.admin.xmltest.VideoYoutube;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.YoutubePlayer;
import com.example.admin.xmltest.models.Category;
import com.example.admin.xmltest.models.Video;

import java.util.ArrayList;
import java.util.List;


public class ViewAllOnCategory extends AppCompatActivity {
    private GridView gridView;
    private TextView tvTypeName;
    private Category category;
    private List<Video> videos;
    private String typeVideo;
    private GridVideoAdapter mAdapter;
    private ArrayList<Video> arrV;
    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_all_on_category);
        catchData();
        addControls();
        getData();
        setView();
        addEvents();
    }

    private void catchData(){
        //bat du lieu chuyen qua
        Intent intent = getIntent();
        category = (Category) intent.getSerializableExtra("videos");
        typeVideo = intent.getStringExtra("TYPE");
        videos = category.getVideos();
    }

    private void addControls(){
        gridView = (GridView) findViewById(R.id.GridVideo);
    }

    private void getData(){
        //copy du lieu vao arraylist
        arrV = new ArrayList<>();
        for(int i = 0; i < videos.size(); i++){
            arrV.add(videos.get(i));
        }
        mAdapter = new GridVideoAdapter(ViewAllOnCategory.this,R.layout.item_video, arrV);
    }

    private void setView(){
        //set font cho man hinh
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/NABILA.TFF");
        tvTypeName = (TextView)findViewById(R.id.typeName);
        tvTypeName.setText(category.getNameType());
        tvTypeName.setTypeface(typeface);
        //set adapter cho gridView
        gridView.setAdapter(mAdapter);
    }
    private void addEvents(){
        //bat su kien khi chon vao item video thi chuyen qua man hinh player
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewAllOnCategory.this, YoutubePlayer.class);
                intent.putExtra("ID", arrV.get(position).getId());
                intent.putExtra("TITLE", arrV.get(position).getTitle());
                intent.putExtra("TYPE", typeVideo);
                startActivity(intent);
            }
        });
    }
}