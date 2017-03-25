package com.ladwa.aditya.moviesearch.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ladwa.aditya.moviesearch.R;
import com.ladwa.aditya.moviesearch.data.model.MovieResponse;
import com.ladwa.aditya.moviesearch.databinding.FragmentMovieBinding;
import com.ladwa.aditya.moviesearch.ui.adapter.MovieListAdapter;
import com.ladwa.aditya.moviesearch.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MovieFragment extends BaseFragment implements MainContract.View {

    @Inject MainPresenter presenter;
    private FragmentMovieBinding movieBinding;
    private static final String KEY_TAB = "tab";
    private int mTab = 0;

    public static Fragment newInstance(int tab) {
        Bundle bundle = new Bundle();
        MovieFragment movieFragment = new MovieFragment();
        switch (tab) {
            case 0:
                bundle.putInt(KEY_TAB, tab);
                movieFragment.setArguments(bundle);
                return movieFragment;
            case 1:
                bundle.putInt(KEY_TAB, tab);
                movieFragment.setArguments(bundle);
                return movieFragment;
        }
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mTab = getArguments().getInt(KEY_TAB);
        movieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        Timber.d(String.valueOf(mTab));
        return movieBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentComponent().inject(this);
        presenter.attachView(this);
    }

    @Override public void setUpView() {
        if (mTab == 0) {
            movieBinding.rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else if (mTab == 1) {
            movieBinding.rvMovie.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        ((MainActivity) getActivity()).getPublishSubject().subscribe(integerStringPair -> {
            if (integerStringPair.first == mTab) {
                movieBinding.progressBar.setVisibility(View.VISIBLE);
                presenter.getMovies(integerStringPair.second, 1);
            }
        });
        movieBinding.rvMovie.setItemAnimator(new DefaultItemAnimator());
    }

    @Override public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        hidePrograssBar();
    }

    private void hidePrograssBar() {
        movieBinding.progressBar.setVisibility(View.INVISIBLE);
    }

    @Override public void showMovies(List<MovieResponse.Movie> movieList) {
        movieBinding.rvMovie.setAdapter(new MovieListAdapter(movieList));
        hidePrograssBar();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
