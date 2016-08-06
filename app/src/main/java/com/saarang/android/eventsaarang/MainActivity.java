package com.saarang.android.eventsaarang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> titleList = Arrays.asList("Music Events", "Classical Events", "Word Games");
        RecyclerView RV_category = (RecyclerView) findViewById(R.id.rv_categories);
        RV_category.setLayoutManager(new LinearLayoutManager(this));
        RV_category.setAdapter(new CategoryAdapter(titleList));

//        RecyclerView RV_eventHorizontal = (RecyclerView) findViewById(R.id.rv_eventHorizontal);
//        if (RV_eventHorizontal != null) {
//            RV_eventHorizontal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//        }
//        if (RV_eventHorizontal != null) {
//            RV_eventHorizontal.setAdapter( new EventAdapter(titleList) );
//        }


        boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstRun", true);
        //Add events to the database only once for the first time
        //In the actual app database should already be ready to the scheduler
        if (firstRun)
            firstRunOperations();
    }


    private void firstRunOperations() {
        DataBaseHandler db = new DataBaseHandler(this);

        //        Event(
        //          int EVENT_ID,
        //          int CATEGORY_ID,
        //          String TITLE,
        //          String SUBTITLE,
        //          String DESCRIPTION,
        //          String VENUE,
        //          Calendar START_TIME,
        //          double EVENT_DURATION,
        //          List<String> PRIZE_MONEY,
        //          List<Contact> CONTACTS,
        //          int IMAGE_ID
        //        )

        db.addEvent(new Event(1,
                1,
                "Alankar",
                "Light Music",
                "Introducing our one and only solo singing competition Alankar where you can battle it out with over a hundred vocalists while being judged by the best singing talents out there.",
                "CLT",
                new GregorianCalendar(2017, 0/*Month*/, 2/*dayOfMonth*/, 10/*hourOfDay*/, 30/*minute*/),
                1.5,
                Arrays.asList("\u20B9" + "2000", "₹1000", "500"),
                Arrays.asList(new Contact("Sathwik", "7734015692", "chadagasathwik@gmail.com"),
                        new Contact("Abhishek", "7092176688", "abhishekaroor@gmail.com")),
                R.drawable.saarang_alankar));

        db.addEvent(new Event(2,
                1,
                "Decibels",
                "Western Music",
                "Introducing our one and only solo singing competition Alankar where you can battle it out with over a hundred vocalists while being judged by the best singing talents out there.",
                "CLT",
                new GregorianCalendar(2017, 0/*Month*/, 3/*dayOfMonth*/, 9/*hourOfDay*/, 15/*minute*/),
                1,
                Arrays.asList("5000", "3000", "1000"),
                Arrays.asList(new Contact("Sathwik", "7734015692", "chadagasathwik@gmail.com"),
                        new Contact("Abhishek", "7092176688", "abhishekaroor@gmail.com")),
                R.drawable.saarang_decibels));

        //Messages
        Toast toast = Toast.makeText(getApplicationContext(), "Database Created!", Toast.LENGTH_LONG);
        toast.show();

        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("firstRun", false)
                .commit();
    }
}
