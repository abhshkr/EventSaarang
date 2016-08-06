package com.saarang.android.eventsaarang;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class EventsInCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_in_category);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);      //enables back button
            actionBar.setTitle("Category Name");        //sets Title text
        }


        List<String> titleList  = Arrays.asList("Music Events", "Classical Events", "Word Games");
        RecyclerView RV_category= (RecyclerView) findViewById(R.id.rv_eventsGrid);
        if (RV_category != null) {
            RV_category.setLayoutManager(new LinearLayoutManager(this));
        }
        if (RV_category != null) {
            RV_category.setAdapter( new EventAdapter(titleList, R.layout.eventlist_item) );
        }
    }
}
