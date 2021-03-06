package com.example.mediaplayer.Activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.Helpers.MyMediaPlayer;
import com.example.mediaplayer.Helpers.Song;
import com.example.mediaplayer.R;
import com.example.mediaplayer.SongListAdapterRec;

import java.util.ArrayList;

public class SongsListActivity extends AppCompatActivity {
    ArrayList<Song> songs = new ArrayList<>();
    ArrayList<Uri> songUris = new ArrayList<>();
    MyMediaPlayer mp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_files_list_activity);


        songUris = this.getIntent().getExtras().getParcelableArrayList("SONG_LIST_URIS");
        mp = (MyMediaPlayer) getIntent().getExtras().getSerializable("MEDIA_PLAYER_OBJECT");

        RecyclerView recyclerViewSongsList = findViewById(R.id.folder_files_list_recycler);
        SongListAdapterRec songListAdapterRec = new SongListAdapterRec(this, mp);


        String lastPath = "";
        for(int i = 0 ; i < songUris.size(); i++){
            lastPath = songUris.get(i).getLastPathSegment();
            songs.add(new Song(lastPath.substring(lastPath.lastIndexOf('/') + 1), songUris.get(i), 912839));
        }


        songListAdapterRec.setSongs(songs);
        recyclerViewSongsList.setAdapter(songListAdapterRec);
        recyclerViewSongsList.setLayoutManager(new LinearLayoutManager(this));
    }
}
