package com.ladwa.aditya.moviesearch.data;

import com.ladwa.aditya.moviesearch.data.model.MovieResponse;

import io.reactivex.Single;

/**
 * Created by Aditya on 25-Mar-17.
 */

public interface DataRepository {
    /**
     * Provides a list of {@link com.ladwa.aditya.moviesearch.data.model.MovieResponse.Movie} as Response
     * given a search string and page
     *
     * @param search
     * @param page
     * @return
     */
    Single<MovieResponse> getMovies(String search, int page);
}
