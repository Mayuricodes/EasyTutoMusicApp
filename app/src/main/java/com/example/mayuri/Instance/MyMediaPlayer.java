package com.example.mayuri.Instance;

import android.media.MediaPlayer;

public class MyMediaPlayer {
    static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer(){
        if(mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
        }
        return mediaPlayer;
    }

    public static int currentIndex = -1;
}
