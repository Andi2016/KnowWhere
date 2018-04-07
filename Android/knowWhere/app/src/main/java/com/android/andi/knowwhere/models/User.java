package com.android.andi.knowwhere.models;

/**
 * Created by Andi Xu on 4/4/18.
 */

public class User {
    private String username;
    private String whatsup;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String photoUrl;
    private String status;
    private String[] friends;
    private String[] groups;
    private Coordinate coordinate;

    public User(){

    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String whatsup, String firstname, String lastname, String photoUrl, String status, String[] friends, String[] groups, Coordinate coordinate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.whatsup = whatsup;
        this.firstname = firstname;
        this.lastname = lastname;
        this.photoUrl = photoUrl;
        this.status = status;
        this.friends = friends;
        this.groups = groups;
        this.coordinate = coordinate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWhatsup() {
        return whatsup;
    }

    public void setWhatsup(String whatsup) {
        this.whatsup = whatsup;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
