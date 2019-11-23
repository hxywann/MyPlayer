package com.project.myplayer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SongAdapter extends BaseAdapter {

    Activity mActivity;
    MySongs mySongs;

    public SongAdapter(Activity mActivity, MySongs mySongs){
        this.mActivity = mActivity;
        this.mySongs = mySongs;
    }

    @Override
    public int getCount() {
        return mySongs.getMySongList().size();
    }

    @Override
    public Song getItem(int position) {
        return mySongs.getMySongList().get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View oneSongLine;
        LayoutInflater inflater =(LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        oneSongLine = inflater.inflate(R.layout.song_oneline, viewGroup, false);
        TextView tv_name = oneSongLine.findViewById(R.id.song_name);
        TextView tv_artist = oneSongLine.findViewById(R.id.song_artist);
        ImageView iv_picture = oneSongLine.findViewById(R.id.imageView);

        Song s = this.getItem(position);
        tv_name.setText(s.getName());
        tv_artist.setText(s.getArtist());
        iv_picture.setImageResource(R.drawable.ic_music_note);


        return oneSongLine;
    }
}
