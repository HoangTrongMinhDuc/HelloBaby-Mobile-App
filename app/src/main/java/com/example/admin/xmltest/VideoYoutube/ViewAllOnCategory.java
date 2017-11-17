package com.example.admin.xmltest.VideoYoutube;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.models.Category;
import com.example.admin.xmltest.models.Video;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


public class ViewAllOnCategory extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private GridVideoAdapter mAdapter;
    private List<Video> videos;
    private String typeName;
    private GridView gridView;
    private TextView tvTypeName;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_all_on_category);

        gridView = (GridView)findViewById(R.id.GridVideo);
        Intent intent = getIntent();
        Category category = (Category) intent.getSerializableExtra("videos");
        List<Video> videos = category.getVideos();
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/NABILA.TFF");
        tvTypeName = (TextView)findViewById(R.id.typeName);
        tvTypeName.setText(category.getNameType());
        tvTypeName.setTypeface(typeface);
        final ArrayList<Video> arrV = new ArrayList<>();
        //final Video video = new Video("7rM6np-DECM","Con Ghẻ - Bé Thanh Hằng | Nhạc Thiếu Nhi [MV 2017]");
        for(int i = 0; i < videos.size(); i++){
            arrV.add(videos.get(i));
        }
        final GridVideoAdapter mAdapter = new GridVideoAdapter(ViewAllOnCategory.this,R.layout.item_video, arrV);
        gridView.setAdapter(mAdapter);
    }
}