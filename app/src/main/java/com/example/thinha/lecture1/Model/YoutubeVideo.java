package com.example.thinha.lecture1.Model;

import com.example.thinha.lecture1.Utils.Constant;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThiNha on 10/15/2016.
 */
public class YoutubeVideo {

    @SerializedName("source")
    private String source;

    public String getSource()
    {
        return source;
    }
}
