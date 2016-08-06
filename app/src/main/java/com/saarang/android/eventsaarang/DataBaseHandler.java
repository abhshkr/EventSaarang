package com.saarang.android.eventsaarang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sathwik on 05-08-2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {// All Static variables

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "eventsManager";
    // events table name
    private static final String TABLE_EVENTS = "events";
    // events Table Columns names

    private static final String KEY_EVENT_ID = "eventId";
    private static final String KEY_CATEGORY_ID = "categoryId";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SUBTITLE = "subtitle";
    private static final String KEY_DESRIPTION = "description";
    private static final String KEY_VENUE = "venue";
    private static final String KEY_START_TIME = "time";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_PRIZE = "prize";
    private static final String KEY_CONTACTS = "contacts1";
    private static final String KEY_IMAGE = "image";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_EVENT_ID + " INTEGER PRIMARY KEY,"
                + KEY_CATEGORY_ID + " INTEGER,"
                + KEY_TITLE + " TEXT,"
                + KEY_SUBTITLE + " TEXT,"
                + KEY_DESRIPTION + " TEXT,"
                + KEY_VENUE + " TEXT,"
                + KEY_START_TIME + " TEXT,"
                + KEY_DURATION + " REAL,"
                + KEY_PRIZE + " TEXT,"
                + KEY_CONTACTS + " TEXT,"
                + KEY_IMAGE + " INTEGER" + ")";

        db.execSQL(CREATE_EVENTS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    Gson gsonTime = new Gson(),
            gsonPrize = new Gson(),
            gsonContacts = new Gson();

    Type typeTime = new TypeToken<Calendar>() {
    }.getType(),
            typePrize = new TypeToken<List<String>>() {
            }.getType(),
            typeContacts = new TypeToken<List<Contact>>() {
            }.getType();

    // Adding new event
    void addEvent(Event event) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

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
        values.put(KEY_EVENT_ID, event.getEventId());
        values.put(KEY_CATEGORY_ID, event.getCatId());
        values.put(KEY_TITLE, event.getTitle());
        values.put(KEY_SUBTITLE, event.getSubtitle());
        values.put(KEY_DESRIPTION, event.getDescription());
        values.put(KEY_VENUE, event.getVenue());


        String jsonTime = gsonTime.toJson(event.getStartTime(), typeTime);
        values.put(KEY_START_TIME, jsonTime);

        values.put(KEY_DURATION, event.getDuration());

        String jsonPrize = gsonPrize.toJson(event.getPrizeMoney(), typePrize);
        values.put(KEY_PRIZE, jsonPrize);

        String jsonContacts = gsonContacts.toJson(event.getContacts(), typeContacts);
        values.put(KEY_CONTACTS, jsonContacts);

        values.put(KEY_IMAGE, event.getImageId());

        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Events on a specific day
    public List<Event> getEvents(int categoryID) {

        List<Event> eventList = new ArrayList<Event>();

        // Select required events from Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS + " WHERE " + KEY_CATEGORY_ID + " == " + String.valueOf(categoryID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setEventId(cursor.getInt(0));
                event.setCatId(cursor.getInt(1));
                event.setTitle(cursor.getString(2));
                event.setSubtitle(cursor.getString(3));
                event.setDescription(cursor.getString(4));
                event.setVenue(cursor.getString(5));

                Calendar time = gsonTime.fromJson(cursor.getString(6), typeTime);
                event.setStartTime(time);

                event.setDuration(cursor.getDouble(7));

                List<String> prize = gsonPrize.fromJson(cursor.getString(8), typePrize);
                event.setPrizeMoney(prize);

                List<Contact> contacts = gsonContacts.fromJson(cursor.getString(9), typeContacts);
                event.setContacts(contacts);

                event.setImageId(cursor.getInt(10));

                // Adding event to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        // return event list
        return eventList;
    }

    public int getEventsCount(int cat) {
        String countQuery = "SELECT  * FROM " + TABLE_EVENTS + " WHERE " + KEY_CATEGORY_ID + " == " + String.valueOf(cat);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;

    }

    // Getting single event
    Event getEvent(int eventId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS,
                new String[]{KEY_EVENT_ID,
                        KEY_CATEGORY_ID,
                        KEY_TITLE,
                        KEY_SUBTITLE,
                        KEY_DESRIPTION,
                        KEY_VENUE,
                        KEY_START_TIME,
                        KEY_DURATION,
                        KEY_PRIZE,
                        KEY_CONTACTS,
                        KEY_IMAGE},
                KEY_EVENT_ID + "=?",
                new String[]{String.valueOf(eventId)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event();
        if (cursor != null) {
            event.setEventId(cursor.getInt(0));
            event.setCatId(cursor.getInt(1));
            event.setTitle(cursor.getString(2));
            event.setSubtitle(cursor.getString(3));
            event.setDescription(cursor.getString(4));
            event.setVenue(cursor.getString(5));

            Calendar time = gsonTime.fromJson(cursor.getString(6), typeTime);
            event.setStartTime(time);

            event.setDuration(cursor.getDouble(7));

            List<String> prize = gsonPrize.fromJson(cursor.getString(8), typePrize);
            event.setPrizeMoney(prize);

            List<Contact> contacts = gsonContacts.fromJson(cursor.getString(9), typeContacts);
            event.setContacts(contacts);

            event.setImageId(cursor.getInt(10));
        }
        cursor.close();
        // return event
        return event;
    }
}
