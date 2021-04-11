package com.example.pixaflip;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(VideoActivity.this);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath("content://com.example.pixaflip/video.mp4");
        videoView.start();
    }
}