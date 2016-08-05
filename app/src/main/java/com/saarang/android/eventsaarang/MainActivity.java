package com.saarang.android.eventsaarang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstRun", true);
        //Add events to the database only once for the first time
        //In the actual app database should already be ready to the scheduler
        if (firstRun)
            firstRunOperations();
    }


    private void firstRunOperations(){
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
        //Success Messages
        Log.d("DATABASE: ", "Created!");
        Toast toast = Toast.makeText(getApplicationContext(), "Database Created!", Toast.LENGTH_LONG);
        toast.show();

        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("firstRun", false)
                .commit();
    }
}
