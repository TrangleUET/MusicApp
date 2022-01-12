package com.example.musicapp.MucsicActivity;

import static com.example.musicapp.MucsicActivity.HomeActivity.musicFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.musicapp.R;

import java.util.ArrayList;

public class ArtistDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView artistPhoto;
    String artistName;
    ArrayList<MusicFiles> artistSongs = new ArrayList<>();
    ArtistDetailsAdapter artistDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_details);
        recyclerView = findViewById(R.id.recyclerView);
        artistPhoto = findViewById(R.id.artistPhoto);
        artistName = getIntent().getStringExtra("artistName");
        int j = 0;
        for (int i = 0; i < musicFiles.size(); i++) {
            if (artistName.equals(musicFiles.get(i).getArtist())) {
                artistSongs.add(j, musicFiles.get(i));
                j++;
            }
        }
        byte[] image = getAlbumArt(artistSongs.get(0).getPath());
        if (image != null) {
            Glide.with(this)
                    .load(image)
                    .into(artistPhoto);
        }
        else {
            Glide.with(this)
                    .load(R.drawable.music)
                    .into(artistPhoto);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!(artistSongs.size() <1 )) {
            artistDetailsAdapter = new ArtistDetailsAdapter(this, artistSongs);
            recyclerView.setAdapter(artistDetailsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
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