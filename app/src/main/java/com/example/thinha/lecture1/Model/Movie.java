package com.example.thinha.lecture1.Model;

import com.example.thinha.lecture1.Utils.Constant;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ThiNha on 10/13/2016.
 */
public class Movie implements Serializable {

    @SerializedName("title")
    private String tiltle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("vote_average")
    private String vote_average;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("id")
    private String id;

    public String getTiltle() {
        return tiltle;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date()
    {
        return release_date;
    }

    public String getId()
    {
        return id;
    }

    public  String getVoteAverage()
    {
        return vote_average;
    }

    public String getPopularity()
    {
        return popularity;
    }
    public String getPoster_path() {
        return Constant.STATIC_BASE_URL + poster_path;
    }

    public String getBackdrop_path() {
        return Constant.STATIC_BASE_URL + backdrop_path;
    }

}
