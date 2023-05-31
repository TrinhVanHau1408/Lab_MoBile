package com.example.playmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
//    private String audioUrl = R.raw.DoLaDo; // Đường dẫn đến file MP3 trên mạng
    private String audioUrl = "https://c1-ex-swe.nixcdn.com/NhacCuaTui2039/KhongBietNenVuiHayBuon-BaoAnhTao-9430785.mp3"; // Đường dẫn đến file MP3 trên mạng
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private Button pauseButton;
    private PlayAudioTask playAudioTask;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        progressBar = findViewById(R.id.progressBar);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new PlayAudioTask(progressBar).execute(audioUrl);
                if (playAudioTask != null) {
                    playAudioTask.resumeAudio();
                } else {
                    playAudioTask = new PlayAudioTask(progressBar);
                    playAudioTask.execute(audioUrl);
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playAudioTask != null) {
                    playAudioTask.pauseAudio();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}