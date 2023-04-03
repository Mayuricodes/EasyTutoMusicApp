package com.example.mayuri.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mayuri.Model.AudioModel;
import com.example.mayuri.Adapter.MusicListAdapter;
import com.example.mayuri.R;
import com.example.mayuri.GetSongList.SongsManger;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView noMusicTextView;

    ArrayList<AudioModel> songsList = new ArrayList<>();

    MusicListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        noMusicTextView = findViewById(R.id.no_songs_text);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        SongsManger manger = new SongsManger();
        songsList = manger.getPlayList();
        adapter = new MusicListAdapter(songsList, MainActivity.this);
        recyclerView.setAdapter(adapter);

        //permission false hongi to ye sabse pehle request krega permission ke liye or baad me permission aane ke baad aage ke songslist show krega
        if(checkPermission() == false){
            requestPermission();
            return;
        }


    }

    boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this,"READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTINGS",Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }
}