package com.example.admin.xmltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView ytPlayer;
    private final String API_KEY_YT ="AIzaSyAFa3KbCqG5XwyYQrqrWOftUSDc9hy3Ano";
    private String id = "id";
    private String title = "title";
    private TextView tvTieude;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        ytPlayer = (YouTubePlayerView) findViewById(R.id.ytPlayer);
        ytPlayer.initialize(API_KEY_YT, this);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        title = intent.getStringExtra("TITLE");
        tvTieude = (TextView)findViewById(R.id.tvTieudevideo);
        tvTieude.setText(title);
        btnBack = (Button)findViewById(R.id.btnBackvideo);
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
