package com.android.andi.knowwhere.models;

/**
 * Created by Andi Xu on 4/7/18.
 */

public class Contact {
    private String photoUrl;
    private String username;

    public Contact(String photoUrl, String username) {
        this.photoUrl = photoUrl;
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
