package com.example.mayuri.GetSongList;

import android.os.Environment;

import com.example.mayuri.Model.AudioModel;

import java.io.File;
import java.util.ArrayList;

public class SongsManger {

    final String MEDIA_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    private ArrayList<AudioModel> songsList = new ArrayList<>();


    public ArrayList<AudioModel> getPlayList() {
        System.out.println(MEDIA_PATH);
        if (MEDIA_PATH != null) {
            File home = new File(MEDIA_PATH);
            File[] listFiles = home.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    System.out.println(file.getAbsolutePath());
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }
                }
            }
        }
        // return songs list array
        return songsList;
    }

    private void scanDirectory(File directory) {
        if (directory != null) {
            File[] listFiles = directory.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }

                }
            }
        }
    }

    private void addSongToList(File song) {
        if (song.getName().endsWith(".mp3")) {
            AudioModel model = new AudioModel(song.getPath(),song.getName().substring(0, (song.getName().length() - 4))+".mp3");
            songsList.add(model);
        }
    }
}
