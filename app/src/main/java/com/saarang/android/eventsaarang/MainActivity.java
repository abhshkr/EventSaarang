package com.saarang.android.eventsaarang;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstRun", true);
        //Add events to the database only once for the first time
        //In the actual app database should already be ready to the scheduler
        if (firstRun) {
            DataBaseHandler db = new DataBaseHandler(this);

            db.addEvent(new Event("Alankar", "Light Music", "1", "CLT, 9:30 AM", R.drawable.saarang_alankar));
            db.addEvent(new Event("Decibels", "Western Music", "1", "SAC, 10:30 AM",R.drawable.saarang_decibels));
            db.addEvent(new Event("Flash Fiction", "Writing", "1", "CRC 205, 11:05 AM", R.drawable.saarang_street));
            db.addEvent(new Event("Solo Dance", "Classical Arts", "1", "Seminar Hall, 1:30 PM", R.drawable.saarang_vox));
            db.addEvent(new Event("India Quiz", "Quizzing", "1", "OAT, 2:00 PM", R.drawable.saarang_alankar));


            db.addEvent(new Event("Raagapella", "Light Music", "2", "SAC, 10:30 AM",R.drawable.saarang_decibels));
            db.addEvent(new Event("Streets", "Choreo", "2", "CLT, 9:30 AM", R.drawable.saarang_alankar));
            db.addEvent(new Event("TinyTales WS", "Writing", "2", "CRC 205, 11:05 AM",R.drawable.saarang_decibels));
            db.addEvent(new Event("Vocals", "Classical Arts", "2", "Seminar Hall, 1:30 PM", R.drawable.saarang_street));
            db.addEvent(new Event("Lone Wolf", "Quizzing", "2", "OAT, 2:00 PM", R.drawable.saarang_alankar));


            db.addEvent(new Event("Vox", "Western Music", "3", "CRC 205, 11:05 AM", R.drawable.saarang_vox));
            db.addEvent(new Event("Dance WS", "Choreo", "3", "OAT, 2:00 PM", R.drawable.saarang_alankar));
            db.addEvent(new Event("Creative Writing", "Writing", "3", "SAC, 10:30 AM", R.drawable.saarang_street));
            db.addEvent(new Event("Instrumentals ", "Classical Arts", "3", "Seminar Hall, 1:30 PM", R.drawable.saarang_alankar));
            db.addEvent(new Event("Spent Quiz", "Quizzing", "3", "CLT, 9:30 AM",R.drawable.saarang_decibels));


            Log.d("DATABASE: ", "Created!");
            Toast toast = Toast.makeText(getApplicationContext(), "Database Created!", Toast.LENGTH_LONG);
            toast.show();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstRun", false)
                    .commit();
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
