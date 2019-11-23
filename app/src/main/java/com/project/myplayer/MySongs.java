package com.project.myplayer;

import android.os.Environment;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MySongs {
    List<Song> mySongList;
    ArrayList<File> arrayList;
    String[]items;

    public MySongs(List<Song> mySongList){this.mySongList = mySongList;}


    public ArrayList<File> findSong(File file) {

        //fetch all the songs from the phone storage and insert into the list

         arrayList= new ArrayList<>();
        File[] files = file.listFiles();
        for (File sFile : files) {
            if (sFile.isDirectory() && !sFile.isHidden()) {

                arrayList.addAll(findSong(sFile));
            } else {
                if (sFile.getName().endsWith(".mp3") ||
                        sFile.getName().endsWith(".wav")) {
                    arrayList.add(sFile);
                }
            }
        }
        return arrayList;
    }

//    public void display(){
//        final ArrayList<File> songList = findSong(Environment.getExternalStorageDirectory());
//        items = new String[songList.size()];
//        for(int i=0; i<songList.size(); i++){
//            items[i]=songList.get(i).getName().toString().replace(".mp3","").replace(".wav","");
//        }
//        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
//    }


        public MySongs(){

        //implement the files into Song class


//            String[] initialSongNames;
//            String[] initalArtists;
            final ArrayList<File> songList = findSong(Environment.getExternalStorageDirectory());
            this.mySongList = new ArrayList<>();
            for(int i=0; i<songList.size(); i++){
                Song s = new Song(songList.get(i).getName().toString().replace(".mp3","").replace(".wav",""),
                        songList.get(i).getPath().toString());
                mySongList.add(s);
            }
        }

    public List<Song> getMySongList(){
        return mySongList;
    }

    public void setMySongList(List<Song> mySongList){
        this.mySongList = mySongList;
    }
}
