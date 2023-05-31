package com.example.playmusic;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

public class PlayAudioTask extends AsyncTask<String, Void, Void> {
    private MediaPlayer mediaPlayer;
    private ProgressBar progressBar;

    private int currentPosition = 0;
    private boolean isPaused = false;
    public PlayAudioTask(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    protected Void doInBackground(String... params) {

        String audioUrl = params[0];
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(audioUrl);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mediaPlayer.seekTo(currentPosition);
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onCancelled() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    public void pauseAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            currentPosition = mediaPlayer.getCurrentPosition();
            isPaused = true;
        }
    }

    public void resumeAudio() {
        if (mediaPlayer != null && isPaused) {
            mediaPlayer.start();
            isPaused = false;
        }
    }

}
