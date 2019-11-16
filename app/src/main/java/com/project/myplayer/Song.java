package com.project.myplayer;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

public class Song implements Serializable {
    private String name;
    private String artist;
//    private int pictureId;
//    private Uri items;

    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
