package com.example.musicapp.MucsicActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.R;

import java.util.ArrayList;

public class ArtistDetailsAdapter extends RecyclerView.Adapter<ArtistDetailsAdapter.MyHolder> {
    private Context mContext;
    static ArrayList<MusicFiles> artistFiles;
    View view;

    public ArtistDetailsAdapter(Context mContext, ArrayList<MusicFiles> artistFiles) {
        this.mContext = mContext;
        this.artistFiles = artistFiles;
    }

    @NonNull
    @Override
    public ArtistDetailsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.music_iems, parent, false);
        return new ArtistDetailsAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistDetailsAdapter.MyHolder holder, final int position) {
        holder.artist_name.setText(artistFiles.get(position).getTitle());
        byte[] image = getAlbumArt(artistFiles.get(position).getPath());
        if (image != null) {
            Glide.with(mContext).asBitmap()
                    .load(image)
                    .into(holder.artist_image);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.music)
                    .into(holder.artist_image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("sender", "artistDetails");
                intent.putExtra("position", holder.getAdapterPosition());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistFiles.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView artist_image;
        TextView artist_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            artist_image = itemView.findViewById(R.id.music_img);
            artist_name = itemView.findViewById(R.id.music_file_name);
        }
    }

    private byte[] getAlbumArt(String uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
