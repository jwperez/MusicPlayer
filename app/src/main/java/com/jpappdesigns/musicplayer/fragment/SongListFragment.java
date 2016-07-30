package com.jpappdesigns.musicplayer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpappdesigns.musicplayer.R;
import com.jpappdesigns.musicplayer.RecyclerTouchListener;
import com.jpappdesigns.musicplayer.SongLab;
import com.jpappdesigns.musicplayer.adapter.SongListAdapter;
import com.jpappdesigns.musicplayer.model.SongData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by engin on 4/29/2016.
 */
public class SongListFragment extends Fragment {

    private static final String TAG = "SongListFragment";
    private RecyclerView mRecyclerView;
    private Callbacks mCallbacks;
    private ArrayList<SongData> songData;

    public static Fragment newInstance() {

        SongListFragment fragment = new SongListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SongListAdapter adapter = new SongListAdapter(getActivity(), displaySong());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                UUID song = songData.get(position).getId();
                mCallbacks.onSongSeleceted(song);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private List<SongData> displaySong() {

        List<SongData> songDatas = new ArrayList<>();
        songData = SongLab.get(getActivity()).getSongs();
        //Log.d(TAG, "displaySong: " + songData);
        for (int i = 0; i < songData.size(); i++) {
            songDatas.add(songData.get(i));
        }
        Log.d(TAG, "songData size: " + songDatas.size());
        return songDatas;
    }

    public interface Callbacks {

        void onSongSeleceted(UUID songId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
