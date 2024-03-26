package com.example.netflix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    private String VIDEO_URL;
    private String VIDEO_TITLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        VIDEO_URL = getIntent().getStringExtra("video");
        VIDEO_TITLE = getIntent().getStringExtra("title");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(VIDEO_TITLE);
        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        playerView = findViewById(R.id.video_player);

        Intent intent = getIntent();
        if (intent != null) {
            VIDEO_URL = intent.getStringExtra("video");
        }

        if (VIDEO_URL != null && !VIDEO_URL.isEmpty()) {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(getApplicationContext()).build();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            playerView.setPlayer(simpleExoPlayer);

            try {
                Uri videoUri = Uri.parse(VIDEO_URL);
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                        Util.getUserAgent(this, getString(R.string.app_name)));
                MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);

                simpleExoPlayer.prepare(videoSource);
                simpleExoPlayer.setPlayWhenReady(true);
                playerView.setKeepScreenOn(true);
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý nếu có lỗi khi parse Uri từ VIDEO_URL
                Toast.makeText(this, "Lỗi khi phát video", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Xử lý trường hợp VIDEO_URL là null hoặc rỗng
            Toast.makeText(this, "Không có URL video", Toast.LENGTH_SHORT).show();
            // Ví dụ: Finish activity khi không có URL video
            finish();
        }
    }



    @Override
    protected void onStop() {
        super.onStop();
        simpleExoPlayer.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //back on button click
        if(item.getItemId() == android.R.id.home)
        {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}