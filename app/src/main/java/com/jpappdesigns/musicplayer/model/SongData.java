package com.jpappdesigns.musicplayer.model;

import java.util.UUID;

/**
 * Created by engin on 4/15/2016.
 */
public class SongData {

    private UUID mId;
    private String mSongTitle;
    private String mArtistName;
    private int mAlbumArt;
    private int mSong;

    public SongData() {
        mId = UUID.randomUUID();

    }

    public SongData(int albumArt, String artistName, String songTitle, int song) {
        setId();
        mAlbumArt = albumArt;
        mArtistName = artistName;
        mSongTitle = songTitle;
        mSong = song;
    }

    public int getAlbumArt() {
        return mAlbumArt;
    }

    public void setAlbumArt(int albumArt) {
        mAlbumArt = albumArt;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public UUID getId() {
        return mId;
    }

    public void setId() {
        this.mId = UUID.randomUUID();
    }

    public int getSong() {
        return mSong;
    }

    public void setSong(int song) {
        mSong = song;
    }

    public String getSongTitle() {
        return mSongTitle;
    }

    public void setSongTitle(String songTitle) {
        mSongTitle = songTitle;
    }


}
