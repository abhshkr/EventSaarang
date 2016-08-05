package com.saarang.android.eventsaarang;

/**
 * Created by Sathwik on 05-08-2016.
 */
public class Event {
    //private variables
    int id, img_id;
    String time;    //TODO - better to make this a Calendar
    String day;     //TODO - better to make this a Calendar
    String title;
    String subtitle;
    //List PrizeMoney; //TODO - add extra fields
    //String venue;
    //double coordinates[2];
    //ContactInfo contacts;

    public Event(){}

    //Constructor
    public Event(String title, String subtitle, String day, String time, int img_id) {
        this.title = title;
        this.subtitle = subtitle;
        this.day = day;
        this.time = time;
        this.img_id = img_id;
    }

    //Get functions
    public int getID() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public String getDay() {
        return day;
    }
    public int get_image_id() {
        return img_id;
    }
    public String getTime() {
        return time;
    }

    //Set functions
    public void setID(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void set_image_id(int img_id) {
        this.img_id = img_id;
    }

}
