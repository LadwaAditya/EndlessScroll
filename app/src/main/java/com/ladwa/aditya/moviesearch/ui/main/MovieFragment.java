package com.ladwa.aditya.moviesearch.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ladwa.aditya.moviesearch.R;
import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MovieFragment extends BaseFragment implements MainContract.View {

    @Inject MainPresenter presenter;

    public static Fragment newInstance() {
        return new MovieFragment();
    }

    @Override public int getLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentComponent().inject(this);
        presenter.attachView(this);
    }

    @Override public void setUpView() {
        ((MainActivity) getActivity()).getPublishSubject().subscribe(new Consumer<String>() {
            @Override public void accept(String s) throws Exception {
                presenter.getMovies(s, 1);
            }
        });
    }

    @Override public void showMovies(List<MovieResponse.Movie> movieList) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
