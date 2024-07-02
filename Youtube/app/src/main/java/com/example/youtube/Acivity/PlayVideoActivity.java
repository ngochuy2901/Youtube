package com.example.youtube.Acivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.youtube.Model.Video;
import com.example.youtube.R;

public class PlayVideoActivity extends AppCompatActivity {
    private VideoView videoView;
    private Intent olderIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        initView();
    }

    private void initView() {
        videoView = findViewById(R.id.video);
        olderIntent = getIntent();
        Video video = (Video)getIntent().getSerializableExtra ("video");
        // Thiết lập đường dẫn video, ví dụ từ URL hoặc tệp nội bộ
        Uri videoUri = Uri.parse(video.getUrl());

        // Cài đặt video URI cho VideoView
        videoView.setVideoURI(videoUri);

        // Thêm các điều khiển phát lại như play, pause, forward, rewind
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        // Bắt đầu phát video
        videoView.start();
    }
}