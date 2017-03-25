package com.ladwa.aditya.moviesearch.util;

import com.ladwa.aditya.moviesearch.data.model.MovieResponse;

import java.util.ArrayList;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class TestDataFactory {


    public static MovieResponse.Movie makeMovie(String name) {
        MovieResponse.Movie movie = new MovieResponse.Movie();
        movie.setImdbID(name + "imdb");
        movie.setPoster(name + "poster");
        movie.setTitle(name + "title");
        movie.setType(name + "type");
        movie.setYear(name + "year");
        return movie;
    }


    public static ArrayList<MovieResponse.Movie> makMovieArrayList(int number) {
        ArrayList<MovieResponse.Movie> movieArrayList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            movieArrayList.add(makeMovie("name" + i));
        }
        return movieArrayList;
    }
}
