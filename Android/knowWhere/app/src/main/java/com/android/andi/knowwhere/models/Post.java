package com.android.andi.knowwhere.models;

/**
 * Created by Andi Xu on 4/5/18.
 */

public class Post {
    private double distance;
    private String username;
    private String whatsup;
    private String photoUrl;

    public Post(double distance, String username, String whatsup, String photoUrl) {
        this.distance = distance;
        this.username = username;
        this.whatsup = whatsup;
        this.photoUrl = photoUrl;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getUsername() {
        return username;
    }

    public void setSender(String username) {
        this.username = username;
    }

    public String getWhatsup() {
        return whatsup;
    }

    public void setWhatsup(String whatsup) {
        this.whatsup = whatsup;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
