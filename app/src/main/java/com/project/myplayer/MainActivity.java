package com.project.myplayer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Magnifier;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addFab;
    ListView mListView;
    SongAdapter mAdapter;
    MySongs mySongs;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = findViewById(R.id.list_view);

        runtimePermission();

        mAdapter = new SongAdapter(MainActivity.this, mySongs);
        mListView.setAdapter(mAdapter);

        //listen for incoming data
        Bundle incomingData = getIntent().getExtras();
        if(incomingData !=null){
            song = (Song) getIntent().getSerializableExtra("newObject");
            mySongs.getMySongList().add(song);
            mAdapter.notifyDataSetChanged();
        }

        addFab = findViewById(R.id.addfab);

        //after select the favorite songs from the initial song list
        //then click the "Add" fab to add the selected songs into the favorite list
        //open favorite list activity
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(view.getContext(), FavoriteActivity.class);
                startActivity(i);
            }
        });

        //after click the song from the list will route to the playing Activity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                Song s = mySongs.getMySongList().get(position);
                intent.putExtra("selectedObject", s).putExtra("pos",position);
                startActivity(intent);
            }
        });
    }

//    Activity act = (Activity)(holder.call.get)
    public void runtimePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener(){
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                mySongs.getMySongList();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.manu_add_item) {
            Intent i = new Intent(this,FavoriteActivity.class);
            this.startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
