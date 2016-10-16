package com.example.thinha.lecture1;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.thinha.lecture1.Adapter.MovieAdapter;
import com.example.thinha.lecture1.Model.Movie;
import com.example.thinha.lecture1.Model.NowPlaying;
import com.example.thinha.lecture1.Model.Trailer;
import com.example.thinha.lecture1.Model.YoutubeVideo;
import com.example.thinha.lecture1.Utils.RetrofitUtils;
import com.example.thinha.lecture1.api.MovieApi;
import com.example.thinha.lecture1.api.TrailerApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovie;
    private MovieApi mMovieApi;
    private ProgressBar pbLoad;
    MovieAdapter movieAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    int pageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieApi = RetrofitUtils.get(getString(R.string.api_key)).create(MovieApi.class);
        lvMovie = (ListView) findViewById(R.id.lvItems);
        pbLoad = (ProgressBar) findViewById(R.id.pbLoad);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        fetchMovie();

        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //get selected movie
                final Movie movie = movieAdapter.getItem(i);

                //get type of movie (if it's popular or not)
                final int Type = movieAdapter.getItemViewType(i);
                mMovieApi.getTrailers(movie.getId()).enqueue(new Callback<Trailer>() {
                    @Override
                    public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                        List<YoutubeVideo> trailers = response.body().getTrailers();

                        if (!trailers.isEmpty())
                        {
                            String Source = trailers.get(0).getSource();
                            Intent intent = new Intent(MainActivity.this,DetailMovieActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("movie",movie);
                            intent.putExtra("source",Source);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Trailer> call, Throwable t) {

                    }
                });


            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMovieApi.getPopular(pageCount).enqueue(new Callback<NowPlaying>() {
                    @Override
                    public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                        handleResponse(response);
                        swipeRefreshLayout.setRefreshing(false);
                        pageCount++;
                    }

                    @Override
                    public void onFailure(Call<NowPlaying> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void fetchMovie()
    {
        mMovieApi.getNowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
            handleResponse(response);
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
    private void handleResponse(Response<NowPlaying>response)
    {
        movieAdapter = new MovieAdapter(this, response.body().getMovies());
        lvMovie.setAdapter(movieAdapter );


        pbLoad.setVisibility(View.GONE);

    }

}

