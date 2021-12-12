package com.example.mediaplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.Helpers.Song;

import java.util.ArrayList;

public class SongListAdapterRec extends RecyclerView.Adapter<SongListAdapterRec.ViewHolder> {

    Context context;
    ArrayList<Song> songs = new ArrayList<>();

    public SongListAdapterRec(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtSongTitleInList.setText(songs.get(position).getShortName());
        holder.btnSongInfo.setOnClickListener(v -> {
            Toast.makeText(context, "btn song info has been clicked", Toast.LENGTH_SHORT).show();
        });
        holder.btnPlayInList.setOnClickListener(v -> {
            Toast.makeText(context, "btn play song has been clicked", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtSongTitleInList;
        Button btnSongInfo, btnPlayInList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtSongTitleInList = itemView.findViewById(R.id.txtSongTitleInList);
            this.btnPlayInList = itemView.findViewById(R.id.btnPlayInList);
            this.btnSongInfo = itemView.findViewById(R.id.btnSongInfo);
        }
    }

}
