package com.saarang.android.eventsaarang;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Sathwik on 06-08-2016.
 */
public class Event {

    //private variables
    int eventId, catId; //Event ID and Category ID

    String title,
            subtitle, //Category
            description, //Long description
            venue;

    Calendar startTime;
    double duration; //Duration of the event

    List<String> prizeMoney;
    List<Contact> contacts;
    Boolean isStarred;
    int imageId;


    //Empty Constructor
    public Event() {
    }

    //Constructor
    public Event(int eventId,
                 int catId,
                 String title,
                 String subtitle,
                 String description,
                 String venue,
                 Calendar startTime,
                 double duration,
                 List<String> prizeMoney,
                 List<Contact> contacts,
                 int imageId
    ) {
        this.eventId = eventId;
        this.catId = catId;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.venue = venue;
        this.startTime = startTime;
        this.duration = duration;
        this.prizeMoney = prizeMoney;
        this.isStarred = false; //Not starred initially
        this.contacts = contacts;
        this.imageId = imageId;
    }


    //Getters

    public int getEventId() {
        return eventId;
    }

    public int getCatId() {
        return catId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public double getDuration() {
        return duration;
    }

    public List<String> getPrizeMoney() {
        return prizeMoney;
    }

    public Boolean getStarred() {
        return isStarred;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public int getImageId() {
        return imageId;
    }

    //Setter
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setPrizeMoney(List<String> prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public void setStarred(Boolean starred) {
        isStarred = starred;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
