package com.example.thinha.lecture1.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinha.lecture1.Model.Movie;
import com.example.thinha.lecture1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by ThiNha on 10/13/2016.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    private List<Movie> mMovies;
    private static final  int TYPE_NORMAL = 0;
    private static final int TYPE_POPULAR = 1;

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, -1);
        mMovies = movies;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (Float.parseFloat(mMovies.get(position).getVoteAverage())>5)
            return TYPE_POPULAR;
        else
            return TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = mMovies.get(position);
        int type = getItemViewType(position);
        Configuration configuration = getContext().getResources().getConfiguration();
        switch (type)
        {
            case TYPE_NORMAL:
            {
                ViewHolder viewHolder;

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                    viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
                    viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }


                //fill data
                viewHolder.tvTitle.setText(movie.getTiltle());
                viewHolder.tvOverview.setText(movie.getOverview());

                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Picasso.with(getContext())
                            .load(movie.getPoster_path())
                            .placeholder(R.drawable.vertical)
                            .transform(new RoundedCornersTransformation(10, 0))
                            .into(viewHolder.ivCover);
                }
                else
                {
                    Picasso.with(getContext())
                            .load(movie.getBackdrop_path())
                            .placeholder(R.drawable.horizontal)
                            .transform(new RoundedCornersTransformation(10,0))
                            .into(viewHolder.ivCover);
                }
            }

            break;

        case TYPE_POPULAR:
        {
            PopularViewHolder popularViewHolder;
            if (convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_popular, parent, false);
                popularViewHolder = new PopularViewHolder();
                popularViewHolder.ivPopularCover = (ImageView) convertView.findViewById(R.id.ivPopularCover);
                convertView.setTag(popularViewHolder);
            } else
            {
                popularViewHolder = (PopularViewHolder) convertView.getTag();
            }
            //fill data
            Picasso.with(getContext())
                    .load(movie.getBackdrop_path())
                    .transform(new RoundedCornersTransformation(10, 10)).into(popularViewHolder.ivPopularCover);
        }
    }
        return convertView;
}

    private class ViewHolder
    {
        public TextView tvTitle;
        public TextView tvOverview;
        public ImageView ivCover;
    }

    private class PopularViewHolder
    {
        public ImageView ivPopularCover;
    }
}
