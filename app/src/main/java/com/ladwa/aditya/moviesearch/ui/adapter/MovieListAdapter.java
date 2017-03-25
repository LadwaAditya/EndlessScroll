package com.ladwa.aditya.moviesearch.ui.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ladwa.aditya.moviesearch.R;
import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.databinding.ListItemMovieBinding;

import java.util.List;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {


    private List<MovieResponse.Movie> movieArrayList;

    public MovieListAdapter(List<MovieResponse.Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemMovieBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_movie, parent, false);
        return new ViewHolder(binding);
    }


    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieResponse.Movie movie = movieArrayList.get(position);
        holder.bindMovie(movie);
    }

    @Override public int getItemCount() {
        return movieArrayList != null ? movieArrayList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemMovieBinding mBinding;
        private MovieResponse.Movie mMovie;

        public ViewHolder(ListItemMovieBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            binding.setHolder(this);
        }

        public void bindMovie(MovieResponse.Movie movie) {
            mMovie = movie;
            mBinding.setMovie(movie);
            mBinding.executePendingBindings();
        }

        @BindingAdapter({"bind:imageUrl"}) public static void loadImage(ImageView imageView, String imageUrl) {
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .placeholder(R.color.colorAccent)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }

        public void onClick(View view) {
            Context context = view.getContext();
            Toast.makeText(context, mMovie.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
