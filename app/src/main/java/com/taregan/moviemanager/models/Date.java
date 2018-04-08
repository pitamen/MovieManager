package com.taregan.moviemanager.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pitambar on 4/7/18.
 */

public class Date {
    @SerializedName("maximum")
    public String dateMaximum;

    @SerializedName("minimum")
    public String dateMinimum;

    public String getDateMaximum() {
        return dateMaximum;
    }

    public void setDateMaximum(String dateMaximum) {
        this.dateMaximum = dateMaximum;
    }

    public String getDateMinimum() {
        return dateMinimum;
    }

    public void setDateMinimum(String dateMinimum) {
        this.dateMinimum = dateMinimum;
    }
}
