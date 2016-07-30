package com.jpappdesigns.musicplayer.model;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by engin on 4/22/2016.
 */
public class AudioPlayer extends MediaPlayer {

    private MediaPlayer mPlayer;

    public void stop(){
        if (mPlayer != null){
            mPlayer.release();
            mPlayer= null;
        }
    }

    public void play(Context c, int song){
        stop();

        mPlayer = MediaPlayer.create(c, song);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                stop();
            }
        });

        mPlayer.start();
    }

    public void pause() {

        if (mPlayer == null) {
            return;
        }

        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();

        }
    }

    public int getDuration(Context c, int song) {
        mPlayer = MediaPlayer.create(c, song);

        return mPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return super.getCurrentPosition();
        //return mPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int msec) throws IllegalStateException {
        super.seekTo(msec);
        mPlayer.seekTo(msec);
    }

}
