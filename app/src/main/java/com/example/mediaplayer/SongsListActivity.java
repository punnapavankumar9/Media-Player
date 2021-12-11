package com.example.mediaplayer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongsListActivity extends AppCompatActivity {
    ArrayList<String > songs = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_files_list_activity);

        RecyclerView recyclerViewSongsList = findViewById(R.id.folder_files_list_recycler);
        SongListAdapterRec songListAdapterRec = new SongListAdapterRec(this);


        songs.add("jhsd aasjdabsd ajsdbajs dahsdbas d");
        songs.add("jhsd absd ajsdb dsfsdf hsdbas d");
        songs.add("jhsd aasjda dbajs dsfmns dfsd d");
        songs.add("jhsd aa   sjda  bsd ajsdba d");
        songs.add("jhsd aasjdabsd a  jsd  ba d");
        songListAdapterRec.setSongs(songs);
        recyclerViewSongsList.setAdapter(songListAdapterRec);
        recyclerViewSongsList.setLayoutManager(new LinearLayoutManager(this));



    }
}
