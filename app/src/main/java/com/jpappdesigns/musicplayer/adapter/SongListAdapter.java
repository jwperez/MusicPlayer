package com.jpappdesigns.musicplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpappdesigns.musicplayer.R;
import com.jpappdesigns.musicplayer.model.SongData;

import java.util.Collections;
import java.util.List;

/**
 * Created by engin on 4/29/2016.
 */
public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private static final String TAG = "SongListAdapter";
    private LayoutInflater mLayoutInflater;
    private List<SongData> data = Collections.emptyList();
    private Context mContext;

    public SongListAdapter(Context context, List<SongData> data) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.song_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mAlbumArt.setImageResource(data.get(position).getAlbumArt());
        holder.mSongTitle.setText(data.get(position).getSongTitle());
        holder.mArtistName.setText(data.get(position).getArtistName());
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: " + data.size());
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mAlbumArt;
        TextView mSongTitle;
        TextView mArtistName;

        public ViewHolder(View itemView) {
            super(itemView);

            mAlbumArt = (ImageView) itemView.findViewById(R.id.ivAlbumArt);
            mSongTitle = (TextView) itemView.findViewById(R.id.tvSongTitle);
            mArtistName = (TextView) itemView.findViewById(R.id.tvArtisitName);
        }
    }
}
