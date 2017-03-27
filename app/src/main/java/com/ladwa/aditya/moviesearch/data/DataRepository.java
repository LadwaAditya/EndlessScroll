package com.ladwa.aditya.moviesearch.data;

import com.ladwa.aditya.moviesearch.data.model.MovieResponse;

import io.reactivex.Single;

/**
 * Created by Aditya on 25-Mar-17.
 */

public interface DataRepository {
    Single<MovieResponse> getMovies(String search, int page);
}
