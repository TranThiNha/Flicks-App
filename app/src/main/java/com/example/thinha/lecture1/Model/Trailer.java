package com.example.thinha.lecture1.Model;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ThiNha on 10/15/2016.
 */
public class Trailer {
    @SerializedName("youtube")
    private List<YoutubeVideo> trailers;

    public List<YoutubeVideo> getTrailers() {
        return trailers;
    }
}
