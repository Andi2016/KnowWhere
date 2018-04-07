package com.android.andi.knowwhere.models;

/**
 * Created by Andi Xu on 4/5/18.
 */

public class Coordinate {
    private double latitiude;
    private double longitude;

    public Coordinate(double latitiude, double longitude) {
        this.latitiude = latitiude;
        this.longitude = longitude;
    }

    public double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(double latitiude) {
        this.latitiude = latitiude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
