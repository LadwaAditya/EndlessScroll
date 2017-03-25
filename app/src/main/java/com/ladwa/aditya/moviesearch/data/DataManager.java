package com.ladwa.aditya.moviesearch.data;

import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.data.remote.MovieSearchService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Aditya on 25-Mar-17.
 */
@Singleton
public class DataManager implements DataRepository {

    private final MovieSearchService movieSearchService;

    @Inject
    public DataManager(MovieSearchService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    public Single<MovieResponse> getMovies(String search, int page) {
        return movieSearchService.getMovie(search, page);
    }
}
