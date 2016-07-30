package com.jpappdesigns.musicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.jpappdesigns.musicplayer.fragment.PlayerFragment;
import com.jpappdesigns.musicplayer.model.SongData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by engin on 5/2/2016.
 */
public class SongDetailActivity extends AppCompatActivity {

    private static final String TAG = "SongDetailActivity";
    private ViewPager mViewPager;
    private ArrayList<SongData> mSongData;
    private UUID song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSongData = SongLab.get(this).getSongs();

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(mViewPager);


        for (int i = 0; i < mSongData.size(); i++) {
            if (mSongData.get(i).getId().equals(song)) {
                mViewPager.setCurrentItem(i);
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        song = (UUID) getIntent().getSerializableExtra("SongID");
        for (int i = 0; i < mSongData.size(); i++) {
            adapter.addFrag(PlayerFragment.newInstance(mSongData.get(i).getId()));
        }


        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Toast.makeText(SongDetailActivity.this, "test", Toast.LENGTH_LONG);
            Log.d(TAG, "onPageScrolled: " + "TEST1");
        }

        @Override
        public void onPageSelected(int position) {
            Toast.makeText(SongDetailActivity.this, "test", Toast.LENGTH_LONG);
            mFragmentList.get(position).onDestroy();
            Log.d(TAG, "onPageScrolled: " + "TEST2");
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Toast.makeText(SongDetailActivity.this, "test", Toast.LENGTH_LONG);
        }
    }
}
