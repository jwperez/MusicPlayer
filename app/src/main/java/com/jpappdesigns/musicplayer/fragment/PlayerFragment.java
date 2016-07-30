package com.jpappdesigns.musicplayer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jpappdesigns.musicplayer.R;
import com.jpappdesigns.musicplayer.SongLab;
import com.jpappdesigns.musicplayer.model.AudioPlayer;
import com.jpappdesigns.musicplayer.model.SongData;

import java.util.UUID;

/**
 * Created by engin on 4/15/2016.
 */
public class PlayerFragment extends Fragment {

    private static final String TAG = "PlayerFragment";
    public static final String EXTRA_SONG_ID = "com.jpappdesigns.musicplayer.song_id";

    private SongData mSongs;
    private ImageView mAlbumArt;
    private ImageButton mPlayButton;
    private ImageButton mPauseButton;
    private TextView mStopButton;
    private TextView mArtistName;
    private TextView mSongTitle;
    private TextView mTotalTime;
    private TextView mElapsedTime;
    private UUID mSongId;
    private AudioPlayer mPlayer = new AudioPlayer();
    private double timeElapsed = 0, finalTime = 0;
    private int forwardTime = 2500, backwardTime = 2500;
    private Handler durationHandler = new Handler();
    private SeekBar seekbar;
    private String finalStringTime;


    public static PlayerFragment newInstance(UUID songId) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SONG_ID, songId);
        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int min, sec;

        mSongId = (UUID) getArguments().getSerializable(EXTRA_SONG_ID);

        mSongs = SongLab.get(getActivity()).getSong(mSongId);
        //Log.d("Test", "" + mSongId);

        finalTime = mPlayer.getDuration(getActivity(), mSongs.getSong());
        Log.d(TAG, "onClick: " + finalTime);

        min = (int) (finalTime / 1000 / 60);
        sec = (int) (finalTime % 60);

        StringBuilder buf = new StringBuilder();
        buf.append(min);
        buf.append(":");
        buf.append(sec);

        finalStringTime = buf.toString();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player, container, false);

        mTotalTime = (TextView) view.findViewById(R.id.tvTotalTime);

        mAlbumArt = (ImageView) view.findViewById(R.id.ivAlbumArt);
        mAlbumArt.setImageResource(mSongs.getAlbumArt());

        mArtistName = (TextView) view.findViewById(R.id.tvArtisitName);
        mArtistName.setText(mSongs.getArtistName());

        mSongTitle = (TextView) view.findViewById(R.id.tvSongTitle);
        mSongTitle.setText(mSongs.getSongTitle());

        mPlayButton = (ImageButton) view.findViewById(R.id.ibPlay);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPlayer.play(getActivity(), mSongs.getSong());
                timeElapsed = mPlayer.getCurrentPosition();
                //seekbar.setProgress(0);
                seekbar.setProgress((int) timeElapsed);
                seekbar.setMax((int) finalTime);
                //durationHandler.postDelayed(updateSeekBarTime, 1000);

                fireOffDuration();


            }
        });

        mPauseButton = (ImageButton) view.findViewById(R.id.ibFastForward);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.pause();
            }
        });

        mElapsedTime = (TextView) view.findViewById(R.id.tvElapsedTime);
        Log.d(TAG, "onCreateView: " + timeElapsed);

        seekbar = (SeekBar) view.findViewById(R.id.seekBar);
        //seekbar.setMax((int) finalTime);
        //seekbar.setProgress(mPlayer.getCurrentPosition());

        //seekbar.setClickable(true);



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int seeked_progess;

            @Override
            public void onProgressChanged(final SeekBar seekBar, int progress, boolean fromUser) {
                /*seeked_progess = progress;
                seeked_progess = seeked_progess * 1000;

                if (fromUser) {

                    Runnable mRunnable = new Runnable() {
                        @Override
                        public void run() {
                            int min, sec;
                            if (mPlayer != null) {
                                int mCurrentPosition = seekBar.getProgress();
                                min = mCurrentPosition / 60;
                                sec = mCurrentPosition % 60;
                                Log.e("Music Player Activity", "Minutes : "+min +" Seconds : " + sec);

                            }
                            durationHandler.postDelayed(this, 1000);
                        }
                    };
                    mRunnable.run();
                }*/

                if (fromUser) {
                    mPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mTotalTime.setText("" + finalStringTime);

        return view;
    }

    private void fireOffDuration() {
        Log.d(TAG, "fireOffDuration: ");
        durationHandler.removeCallbacks(updateSeekBarTime);
        durationHandler.postDelayed(updateSeekBarTime, 100);
    }

    //handler to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            Log.d(TAG, "run: ");
            if (mPlayer.isPlaying()) {
                int mediaPos_new = mPlayer.getCurrentPosition();
                int mediaMax_new = mPlayer.getDuration();

                seekbar.setMax(mediaMax_new);
                seekbar.setProgress(mediaPos_new);

                durationHandler.postDelayed(this, 100); // Looping the thread after 0.1
                // seconds
                seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    int seeked_progess;

                    @Override
                    public void onProgressChanged(final SeekBar seekBar, int progress, boolean fromUser) {
                /*seeked_progess = progress;
                seeked_progess = seeked_progess * 1000;

                if (fromUser) {

                    Runnable mRunnable = new Runnable() {
                        @Override
                        public void run() {
                            int min, sec;
                            if (mPlayer != null) {
                                int mCurrentPosition = seekBar.getProgress();
                                min = mCurrentPosition / 60;
                                sec = mCurrentPosition % 60;
                                Log.e("Music Player Activity", "Minutes : "+min +" Seconds : " + sec);

                            }
                            durationHandler.postDelayed(this, 1000);
                        }
                    };
                    mRunnable.run();
                }*/

                        if (fromUser) {
                            mPlayer.seekTo(progress);
                            seekBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        }
            /*//get current position
            timeElapsed = mPlayer.getCurrentPosition();
            //set seekbar progress
            seekbar.setProgress((int) timeElapsed);
            //set time remaining
            //double timeRemaining = finalTime - timeElapsed;
            //duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

            //repeat yourself that again in 100 miliseconds
            durationHandler.postDelayed(this, 100);*/
    };
    //get current position
           /*timeElapsed = mPlayer.getCurrentPosition();
            //set seekbar progress
            seekbar.setProgress((int) timeElapsed);
            //set time remaining
            //double timeRemaining = finalTime - timeElapsed;
            //duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

            //repeat yourself that again in 100 miliseconds
            durationHandler.postDelayed(this, 100);
    };*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPlayer.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPlayer.pause();
    }



    @Override
    public void onStop() {
        super.onStop();
        mPlayer.stop();
    }
}
