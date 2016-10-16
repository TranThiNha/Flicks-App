package com.example.thinha.lecture1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.thinha.lecture1.Model.Movie;
import com.example.thinha.lecture1.Model.YoutubeVideo;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DetailMovieActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY ="AIzaSyDTkPvID4hZ6DF8hS2EUMHDaKkgiiKUcis";
    private static final int RECOVERY_REQUEST = 1;
    private String videoId ="aJ7BoNG-r2c";
    String source;
    TextView tvDetailTitle, tvRelease_Date, tvPopularity, tvDetailOverView;
    YouTubePlayer player;
    YouTubePlayerView youTubePlayerView;
    RatingBar rtVoteAverage;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
        tvRelease_Date = (TextView) findViewById(R.id.tvRelease_Date);
        tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvDetailOverView = (TextView) findViewById(R.id.tvDetailOverview);
        rtVoteAverage = (RatingBar) findViewById(R.id.rating);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        setData();

    }


    public void setData()
    {
        Intent intent = getIntent();
        movie =(Movie) intent.getExtras().get("movie");
        videoId  = intent.getStringExtra("source");
        youTubePlayerView.initialize(API_KEY,this);

        tvDetailTitle.setText(movie.getTiltle());
        tvDetailOverView.setText(movie.getOverview());
        tvRelease_Date.setText("Release Date: " + movie.getRelease_date());
        tvPopularity.setText("Popularity: " + movie.getPopularity());
        rtVoteAverage.setRating(Float.parseFloat(movie.getVoteAverage()));
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b)
        {
            player = youTubePlayer;
            player.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RECOVERY_REQUEST)
        {
            youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);
            youTubePlayerView.initialize(API_KEY,this);
        }
    }
}





