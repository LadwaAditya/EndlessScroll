package com.ladwa.aditya.moviesearch.data.remote;

import com.ladwa.aditya.moviesearch.data.model.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aditya on 25-Mar-17.
 */

public interface MovieSearchService {

    @GET("?")
    Single<MovieResponse> getMovie(@Query("s") String search, @Query("page") int page);
}
