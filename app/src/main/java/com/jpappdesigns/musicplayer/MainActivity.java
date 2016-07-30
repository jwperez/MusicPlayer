package com.jpappdesigns.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.jpappdesigns.musicplayer.fragment.PlayerFragment;
import com.jpappdesigns.musicplayer.fragment.SongListFragment;
import com.jpappdesigns.musicplayer.model.SongData;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements SongListFragment.Callbacks{

    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private ArrayList<SongData> mSongData;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdatail);

        mFrameLayout = (FrameLayout) findViewById(R.id.fragmentList);

        mSongData =SongLab.get(this).getSongs();

        Fragment fragment = new SongListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentList, fragment).commit();

    }

    @Override
    public void onSongSeleceted(UUID songId) {

        if (findViewById(R.id.fragmentDetail) == null) {
            Intent i = new Intent(this, SongDetailActivity.class);
            i.putExtra("SongID", songId);
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.fragmentDetail);
            Fragment newDetail = PlayerFragment.newInstance(songId);

            if (oldDetail != null) {
                ft.remove(oldDetail);
            }

            ft.add(R.id.fragmentDetail, newDetail).commit();
        }

    }



}
