package com.example.thinha.lecture1.api;

import com.example.thinha.lecture1.Model.NowPlaying;
import com.example.thinha.lecture1.Model.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ThiNha on 10/13/2016.
 */
public interface MovieApi {
    @GET("now_playing")
    Call<NowPlaying> getNowPlaying();

    @GET("{videoId}/trailers")
    Call<Trailer> getTrailers(@Path("videoId") String videoId);

    @GET("popular")
    Call<NowPlaying> getPopular(@Query("page")Integer page);
}
