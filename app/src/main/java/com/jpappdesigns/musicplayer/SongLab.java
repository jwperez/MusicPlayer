package com.jpappdesigns.musicplayer;

import android.content.Context;

import com.jpappdesigns.musicplayer.model.SongData;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by engin on 4/15/2016.
 */
public class SongLab {

    private ArrayList<SongData> mSongs = new ArrayList<SongData>();
    private static SongLab sSongLab;
    private Context mAppContext;

    public SongLab(Context appContext) {
        mAppContext = appContext;

        SongData song1 = new SongData();
        song1.setSongTitle("Basket Case");
        song1.setArtistName("Ritz");
        song1.setAlbumArt(R.drawable.albumimage1);
        song1.setSong(R.raw.basketcase);
        mSongs.add(song1);

        SongData song2 = new SongData();
        song2.setSongTitle("The Theme to Rocky XIII");
        song2.setArtistName("Weird Al");
        song2.setAlbumArt(R.drawable.albumimage2);
        song2.setSong(R.raw.rockythirteen);
        mSongs.add(song2);

        SongData song3 = new SongData();
        song3.setSongTitle("25 to Life");
        song3.setArtistName("Eminem");
        song3.setAlbumArt(R.drawable.albumimage3);
        song3.setSong(R.raw.eminem);
        mSongs.add(song3);

        SongData song4 = new SongData();
        song4.setSongTitle("Citizen Soldier");
        song4.setArtistName("3 Doors Down");
        song4.setAlbumArt(R.drawable.citizen_soldier);
        song4.setSong(R.raw.citizen_soldier);
        mSongs.add(song4);

        SongData song5 = new SongData();
        song5.setSongTitle("Fever for the Flava");
        song5.setArtistName("Hor Action Cop");
        song5.setAlbumArt(R.drawable.fever_for_the_flava);
        song5.setSong(R.raw.fever_for_the_flava);
        mSongs.add(song5);

        SongData song6 = new SongData();
        song6.setSongTitle("The Lazy Song");
        song6.setArtistName("Bruno Mars");
        song6.setAlbumArt(R.drawable.the_lazy_song);
        song6.setSong(R.raw.the_lazy_song);
        mSongs.add(song6);

        SongData song7 = new SongData();
        song7.setSongTitle("Viva La Vida");
        song7.setArtistName("Coldplay");
        song7.setAlbumArt(R.drawable.vivalavidamrniceguy);
        song7.setSong(R.raw.viva_la_vida);
        mSongs.add(song7);

        SongData song8 = new SongData();
        song8.setSongTitle("When I'm Gone");
        song8.setArtistName("3 Doors Down");
        song8.setAlbumArt(R.drawable.when_im_gone);
        song8.setSong(R.raw.when_im_gone);
        mSongs.add(song8);
    }

    public static SongLab get(Context c) {
        if (sSongLab == null) {
            sSongLab = new SongLab(c.getApplicationContext());
        }

        return sSongLab;
    }

    public ArrayList<SongData> getSongs() {
        return mSongs;
    }

    public SongData getSong(UUID id) {
        for (SongData s : mSongs) {
            if (s.getId().equals(id))
                return s;
        }
        return null;
    }
}
