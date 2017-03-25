package com.ladwa.aditya.moviesearch.ui.main;

import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.ui.base.MvpPresenter;
import com.ladwa.aditya.moviesearch.ui.base.MvpView;

import java.util.List;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MainContract {

    interface Presenter extends MvpPresenter<View> {
        void getMovies(String search, int page);
    }

    interface View extends MvpView {
        void showMovies(List<MovieResponse.Movie> movieList);

        void showEmptyMovies(String message);
    }
}
