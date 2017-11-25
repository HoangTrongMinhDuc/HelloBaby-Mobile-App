package com.example.admin.xmltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.xmltest.models.Video;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class YoutubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView ytPlayer;
    private final String API_KEY_YT ="AIzaSyAFa3KbCqG5XwyYQrqrWOftUSDc9hy3Ano";
    private String id = "id";
    private String title = "title";
    private TextView tvTieude;
    private Button btnBack;
    private LinearLayout likeVideo;
    private ImageView imgLike;
    private boolean isLike = false;
    private DatabaseReference mDatabase;
    private List<String> mListLike;
    private String idAcc;
    private String typeVideo;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        addControls();
        addData();
        catchData();
        getData();
        addEvents();
    }

    private void addControls(){
        ytPlayer = (YouTubePlayerView) findViewById(R.id.ytPlayer);
        likeVideo = (LinearLayout)findViewById(R.id.btnLike);
        imgLike = (ImageView)findViewById(R.id.imgLike);
        tvTieude = (TextView)findViewById(R.id.tvTieudevideo);
        ytPlayer.initialize(API_KEY_YT, this);
        btnBack = (Button)findViewById(R.id.btnBackvideo);
    }

    private void catchData(){
        //bat du lieu chuyen qua
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        title = intent.getStringExtra("TITLE");
        typeVideo = intent.getStringExtra("TYPE");
    }

    private void addData(){
        //tao danh sach video da like
        mListLike = new ArrayList<>();
        //lay id cua acc va firebase
        mAuth = FirebaseAuth.getInstance();
        idAcc = mAuth.getCurrentUser().getUid().toString();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void getData(){
        //lay du lieu video da like va add vao danh sach da like
        mDatabase.child("favorite").child(idAcc).child(typeVideo).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String idVideo = dataSnapshot.getKey();
                mListLike.add(idVideo);
                //neu video da like thi set man hinh qua trang thai da like
                if(mListLike.contains(id)){
                    imgLike.setImageResource(R.mipmap.like);
                    isLike = true;
                }
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

    private void addEvents(){
        //set title video
        tvTieude.setText(title);
        //bat su kien nhan like
        likeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLike) {
                    imgLike.setImageResource(R.mipmap.unlike);
                    isLike = false;
                    mDatabase.child("favorite").child(idAcc).child(typeVideo).child(id).setValue(null);
                    Toast.makeText(YoutubePlayer.this, "Đã xóa khỏi danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                }
                else{
                    imgLike.setImageResource(R.mipmap.like);
                    isLike = true;
                    Video video = new Video(id,title);
                    mDatabase.child("favorite").child(idAcc).child(typeVideo).child(id).setValue(video);
                    Toast.makeText(YoutubePlayer.this, "Đã thêm vào danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(id);//insert bien id video o day
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        } else {
            String error = String.format("Error initializing YouTube player", youTubeInitializationResult.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }
}
