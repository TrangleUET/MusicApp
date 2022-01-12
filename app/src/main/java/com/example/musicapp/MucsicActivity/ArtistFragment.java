package com.example.musicapp.MucsicActivity;

import static com.example.musicapp.MucsicActivity.HomeActivity.albums;
import static com.example.musicapp.MucsicActivity.HomeActivity.artists;
import static com.example.musicapp.MucsicActivity.HomeActivity.musicFiles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ArtistFragment extends Fragment {
    RecyclerView recyclerView;
    ArtistAdapter artistAdapter;
    public ArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        if(!(artists.size() < 1)) {
            artistAdapter = new ArtistAdapter(getContext(), artists);
            recyclerView.setAdapter(artistAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        }
        return view;
    }
}